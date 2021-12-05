package com.emberiris.airplanewargame.Utils;

public class Vector2f {
    public float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(int x, int y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public Vector2f(double x,double y){
        this.x = (float)x;
        this.y = (float)y;
    }

    public Vector2f() {
        this.x = -1000;
        this.y = -1000;
    }


}
