/*
 * IMPULSE ORB
 * Copyright (C) 2016 Juan M. Molina
 *
 * This file is part of the IMPULSE ORB source code.
 *
 * IMPULSE ORB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IMPULSE ORB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jmolina.orb.screens.levels;

import com.jmolina.orb.interfaces.SuperManager;
import com.jmolina.orb.managers.ScreenManager;
import com.jmolina.orb.screens.Level;
import com.jmolina.orb.situations.hero.Situation400;
import com.jmolina.orb.situations.hero.Situation401;
import com.jmolina.orb.situations.hero.Situation402;
import com.jmolina.orb.situations.hero.Situation403;
import com.jmolina.orb.situations.hero.Situation404;
import com.jmolina.orb.situations.hero.Situation406;
import com.jmolina.orb.situations.hero.Situation407;
import com.jmolina.orb.situations.hero.Situation408;
import com.jmolina.orb.situations.hero.Situation409CCW;
import com.jmolina.orb.situations.hero.Situation409CW;
import com.jmolina.orb.situations.hero.Situation410;
import com.jmolina.orb.situations.hero.Situation499;

/**
 * Nivel 4
 */
public class Level4 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level4(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_4);
        setSuccessScreen(ScreenManager.Key.SUCCESS_4);

        addSituation(Situation400.class);
        addSituation(Situation401.class);
        addSituation(Situation401.class);
        addSituation(Situation409CW.class);
        addSituation(Situation402.class);
        addSituation(Situation402.class);
        addSituation(Situation409CCW.class);
        addSituation(Situation403.class);
        addSituation(Situation403.class);
        addSituation(Situation403.class);
        addSituation(Situation403.class);
        addSituation(Situation410.class);
        addSituation(Situation404.class);
        addSituation(Situation404.class);
        addSituation(Situation404.class);
        addSituation(Situation404.class);
        addSituation(Situation406.class);
        addSituation(Situation406.class);
        addSituation(Situation406.class);
        addSituation(Situation406.class);
        addSituation(Situation401.class);
        addSituation(Situation407.class);
        addSituation(Situation407.class);
        addSituation(Situation407.class);
        addSituation(Situation407.class);
        addSituation(Situation409CW.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation408.class);
        addSituation(Situation499.class);

        setOrbStartPosition(6, 9);
    }

}
