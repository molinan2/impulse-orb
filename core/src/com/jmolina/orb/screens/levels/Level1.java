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
import com.jmolina.orb.situations.basic.Situation100;
import com.jmolina.orb.situations.basic.Situation101;
import com.jmolina.orb.situations.basic.Situation102L;
import com.jmolina.orb.situations.basic.Situation102R;
import com.jmolina.orb.situations.basic.Situation103;
import com.jmolina.orb.situations.basic.Situation104;
import com.jmolina.orb.situations.basic.Situation105;
import com.jmolina.orb.situations.basic.Situation106B;
import com.jmolina.orb.situations.basic.Situation107L;
import com.jmolina.orb.situations.basic.Situation107R;
import com.jmolina.orb.situations.basic.Situation108;
import com.jmolina.orb.situations.basic.Situation109;
import com.jmolina.orb.situations.basic.Situation199;

/**
 * Nivel 1
 */
public class Level1 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level1(SuperManager sm) {
        super(sm);
        setPreviousScreen(ScreenManager.Key.LAUNCH_1);
        setSuccessScreen(ScreenManager.Key.SUCCESS_1);

        addSituation(Situation100.class);
        addSituation(Situation106B.class);
        addSituation(Situation106B.class);
        addSituation(Situation101.class);
        addSituation(Situation107R.class);
        addSituation(Situation102R.class);
        addSituation(Situation107L.class);
        addSituation(Situation102L.class);
        addSituation(Situation103.class);
        addSituation(Situation103.class);
        addSituation(Situation104.class);
        addSituation(Situation104.class);
        addSituation(Situation107R.class);
        addSituation(Situation102R.class);
        addSituation(Situation105.class);
        addSituation(Situation105.class);
        addSituation(Situation107L.class);
        addSituation(Situation106B.class);
        addSituation(Situation106B.class);
        addSituation(Situation107R.class);
        addSituation(Situation102R.class);
        addSituation(Situation108.class);
        addSituation(Situation108.class);
        addSituation(Situation108.class);
        addSituation(Situation109.class);
        addSituation(Situation109.class);
        addSituation(Situation109.class);
        addSituation(Situation199.class);

        setOrbStartPosition(6, 9);
    }

}
