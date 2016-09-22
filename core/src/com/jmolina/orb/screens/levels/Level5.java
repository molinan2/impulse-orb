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
import com.jmolina.orb.situations.god.Situation500;
import com.jmolina.orb.situations.god.Situation501;
import com.jmolina.orb.situations.god.Situation502;
import com.jmolina.orb.situations.god.Situation503;
import com.jmolina.orb.situations.god.Situation504;
import com.jmolina.orb.situations.god.Situation505;
import com.jmolina.orb.situations.god.Situation506;
import com.jmolina.orb.situations.god.Situation507;
import com.jmolina.orb.situations.god.Situation508;
import com.jmolina.orb.situations.god.Situation509;
import com.jmolina.orb.situations.god.Situation511;
import com.jmolina.orb.situations.god.Situation599B;

/**
 * Nivel 5
 */
public class Level5 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level5(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_5);
        setSuccessScreen(ScreenManager.Key.SUCCESS_5);

        addSituation(Situation500.class);
        addSituation(Situation501.class);
        addSituation(Situation501.class);
        addSituation(Situation501.class);
        addSituation(Situation502.class);
        addSituation(Situation502.class);
        addSituation(Situation502.class);
        addSituation(Situation503.class);
        addSituation(Situation503.class);
        addSituation(Situation503.class);
        addSituation(Situation503.class);
        addSituation(Situation507.class);
        addSituation(Situation504.class);
        addSituation(Situation504.class);
        addSituation(Situation504.class);
        addSituation(Situation504.class);
        addSituation(Situation505.class);
        addSituation(Situation505.class);
        addSituation(Situation505.class);
        addSituation(Situation505.class);
        addSituation(Situation507.class);
        addSituation(Situation506.class);
        addSituation(Situation506.class);
        addSituation(Situation506.class);
        addSituation(Situation507.class);
        addSituation(Situation508.class);
        addSituation(Situation508.class);
        addSituation(Situation508.class);
        addSituation(Situation508.class);
        addSituation(Situation507.class);
        addSituation(Situation509.class);
        addSituation(Situation509.class);
        addSituation(Situation509.class);
        addSituation(Situation509.class);
        addSituation(Situation507.class);
        addSituation(Situation511.class);
        addSituation(Situation511.class);
        addSituation(Situation511.class);
        addSituation(Situation511.class);
        addSituation(Situation507.class);
        addSituation(Situation599B.class);

        setOrbStartPosition(6, 9);
    }

}
