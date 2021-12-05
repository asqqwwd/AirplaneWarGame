package com.emberiris.airplanewargame.Model;

import com.emberiris.airplanewargame.Utils.Vector2f;

public class Bullet  extends FreePoint{
    private Vector2f direct;
    private float speed;
    public int type;  //子弹类型

    public Bullet(Vector2f initPos, Vector2f initDirect, float speed, int type) {
        this.pos = initPos;
        this.direct = initDirect;
        this.speed = speed;
        this.type = type;
    }

    public void UpdateBullet() {
        pos.x += direct.x * speed;
        pos.y += direct.y * speed;
    }
}
