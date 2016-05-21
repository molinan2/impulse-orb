package com.jmolina.orb.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jmolina.orb.var.Utils;
import com.jmolina.orb.var.Vars;
import com.jmolina.orb.groups.SectionTitleGroup;

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
        getTable().row();
        getTable().add(actor).width(Vars.VIEWPORT_WIDTH - 2 * Vars.GRID_UNIT).expandX().padBottom(0.5f * Vars.GRID_UNIT);
    }
}
