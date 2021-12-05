package com.emberiris.airplanewargame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.emberiris.airplanewargame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*组件绑定*/
        Button start_game_button = findViewById(R.id.start_game);
        Button rank_list_button = findViewById(R.id.rank_list);
        Button instruction_button = findViewById(R.id.instruction);
        Button exit_game_button = findViewById(R.id.exit_game);

        /*监听绑定*/
        start_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartGameActivity();
            }
        });

        exit_game_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });

    }


    private void StartGameActivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivityForResult(intent, 0);
    }
}
