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
import com.jmolina.orb.situations.advanced.Situation200;
import com.jmolina.orb.situations.advanced.Situation201;
import com.jmolina.orb.situations.advanced.Situation202;
import com.jmolina.orb.situations.advanced.Situation203;
import com.jmolina.orb.situations.advanced.Situation204;
import com.jmolina.orb.situations.advanced.Situation205CCW;
import com.jmolina.orb.situations.advanced.Situation205CW;
import com.jmolina.orb.situations.advanced.Situation206;
import com.jmolina.orb.situations.advanced.Situation207CCW;
import com.jmolina.orb.situations.advanced.Situation207CW;
import com.jmolina.orb.situations.advanced.Situation208;
import com.jmolina.orb.situations.advanced.Situation209CCW;
import com.jmolina.orb.situations.advanced.Situation209CW;
import com.jmolina.orb.situations.advanced.Situation210;
import com.jmolina.orb.situations.advanced.Situation299;

/**
 * Nivel 2
 */
public class Level2 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level2(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_2);
        setSuccessScreen(ScreenManager.Key.SUCCESS_2);

        addSituation(Situation200.class);
        addSituation(Situation201.class);
        addSituation(Situation201.class);
        addSituation(Situation202.class);
        addSituation(Situation202.class);
        addSituation(Situation203.class);
        addSituation(Situation203.class);
        addSituation(Situation203.class);
        addSituation(Situation204.class);
        addSituation(Situation204.class);
        addSituation(Situation201.class);
        addSituation(Situation205CW.class);
        addSituation(Situation205CCW.class);
        addSituation(Situation205CW.class);
        addSituation(Situation206.class);
        addSituation(Situation201.class);
        addSituation(Situation207CW.class);
        addSituation(Situation207CCW.class);
        addSituation(Situation207CW.class);
        addSituation(Situation207CCW.class);
        addSituation(Situation208.class);
        addSituation(Situation208.class);
        addSituation(Situation201.class);
        addSituation(Situation209CW.class);
        addSituation(Situation209CCW.class);
        addSituation(Situation209CW.class);
        addSituation(Situation210.class);
        addSituation(Situation210.class);
        addSituation(Situation210.class);
        addSituation(Situation299.class);

        setOrbStartPosition(6, 10);
    }

}
