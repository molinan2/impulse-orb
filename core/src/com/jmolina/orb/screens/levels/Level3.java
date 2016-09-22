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
import com.jmolina.orb.situations.expert.Situation300;
import com.jmolina.orb.situations.expert.Situation301;
import com.jmolina.orb.situations.expert.Situation302;
import com.jmolina.orb.situations.expert.Situation303;
import com.jmolina.orb.situations.expert.Situation304;
import com.jmolina.orb.situations.expert.Situation305;
import com.jmolina.orb.situations.expert.Situation306;
import com.jmolina.orb.situations.expert.Situation307;
import com.jmolina.orb.situations.expert.Situation308L;
import com.jmolina.orb.situations.expert.Situation308R;
import com.jmolina.orb.situations.expert.Situation309;
import com.jmolina.orb.situations.expert.Situation310;
import com.jmolina.orb.situations.expert.Situation311;
import com.jmolina.orb.situations.expert.Situation399;

/**
 * Nivel 3
 */
public class Level3 extends Level {

    /**
     * Consntructor
     *
     * @param sm SuperManager
     */
    public Level3(SuperManager sm) {
        super(sm);

        setPreviousScreen(ScreenManager.Key.LAUNCH_3);
        setSuccessScreen(ScreenManager.Key.SUCCESS_3);

        addSituation(Situation300.class);
        addSituation(Situation302.class);
        addSituation(Situation302.class);
        addSituation(Situation302.class);
        addSituation(Situation301.class);
        addSituation(Situation301.class);
        addSituation(Situation301.class);
        addSituation(Situation303.class);
        addSituation(Situation303.class);
        addSituation(Situation303.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation304.class);
        addSituation(Situation305.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation306.class);
        addSituation(Situation305.class);
        addSituation(Situation307.class);
        addSituation(Situation307.class);
        addSituation(Situation307.class);
        addSituation(Situation307.class);
        addSituation(Situation305.class);
        addSituation(Situation308R.class);
        addSituation(Situation308R.class);
        addSituation(Situation308R.class);
        addSituation(Situation308R.class);
        addSituation(Situation308L.class);
        addSituation(Situation308L.class);
        addSituation(Situation308L.class);
        addSituation(Situation308L.class);
        addSituation(Situation301.class);
        addSituation(Situation309.class);
        addSituation(Situation309.class);
        addSituation(Situation309.class);
        addSituation(Situation309.class);
        addSituation(Situation305.class);
        addSituation(Situation310.class);
        addSituation(Situation310.class);
        addSituation(Situation310.class);
        addSituation(Situation310.class);
        addSituation(Situation301.class);
        addSituation(Situation311.class);
        addSituation(Situation311.class);
        addSituation(Situation311.class);
        addSituation(Situation301.class);
        addSituation(Situation303.class);
        addSituation(Situation303.class);
        addSituation(Situation399.class);

        setOrbStartPosition(6, 9);
    }

}
