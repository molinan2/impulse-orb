package com.jmolina.orb.managers;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.situations.SituationFactory;
import com.jmolina.orb.var.Utils;

import java.util.ArrayList;

/**
 * Manager de situaciones de un nivel de juego. Este manager se encarga de instanciar las situaciones
 * visibles y destruir las que ya no se ven.
 */
public class SituationManager {

    /**
     * Adyacencia.
     *
     * TOP: Si la situacion adyacente toca por la parte superior.
     * BOTTOM: Si la situacion adyacente toca por la parte inferior.
     */
    private enum Adjacency { TOP, BOTTOM }

    /**
     * Frontera
     *
     * BOUNDARY: Una de las 2 fronteras que distan del borde un 25% de la altura de la situacion.
     * MIDDLE: Frontera central, al 50% de altura.
     */
    private enum Frontier { BOUNDARY, MIDDLE }

    private final int Z_INDEX_BLACK = 10000;
    private final int Z_INDEX_ORB = 20000;

    /** Lista de clases de situaciones ordenadas que componen el nivel */
    private ArrayList<Class> situations;

    /** Factoria de situaciones */
    private SituationFactory situationFactory;

    /** Situaciones visibles */
    private Situation currentSituation, adjacentSituation;

    /** Adyacencias */
    private Adjacency adjacencyNow, adjacencyLast;

    private Orb orb;
    private Stage stage;
    private Viewport viewport;

    /**
     * Constructor
     *
     * @param am AssetManager
     * @param world Mundo fisico
     * @param orb Orbe
     * @param pixelsPerMeter Ratio de pixels/metros
     * @param stage Stage donde se dibujan las situaciones
     * @param viewport Viewport de la stage
     */
    public SituationManager(AssetManager am, World world, Orb orb, float pixelsPerMeter, Stage stage, Viewport viewport) {
        currentSituation = null;
        adjacentSituation = null;
        this.orb = orb;
        this.stage = stage;
        this.viewport = viewport;
        situationFactory = new SituationFactory(am, world, pixelsPerMeter);
        situations = new ArrayList<Class>();
    }

    /**
     * Destruye las situaciones y libera su memoria.
     */
    public void removeSituations() {
        if (currentSituation != null) currentSituation.dispose();
        if (adjacentSituation != null) adjacentSituation.dispose();
        currentSituation = null;
        adjacentSituation = null;
    }

    /**
     * Actualiza las situaciones visibles.
     */
    public void updateSituations() {
        float altitude = orb.getPosition().y / Situation.HEIGHT;
        int current = (int) altitude;
        adjacencyLast = adjacencyNow;
        adjacencyNow = findAdjacency(altitude);

        int adjacent = calculateAdjacent(current);

        if (noVisibility())
            initializeVisibility(current, adjacent);

        if (crossedFrontier()) {
            switch (findFrontier(altitude)) {
                case MIDDLE: newAdjacent(adjacent); break;
                case BOUNDARY: swapSituations(); break;
            }
        }
    }

    /**
     * Devuelve el array de situaciones del nivel actual
     *
     * @return SnapshotArray de {@link Situation}s
     */
    public SnapshotArray<Situation> getVisibleSituations() {
        SnapshotArray<Situation> situations = new SnapshotArray<Situation>();
        situations.add(getCurrentSituation());
        situations.add(getAdjacentSituation());

        return situations;
    }

    public void addSituation(Class clazz) {
        situations.add(clazz);
    }

    /**
     * Calcula el índice adyacente en función del actual
     *
     * @param current Indice de la situacion actual
     */
    private int calculateAdjacent(int current) {
        int adjacent;

        if (adjacencyNow == Adjacency.TOP)
            adjacent = current + 1;
        else
            adjacent = current - 1;

        return adjacent;
    }

    /**
     * Devuelve si no hay visibilidad. Esto ocurre si la situación actual no está inicializada, por
     * ejemplo al iniciar el nivel.
     */
    private boolean noVisibility() {
        return currentSituation == null;
    }

    /**
     * Inicializa la visibilidad: crea la situación actual y la añade al nivel. Si procede, crea la
     * situación adyacente y la añade al nivel.
     *
     * @param current Indice de la situacion actual
     * @param adjacent Indice de la situacion adyacente
     */
    private void initializeVisibility(int current, int adjacent) {
        Class currentClass = situations.get(current);
        currentSituation = situationFactory.newSituation(currentClass);
        placeSituation(currentSituation, current);

        if (adjacent >= 0 && adjacent <= situations.size()-1) {
            Class adjacentClass = situations.get(adjacent);
            adjacentSituation = situationFactory.newSituation(adjacentClass);
            placeSituation(adjacentSituation, adjacent);
        }
    }

    /**
     * Detecta el paso del Orb por una {@link Frontier}.
     */
    private boolean crossedFrontier() {
        return adjacencyLast != adjacencyNow;
    }

    /**
     * Calcula la {@link Adjacency}, es decir, si la situación adyacente se encuentra por arriba o por debajo.
     *
     * @param altitude Altitud de la cámara (en unidades del mundo)
     */
    private Adjacency findAdjacency(float altitude) {
        float decimalPart = Utils.decimalPart(altitude);

        if (decimalPart >= 0.5f)
            return Adjacency.TOP;
        else
            return Adjacency.BOTTOM;
    }

    /**
     * Calcula la {@link Frontier} más cercana. Se asume que la velocidad del Orb no va a ser tan
     * alta como para, en un tiempo de frame, cruzar una frontera y colocarse más cerca de OTRA
     * frontera.
     *
     * @param altitude Altitud del {@link Orb} (en unidades del mundo)
     */
    private Frontier findFrontier(float altitude) {
        float decimalPart = Utils.decimalPart(altitude);

        if (decimalPart > 0.25f && decimalPart < 0.75f)
            return Frontier.MIDDLE;
        else
            return Frontier.BOUNDARY;
    }

    /**
     * Se destruye la anterior adyacente, se instancia una nueva y se añade al nivel.
     *
     * @param adjacent Indice de la situacion adyacente
     */
    private void newAdjacent(int adjacent) {
        if (adjacentSituation != null)
            adjacentSituation.dispose();

        adjacentSituation = null;

        if (adjacent >= 0 && adjacent <= situations.size()-1) {
            Class adjacentClass = situations.get(adjacent);
            adjacentSituation = situationFactory.newSituation(adjacentClass);
            placeSituation(adjacentSituation, adjacent);
        }
    }

    /**
     * Se intercambian las situaciones adyacente y actual.
     */
    private void swapSituations() {
        Situation temporal = currentSituation;
        currentSituation = adjacentSituation;
        adjacentSituation = temporal;
    }

    /**
     * Devuelve la situacion actual
     */
    private Situation getCurrentSituation() {
        return currentSituation;
    }

    /**
     * Devuelve la situacion adyacente
     */
    private Situation getAdjacentSituation() {
        return adjacentSituation;
    }

    /**
     * Introduce una situacion en el nivel
     *
     * @param situation Situacion
     * @param positionY Posicion ordinal Y
     */
    private void placeSituation(Situation situation, int positionY) {
        situation.setPositionY(positionY);

        for (Element element : situation.getElements()) {
            stage.addActor(element.getActor());
            element.syncActor(viewport);
        }

        correctZIndexes();
    }

    /**
     * Corrige los Z index de los elementos BLACK (muros) y el Orb. Los elementos BLACK deben
     * permanecer siempre por encima de los demás. El Orb debe permanecer por encima de todos.
     */
    private void correctZIndexes() {
        for (Situation situation : getVisibleSituations()) {
            if (situation == null) continue;
            for (Element element : situation.getElements()) {
                if (element.getFlavor() == WorldElement.Flavor.BLACK)
                    element.getActor().setZIndex(Z_INDEX_BLACK);
            }
        }

        orb.getActor().setZIndex(Z_INDEX_ORB);
    }

}
