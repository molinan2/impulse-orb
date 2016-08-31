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
