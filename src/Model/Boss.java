package com.emberiris.airplanewargame.Model;

import android.graphics.Bitmap;

import com.emberiris.airplanewargame.Utils.Vector2f;

public class Boss extends Collider {
    public int health;  //生命值
    public float speed;  //移动速度
    public int level;

    public Boss(Bitmap img, Vector2f pos, float colliderR, int health, float speed, int level) {
        this.img = img;
        this.pos = pos;
        this.colliderR = colliderR;
        this.health = health;
        this.speed = speed;
        this.level = level;
    }


    public void HideBoss() {
        colliderR = 0;
        pos.x = -1000;  //防止碰撞检测
        pos.y = -1000;
    }

}
