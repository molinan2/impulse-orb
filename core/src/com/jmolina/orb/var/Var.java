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

package com.jmolina.orb.var;

import com.jmolina.orb.managers.ScreenManager;

public class Var {

    public static final ScreenManager.Key FIRST_SCREEN = ScreenManager.Key.MAIN;

    public static final boolean DEBUG_INVULNERABILITY = false;
    public static final boolean DEBUG_RENDERER = false;
    public static final boolean DEBUG_FRAME_TIME = false;

    public static final String APP_NAME = "Impulse Orb";
    public static final String APP_VERSION = "0.8.10";
    public static final String APP_AUTHOR = "Juan M. Molina";

    public static final float SCREEN_HEIGHT = 1184.0f;
    public static final float SCREEN_WIDTH = 768.0f;
    public static final float GRID_CELL_SIZE = 64f;
    public static final float FPS = 1/60f;

    public static final String EXCEPTION_EMPTY_LIST_ACCESS = "Can't access item on an empty list";

    public static final int COLOR_LILAC = 0x765AE0ff;
    public static final int COLOR_LILAC_MEDIUM = 0x9391A1ff;
    public static final int COLOR_LILAC_DARK = 0x646273ff;
    public static final int COLOR_GREEN_DARK = 0x269964ff;
    public static final int COLOR_WHITE = 0xffffffff;
    public static final int COLOR_BLACK = 0x404040ff;
    public static final int COLOR_RED = 0xff0000ff;

    public static final float TIME_LEVEL_1_DEV = 53.75f;
    public static final float TIME_LEVEL_1_GOLD = 70;
    public static final float TIME_LEVEL_1_SILVER = 95;
    public static final float TIME_LEVEL_1_BRONZE = 129;
    public static final float TIME_LEVEL_2_DEV = 97.87f;
    public static final float TIME_LEVEL_2_GOLD = 120;
    public static final float TIME_LEVEL_2_SILVER = 155;
    public static final float TIME_LEVEL_2_BRONZE = 220;
    public static final float TIME_LEVEL_3_DEV = 95.66f;
    public static final float TIME_LEVEL_3_GOLD = 127;
    public static final float TIME_LEVEL_3_SILVER = 157;
    public static final float TIME_LEVEL_3_BRONZE = 217;
    public static final float TIME_LEVEL_4_DEV = 126.37f;
    public static final float TIME_LEVEL_4_GOLD = 148;
    public static final float TIME_LEVEL_4_SILVER = 188;
    public static final float TIME_LEVEL_4_BRONZE = 263;
    public static final float TIME_LEVEL_5_DEV = 129.50f;
    public static final float TIME_LEVEL_5_GOLD = 149;
    public static final float TIME_LEVEL_5_SILVER = 189;
    public static final float TIME_LEVEL_5_BRONZE = 270;

}
