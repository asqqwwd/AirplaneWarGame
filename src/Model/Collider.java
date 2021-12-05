package com.emberiris.airplanewargame.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.emberiris.airplanewargame.Utils.Vector2f;

class Collider {
    public Bitmap img;  //碰撞体图片
    public Vector2f pos;  //碰撞体位置
    public float colliderR;  //碰撞体半径

    //是否在碰撞体范围内
    public boolean isInColliderRange(Vector2f p) {
        return Math.pow(pos.x - p.x, 2) + Math.pow(pos.y - p.y, 2) <= Math.pow(colliderR, 2);
    }


}
