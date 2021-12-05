package com.emberiris.airplanewargame.Model;

import com.emberiris.airplanewargame.Utils.Vector2f;

public class Prop extends Collider {
    private Vector2f direct;
    private float speed;
    public int type;  //道具类型

    public Prop(Vector2f initPos, Vector2f initDirect, float speed, int type) {
        this.pos = initPos;
        this.direct = initDirect;
        this.speed = speed;
        this.type = type;
    }

    public void UpdateProp() {
        pos.x += direct.x * speed;
        pos.y += direct.y * speed;
    }
}
