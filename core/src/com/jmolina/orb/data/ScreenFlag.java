package com.jmolina.orb.data;

import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.BaseScreen;

public class ScreenFlag {

    private boolean flag;
    private ScreenManager.Key screen;
    private BaseScreen.Hierarchy hierarchy;

    public void Flag() {
        this.flag = false;
    }

    public void enable(ScreenManager.Key screen, BaseScreen.Hierarchy hierarchy) {
        this.flag = true;
        this.screen = screen;
        this.hierarchy = hierarchy;
    }

    public boolean isEnabled() {
        return flag;
    }

    public ScreenManager.Key getScreen() {
        return isEnabled() ? screen : null;
    }

    public BaseScreen.Hierarchy getHierarchy() {
        return isEnabled() ? hierarchy : null;
    }

}
