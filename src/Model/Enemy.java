package com.emberiris.airplanewargame.Model;

import android.graphics.Bitmap;

import com.emberiris.airplanewargame.Utils.Vector2f;

public class Enemy extends Collider {
    private Vector2f direct;  //移动方向
    public int health;  //生命值
    public float speed;  //移动速度
    public int type;  //敌人类型

    public Enemy(Bitmap img, Vector2f pos, Vector2f direct, float colliderR, int health, float speed, int type) {
        this.img = img;
        this.pos = pos;
        this.direct = direct;
        this.colliderR = colliderR;
        this.health = health;
        this.speed = speed;
        this.type = type;
    }

    public void UpdateEnemy() {
        pos.x += direct.x * speed;
        pos.y += direct.y * speed;
    }

}
