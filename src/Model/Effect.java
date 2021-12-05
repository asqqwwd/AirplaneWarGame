package com.emberiris.airplanewargame.Model;

import com.emberiris.airplanewargame.Utils.Vector2f;

public class Effect extends FreePoint {
    public int count;  //特效当前帧数
    public int maxCount;  //特效最大帧数
    public int type;  //特效类型

    public Effect(Vector2f pos, int maxCount, int type) {
        this.pos = pos;
        this.maxCount = maxCount;
        this.type = type;
    }
}
