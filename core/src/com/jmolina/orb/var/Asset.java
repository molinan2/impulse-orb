package com.jmolina.orb.var;

// Arroja una Exception al ejecutar la version GWT (HTML)

/**
 * Assets principales. {@link com.jmolina.orb.managers.AssetManager} precarga automáticamente todos
 * los assets mediante Reflection. Todos los campos públicos deben ser de tipo String.
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
    public static final String UI_MULTICHECK_0 = UI + "multicheck_0.png";
    public static final String UI_MULTICHECK_1 = UI + "multicheck_1.png";
    public static final String UI_MULTICHECK_2 = UI + "multicheck_2.png";
    public static final String UI_BUTTON_DEFAULT = UI + "button_default.png";
    public static final String UI_BUTTON_SUCCESS = UI + "button_success.png";
    public static final String UI_BUTTON_DANGER = UI + "button_danger.png";
    public static final String UI_BUTTON_WARNING = UI + "button_warning.png";
    public static final String UI_BUTTON_FRAME = UI + "button_frame.png";
    public static final String UI_CARD_COVER_1 = UI + "card_cover_1.png";
    public static final String UI_CARD_COVER_2 = UI + "card_cover_2.png";
    public static final String UI_CARD_COVER_3 = UI + "card_cover_3.png";
    public static final String UI_CARD_COVER_4 = UI + "card_cover_4.png";
    public static final String UI_CARD_COVER_5 = UI + "card_cover_5.png";
    public static final String UI_CARD_COVER_1_BLUR = UI + "card_cover_1_blur.png";
    public static final String UI_CARD_COVER_2_BLUR = UI + "card_cover_2_blur.png";
    public static final String UI_CARD_COVER_3_BLUR = UI + "card_cover_3_blur.png";
    public static final String UI_CARD_COVER_4_BLUR = UI + "card_cover_4_blur.png";
    public static final String UI_CARD_COVER_5_BLUR = UI + "card_cover_5_blur.png";
    public static final String UI_CARD_COVER_FRAME = UI + "card_cover_frame.png";
    public static final String UI_CARD_BACKGROUND = UI + "card_background.png";
    public static final String UI_CARD_FRAME = UI + "card_frame.png";
    public static final String UI_CARD_PADLOCK = UI + "card_padlock.png";
    public static final String UI_LAUNCH_COVER_1 = UI + "launch_cover_1.png";
    public static final String UI_LAUNCH_COVER_2 = UI + "launch_cover_2.png";
    public static final String UI_LAUNCH_COVER_3 = UI + "launch_cover_3.png";
    public static final String UI_LAUNCH_COVER_4 = UI + "launch_cover_4.png";
    public static final String UI_LAUNCH_COVER_5 = UI + "launch_cover_5.png";
    public static final String UI_LAUNCH_FRAME = UI + "launch_frame.png";
    public static final String UI_LADDER_BACKGROUND = UI + "ladder_background.png";
    public static final String UI_SPLASH_BODY = UI + "splash_body.mip.png";
    public static final String UI_SPLASH_REFLECTIONS = UI + "splash_reflections.png";
    public static final String UI_SCROLL_KNOB = UI + "scroll_knob.png";
    public static final String UI_MAIN_TITLE_TEXT = UI + "maintitle_text.png";
    public static final String UI_MAIN_TITLE_ORB = UI + "maintitle_orb.mip.png";
    public static final String UI_BACK = UI + "back.png";
    public static final String UI_BACK_FRAME = UI + "back_frame.png";
    public static final String UI_BACKGROUND = UI + "background.png";
    public static final String UI_PROGRESS_BASE = UI + "progress_base.png";
    public static final String UI_PROGRESS_FILL = UI + "progress_fill.png";
    public static final String UI_SUCCESS_COVER_1 = UI + "success_cover_1.png";
    public static final String UI_SUCCESS_COVER_2 = UI + "success_cover_2.png";
    public static final String UI_SUCCESS_COVER_3 = UI + "success_cover_3.png";
    public static final String UI_SUCCESS_COVER_4 = UI + "success_cover_4.png";
    public static final String UI_SUCCESS_COVER_5 = UI + "success_cover_5.png";
    public static final String UI_SUCCESS_TITLE_BACKGROUND = UI + "success_title_background.png";
    public static final String UI_STAR_1ST = UI + "star_1st.png";
    public static final String UI_STAR_2ND = UI + "star_2nd.png";
    public static final String UI_STAR_3RD = UI + "star_3rd.png";
    public static final String UI_RATING_YES = UI + "rating_yes.png";
    public static final String UI_RATING_NO = UI + "rating_no.png";

    public static final String GAME_SQUARE_BLACK = GAME + "square_black.png";
    public static final String GAME_SQUARE_GREY = GAME + "square_grey.png";
    public static final String GAME_SQUARE_RED = GAME + "square_red.png";
    public static final String GAME_SQUARE_WHITE = GAME + "square_white.png";
    public static final String GAME_SQUARE_VIOLET = GAME + "square_violet.png";
    public static final String GAME_SQUARE_TRANSPARENT = GAME + "square_transparent.png";
    public static final String GAME_CIRCLE_BLACK = GAME + "circle_black.png";
    public static final String GAME_CIRCLE_GREY = GAME + "circle_grey.png";
    public static final String GAME_CIRCLE_RED = GAME + "circle_red.png";
    public static final String GAME_CIRCLE_VIOLET = GAME + "circle_violet.png";
    public static final String GAME_CIRCLE_TRANSPARENT = GAME + "circle_transparent.png";
    public static final String GAME_TRIANGLE_GREY = GAME + "triangle_grey.png";
    public static final String GAME_TRIANGLE_RED = GAME + "triangle_red.png";
    public static final String GAME_ORB_FRAGMENT_1 = GAME + "orb_fragment_01.mip.png";
    public static final String GAME_ORB_FRAGMENT_2 = GAME + "orb_fragment_02.mip.png";
    public static final String GAME_ORB_FRAGMENT_3 = GAME + "orb_fragment_03.mip.png";
    public static final String GAME_ORB_FRAGMENT_4 = GAME + "orb_fragment_04.mip.png";
    public static final String GAME_GESTURE_BASE = GAME + "gesture_base.mip.png";
    public static final String GAME_GESTURE_LINE = GAME + "gesture_line.mip.png";
    public static final String GAME_GESTURE_ARROWHEAD = GAME + "gesture_arrowhead.mip.png";
    public static final String GAME_PARALLAX_LAYER_1_BLUR = GAME + "parallax_layer_01_blur.png";
    public static final String GAME_PARALLAX_LAYER_2_BLUR = GAME + "parallax_layer_02_blur.png";
    public static final String GAME_PARALLAX_LAYER_3 = GAME + "parallax_layer_03.png";
    public static final String GAME_EXIT = GAME + "exit.png";
    public static final String GAME_UP = GAME + "up.png";
    public static final String GAME_HEAT = GAME + "heat.png";
    public static final String GAME_MAGNETIC_FIELD_RADIAL = GAME + "magnetic_field_radial.png";
    public static final String GAME_MAGNETIC_FIELD_LINEAR = GAME + "magnetic_field_linear.png";
    public static final String GAME_MAGNETIC_PARTICLE = GAME + "magnetic_particle.mip.png";

    public static final String HUD_BACKGROUND_BORDER = HUD + "background_border.png";
    public static final String HUD_BACKGROUND_OVERLAY = HUD + "background_overlay.png";
    public static final String HUD_BUTTON_PAUSE = HUD + "button_pause.png";
    public static final String HUD_BUTTON_RESUME = HUD + "button_resume.png";
    public static final String HUD_GAUGE_BACKGROUND = HUD + "gauge_background.png";
    public static final String HUD_GAUGE_FRAME = HUD + "gauge_frame.png";
    public static final String HUD_GAUGE_BAR = HUD + "gauge_bar.png";
    public static final String HUD_GAUGE_OVERLOAD = HUD + "gauge_overload.png";

    public static final String APP_ICON_256 = APP + "icon_256.png";
    public static final String APP_ICON_128 = APP + "icon_128.png";
    public static final String APP_ICON_64 = APP + "icon_64.png";
    public static final String APP_ICON_32 = APP + "icon_32.png";
    public static final String APP_ICON_16 = APP + "icon_16.png";

    public static final String SOUND_BACK = SOUND + "back.mp3";
    public static final String SOUND_BUTTON = SOUND + "button.mp3";
    public static final String SOUND_ELEMENT_COLLISION = SOUND + "collision_element.mp3";
    public static final String SOUND_WALL_COLLISION = SOUND + "collision_wall.mp3";
    public static final String SOUND_DESTROY = SOUND + "destroy.mp3";
    public static final String SOUND_ERROR = SOUND + "error.mp3";
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
