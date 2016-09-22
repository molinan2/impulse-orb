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

import com.jmolina.orb.managers.AssetManager;

/**
 * Ficheros descriptores de fuentes FNT. Sus correspondientes texturas están definidas en
 * {@link Atlas} y deben cargarse a través del {@link AssetManager#getGameAtlas()}.
 */
public class Font {

    private static final String FONT = "font/";

    public static final String FONT_ROBOTO_REGULAR_30 = FONT + "roboto_regular_30s.fnt";
    public static final String FONT_ROBOTO_REGULAR_45 = FONT + "roboto_regular_45s.fnt";
    public static final String FONT_ROBOTO_MEDIUM_45 = FONT + "roboto_medium_45s.fnt";
    public static final String FONT_ROBOTO_BOLD_30 = FONT + "roboto_bold_30s.fnt";
    public static final String FONT_ROBOTO_BOLD_45 = FONT + "roboto_bold_45s.fnt";
    public static final String FONT_ROBOTO_BOLD_90 = FONT + "roboto_bold_90s.fnt";
    public static final String FONT_ROBOTO_BLACK_90 = FONT + "roboto_black_90s.fnt";

}
