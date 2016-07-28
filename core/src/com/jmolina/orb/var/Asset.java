package com.jmolina.orb.var;

// Arroja una Exception al ejecutar la version GWT (HTML)

/**
 * Assets principales. {@link com.jmolina.orb.managers.AssetManager} precarga automáticamente todos
 * los assets mediante Reflection.
 */
public class Asset {

    private static final String FONT = "font/";
    private static final String UI = "ui/";
    private static final String APP = "app/";
    private static final String GAME = "game/";
    private static final String HUD = "hud/";
    private static final String SOUND = "sound/";
    private static final String MUSIC = "music/";

    public static final String FONT_ROBOTO_BOLD_30 = FONT + "roboto_bold_30.fnt";
    public static final String FONT_ROBOTO_BOLD_45 = FONT + "roboto_bold_45.fnt";
    public static final String FONT_ROBOTO_BOLD_90 = FONT + "roboto_bold_90.fnt";
    public static final String FONT_ROBOTO_MEDIUM_45 = FONT + "roboto_medium_45.fnt";
    public static final String FONT_ROBOTO_REGULAR_30 = FONT + "roboto_regular_30.fnt";
    public static final String FONT_ROBOTO_REGULAR_45 = FONT + "roboto_regular_45.fnt";

    public static final String UI_CHECKBOX_CHECKED = UI + "checkbox_checked.png";
    public static final String UI_CHECKBOX_UNCHECKED = UI + "checkbox_unchecked.png";
    public static final String UI_BUTTON_DEFAULT = UI + "button_default.png";
    public static final String UI_BUTTON_PLAY = UI + "button_play.png";
    public static final String UI_BUTTON_EXIT = UI + "button_exit.png";
    public static final String UI_CARD_COVER = UI + "card_cover.png";
    public static final String UI_CARD_BACKGROUND = UI + "card_background.png";
    public static final String UI_CARD_COVER_WHITE = UI + "card_cover_white.png";
    public static final String UI_CARD_PADLOCK = UI + "card_padlock.png";
    public static final String UI_LAUNCH_COVER = UI + "launch_cover.png";
    public static final String UI_LAUNCH_TITLE = UI + "launch_title.png";
    public static final String UI_LADDER_BACKGROUND = UI + "ladder_background.png";
    public static final String UI_SPLASH = UI + "splash.png";
    public static final String UI_SCROLL_KNOB = UI + "scroll_knob.png";
    public static final String UI_MAIN_TITLE = UI + "maintitle.png";
    public static final String UI_BACK = UI + "back.png";
    public static final String UI_BACKGROUND = UI + "background.png";
    public static final String UI_PROGRESS_BASE = UI + "progress_base.png";
    public static final String UI_PROGRESS_FILL = UI + "progress_fill.png";
    public static final String UI_SUCCESS_COVER_01 = UI + "success_cover.png";

    public static final String GAME_SQUARE_BLACK = GAME + "square_black.png";
    public static final String GAME_SQUARE_GREY = GAME + "square_grey.png";
    public static final String GAME_SQUARE_RED = GAME + "square_red.png";
    public static final String GAME_SQUARE_WHITE = GAME + "square_white.png";
    public static final String GAME_CIRCLE_BLACK = GAME + "circle_black.png";
    public static final String GAME_CIRCLE_GREY = GAME + "circle_grey.png";
    public static final String GAME_CIRCLE_RED = GAME + "circle_red.png";
    public static final String GAME_CIRCLE_VIOLET = GAME + "circle_violet.png";
    public static final String GAME_TRIANGLE_GREY = GAME + "triangle_grey.png";
    public static final String GAME_TRIANGLE_RED = GAME + "triangle_red.png";
    public static final String GAME_ORB_FRAGMENT_1 = GAME + "orb_fragment_01.png";
    public static final String GAME_ORB_FRAGMENT_2 = GAME + "orb_fragment_02.png";
    public static final String GAME_ORB_FRAGMENT_3 = GAME + "orb_fragment_03.png";
    public static final String GAME_ORB_FRAGMENT_4 = GAME + "orb_fragment_04.png";
    public static final String GAME_GESTURE_BASE = GAME + "gesture_base.png";
    public static final String GAME_GESTURE_LINE = GAME + "gesture_line.png";
    public static final String GAME_GESTURE_ARROWHEAD = GAME + "gesture_arrowhead.png";
    public static final String GAME_PARALLAX_LAYER_1 = GAME + "parallax_layer_01.png";
    public static final String GAME_PARALLAX_LAYER_2 = GAME + "parallax_layer_02.png";
    public static final String GAME_PARALLAX_LAYER_3 = GAME + "parallax_layer_03.png";
    public static final String GAME_EXIT = GAME + "exit.png";
    public static final String GAME_INIT = GAME + "init.png";
    public static final String GAME_HEAT = GAME + "heat.png";
    public static final String GAME_MAGNETIC_FIELD = GAME + "magnetic_field.png";
    public static final String GAME_MAGNETIC_PARTICLE = GAME + "magnetic_particle.png";

    public static final String HUD_BACKGROUND = HUD + "hud_background.png";
    public static final String HUD_PAUSE = HUD + "hud_pause.png";
    public static final String HUD_PAUSE_RESUME = HUD + "hud_pause_resume.png";
    public static final String HUD_GAUGE_BACKGROUND = HUD + "hud_gauge_background.png";
    public static final String HUD_GAUGE_FRAME = HUD + "hud_gauge_frame.png";
    public static final String HUD_GAUGE_BAR = HUD + "hud_gauge_bar.png";
    public static final String HUD_GAUGE_OVERLOAD = HUD + "hud_gauge_overload.png";

    public static final String APP_ICON_128 = APP + "icon_128.png";
    public static final String APP_ICON_64 = APP + "icon_64.png";
    public static final String APP_ICON_32 = APP + "icon_32.png";
    public static final String APP_ICON_16 = APP + "icon_16.png";

    public static final String SOUND_BACK = SOUND + "back.mp3";
    public static final String SOUND_BUTTON = SOUND + "button.mp3";
    public static final String SOUND_ELEMENT_COLLISION = SOUND + "collision_element.mp3";
    public static final String SOUND_WALL_COLLISION = SOUND + "collision_wall.mp3";
    public static final String SOUND_DESTROY = SOUND + "destroy.mp3";
    public static final String SOUND_EXIT = SOUND + "exit.mp3";
    public static final String SOUND_FLING = SOUND + "fling.mp3";
    public static final String SOUND_INIT = SOUND + "init.mp3";
    public static final String SOUND_OPTION = SOUND + "option.mp3";
    public static final String SOUND_TAP = SOUND + "tap.mp3";
    public static final String SOUND_TICK = SOUND + "tick.mp3";
    public static final String SOUND_WARNING = SOUND + "warning.mp3";

    public static final String MUSIC_GAME = MUSIC + "game.music.ogg";
    public static final String MUSIC_MENU = MUSIC + "menu.music.ogg";
    public static final String MUSIC_SUCCESS = MUSIC + "success.music.ogg";

}
