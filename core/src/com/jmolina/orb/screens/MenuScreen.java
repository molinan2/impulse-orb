package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Vars;
import com.jmolina.orb.groups.SectionTitleGroup;

/**
 * TODO Cuando se pierde el focus, hay que cancelar de alguna manera el movimientoo de scroll
 */
public class MenuScreen extends BaseScreen {

    private SectionTitleGroup sectionTitleGroup;
    private Table table;
    private ScrollPane scrollPane;

    public MenuScreen() {
        super();

        sectionTitleGroup = new SectionTitleGroup();
        sectionTitleGroup.setGridPosition(1, 3);
        getStage().addActor(sectionTitleGroup);

        table = new Table();
        table.debug();
        table.top();
        table.setPosition(Vars.GRID_UNIT, 0f);
        table.setWidth(Vars.VIEWPORT_WIDTH - 2 * Vars.GRID_UNIT);
        // table.setFillParent(true);
        // table.pack();

        scrollPane = new ScrollPane(table);
        scrollPane.setWidth(Vars.VIEWPORT_WIDTH);
        scrollPane.setHeight(Utils.yGrid(4.5f));
        scrollPane.setPosition(0f, 0f);

        getStage().addActor(scrollPane);
    }

    @Override
    public void dispose() {
        sectionTitleGroup.dispose();
        super.dispose();
    }

    /**
     * TODO Cuando estén claras las funcionalidades que se usan de Table, crear una API simple
     * @return Table
     */
    private Table getTable() {
        return table;
    }

    public <T extends Actor> void addRow(T actor) {
        addRow(actor, 0.5f, 10f);
    }

    public <T extends Actor> void addRow(T actor, float bottomPadding) {
        addRow(actor, bottomPadding, 10f);
    }

    public <T extends Actor> void addRow(T actor, float bottomPadding, float width) {
        getTable().row();
        getTable().add(actor).width(width * Vars.GRID_UNIT).expandX().padBottom(bottomPadding * Vars.GRID_UNIT);
    }
}
