package com.emberiris.airplanewargame.Model;

import android.graphics.Bitmap;

import com.emberiris.airplanewargame.Utils.Vector2f;

public class Player extends Collider {
    public int health;  //生命值
    public float touchR;
    public int level;
    public int shootIntervel = 20;
    public int bulletSpeed = 35;

    public Player(Bitmap img, Vector2f pos, float colliderR, int health, float touchR, int level) {
        this.img = img;
        this.pos = pos;
        this.colliderR = colliderR;
        this.health = health;
        this.touchR = touchR;
        this.level = level;
    }

    public void HidePlayer() {
        level = 0;  //第0级别不会有任何操作
        pos.x = -1000;  //防止碰撞检测
        pos.y = -1000;
    }

}
