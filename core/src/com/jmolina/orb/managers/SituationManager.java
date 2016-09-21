package com.jmolina.orb.managers;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolina.orb.elements.Element;
import com.jmolina.orb.elements.Orb;
import com.jmolina.orb.elements.WorldElement;
import com.jmolina.orb.situations.Situation;
import com.jmolina.orb.situations.SituationFactory;
import com.jmolina.orb.var.Utils;

import java.util.ArrayList;

/**
 * Manager de situaciones de un nivel de juego. Se encarga de instanciar las situaciones que entran
 * en el campo visible y destruir las que salen de el.
 */
public class SituationManager {

    /**
     * Adyacencia.
     *
     * TOP: Si la situacion mas cercana a la actual es la superior.
     * BOTTOM: Si la situacion mas cercana a la actual es la inferior.
     */
    private enum Adjacency { TOP, BOTTOM }

    private final int Z_INDEX_BLACK = 10000;
    private final int Z_INDEX_ORB = 20000;
    private final int MIN_VISIBLE_SITUATIONS = 1;
    private final int MAX_VISIBLE_SITUATIONS = 3;

    /** Factoria de situaciones */
    private SituationFactory situationFactory;

    /** Mapa del nivel: lista de clases de situaciones ordenadas */
    private ArrayList<Class> map;

    /** Situaciones visibles que se dibujaran */
    private ArrayList<Situation> visible;

    /** Indices de las situaciones visibles en el mapa del nivel */
    private ArrayList<Integer> indexes;

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
        visible = new ArrayList<Situation>();
        this.orb = orb;
        this.stage = stage;
        this.viewport = viewport;
        situationFactory = new SituationFactory(am, world, pixelsPerMeter);
        map = new ArrayList<Class>();
    }

    /**
     * Destruye las situaciones y libera su memoria.
     */
    public void removeAll() {
        for (Situation situation : visible)
            situation.dispose();

        visible.clear();
        indexes.clear();
    }

    /**
     * Actualiza las situaciones visibles. Primero se calculan los indices visibles; despues, se
     * eliminan las situaciones previamente visibles que no tengan ninguno de estosestos indices;
     * finalmente, se añaden las situaciones que tengan alguno de estos indices y no fueran previamente
     * visibles.
     */
    public void update() {
        updateIndexes();
        removeOld();
        addNew();
    }

    /**
     * Actualiza la lista de los indices mas cercanos al actual (campo visible)
     */
    private void updateIndexes() {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int current = getCurrentIndex();
        Adjacency adjacency = findAdjacency(getAltitude());

        for (int i = MIN_VISIBLE_SITUATIONS-1; i < MAX_VISIBLE_SITUATIONS; i++) {
            int index;
            int offset = (i+1)/2;

            if (adjacency == Adjacency.BOTTOM)
                offset = -offset;

            if (i%2 != 0) index = current + offset;
            else index = current - offset;

            if (isWithinBounds(index))
                indexes.add(index);
        }

        this.indexes = indexes;
    }

    /**
     * Elimina las situaciones que ya no estan en el campo visible
     */
    private void removeOld() {
        for (int i = 0; i < visible.size(); i++) {
            Situation situation = visible.get(i);

            if (!indexes.contains(situation.getPositionY())) {
                situation.dispose();
                visible.remove(i);
            }
        }
    }

    /**
     * Añade las situaciones que acaban de entrar en el campo visible
     */
    private void addNew() {
        for (int i = 0; i < indexes.size(); i++) {
            boolean visible = false;

            for (Situation visibleSituation : this.visible) {
                if (visibleSituation.getPositionY() == indexes.get(i)) {
                    visible = true;
                    break;
                }
            }

            if (!visible) {
                int situationIndex = indexes.get(i);
                Class situationClass = map.get(situationIndex);
                Situation situation = situationFactory.newSituation(situationClass);
                placeSituation(situation, indexes.get(i));
                this.visible.add(situation);
            }
        }
    }

    /**
     * Comprueba si un indice esta entre los limites permitidos. El minimo es 0 (minimo ordinal de
     * situacion) y el maximo es la cantidad de situaciones del nivel menos 1.
     *
     * @param index Indice
     */
    private boolean isWithinBounds(int index) {
        return index >= 0 && index < map.size();
    }

    /**
     * Devuelve las situaciones visibles actualmente
     */
    public ArrayList<Situation> getVisible() {
        ArrayList<Situation> situations = new ArrayList<Situation>();

        for (Situation situation : visible)
            situations.add(situation);

        return situations;
    }

    public void addSituation(Class clazz) {
        map.add(clazz);
    }

    /**
     * Obtiene la altitud de la camara/orbe (unidades relativas a la altura de una situacion)
     */
    private float getAltitude() {
        return orb.getPosition().y / (float) Situation.HEIGHT;
    }

    /**
     * Obtiene el indice ordinal de la situacion actual del orbe
     */
    private int getCurrentIndex() {
        return (int) getAltitude();
    }

    /**
     * Calcula la {@link Adjacency}, es decir, por donde toca la situacion mas cercana a la actual.
     *
     * @param altitude Altitud de la cámara/orbe (unidades relativas a la altura de una situacion)
     */
    private Adjacency findAdjacency(float altitude) {
        float decimalPart = Utils.decimalPart(altitude);

        if (decimalPart >= 0.5f)
            return Adjacency.TOP;
        else
            return Adjacency.BOTTOM;
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
        for (Situation situation : getVisible()) {
            for (Element element : situation.getElements()) {
                if (element.getFlavor() == WorldElement.Flavor.BLACK)
                    element.getActor().setZIndex(Z_INDEX_BLACK);
            }
        }

        orb.getActor().setZIndex(Z_INDEX_ORB);
    }

    /**
     * Destruye las situaciones visibles
     */
    public void dispose() {
        removeAll();
    }

}
