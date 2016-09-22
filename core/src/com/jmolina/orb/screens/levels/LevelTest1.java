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
import com.jmolina.orb.situations.test.SituationT100;
import com.jmolina.orb.situations.test.SituationT101;
import com.jmolina.orb.situations.test.SituationT102;
import com.jmolina.orb.situations.test.SituationT103;
import com.jmolina.orb.situations.test.SituationT104;
import com.jmolina.orb.situations.test.SituationT105;
import com.jmolina.orb.situations.test.SituationT106;
import com.jmolina.orb.situations.test.SituationT107;
import com.jmolina.orb.situations.test.SituationT199;

/**
 * Nivel de Test 1
 */
public class LevelTest1 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public LevelTest1(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_T1);
        setSuccessScreen(ScreenManager.Key.SUCCESS_T1);

        addSituation(SituationT100.class);
        addSituation(SituationT101.class);
        addSituation(SituationT102.class);
        addSituation(SituationT103.class);
        addSituation(SituationT104.class);
        addSituation(SituationT105.class);
        addSituation(SituationT106.class);
        addSituation(SituationT107.class);
        addSituation(SituationT199.class);

        setOrbStartPosition(6, 9);
    }

}
