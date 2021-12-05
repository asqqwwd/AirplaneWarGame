package com.emberiris.airplanewargame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.emberiris.airplanewargame.Scene.GameScene;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置游戏场景
        setContentView(new GameScene(this));
    }
}
