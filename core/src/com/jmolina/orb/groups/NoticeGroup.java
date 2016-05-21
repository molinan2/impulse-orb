package com.jmolina.orb.groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

public class NoticeGroup extends BaseGroup implements Disposable {

    private Image notice;
    private Texture noticeTexture;

    public NoticeGroup() {
        noticeTexture = new Texture(Gdx.files.internal("notice.png"));
        notice = new Image(noticeTexture);
        notice.setPosition(0f, 0f);
        addActor(notice);
    }

    @Override
    public void dispose() {
        noticeTexture.dispose();
    }
}
