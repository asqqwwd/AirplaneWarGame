package com.emberiris.airplanewargame.Scene;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.emberiris.airplanewargame.Manager.BackgroundManager;
import com.emberiris.airplanewargame.Manager.BossManager;
import com.emberiris.airplanewargame.Manager.EnemyManager;
import com.emberiris.airplanewargame.Manager.PlayerManager;
import com.emberiris.airplanewargame.R;
import com.emberiris.airplanewargame.Utils.BitmapHelper;
import com.emberiris.airplanewargame.Utils.Vector2f;

public class GameScene extends View {
    boolean isIngame = true;
    int frame = 0;
    Vector2f touchAnchor = new Vector2f();
    Vector2f airplaneAnchor = new Vector2f();
    boolean inTouchRangeFlag = false;

    PlayerManager playerManager;
    BossManager bossManager;
    BackgroundManager backgroundManager;
    EnemyManager enemyManager;

    public GameScene(Activity activity) {
        super(activity);

        playerManager = new PlayerManager(activity);
        backgroundManager = new BackgroundManager(activity);
        enemyManager = new EnemyManager(activity);
        bossManager = new BossManager(activity, enemyManager);

        //监听触屏，用于更新玩家位置
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)  //点击
                {
                    if (playerManager.isInTouchRange(event.getX(), event.getY())) {
                        touchAnchor.x = event.getX();
                        touchAnchor.y = event.getY();
                        airplaneAnchor.x = playerManager.player.pos.x;
                        airplaneAnchor.y = playerManager.player.pos.y;

                        inTouchRangeFlag = true;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_MOVE)  //滑动
                {
                    if (inTouchRangeFlag) {
                        playerManager.Move(airplaneAnchor.x + event.getX() - touchAnchor.x, airplaneAnchor.y + event.getY() - touchAnchor.y);  //玩家移动
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    inTouchRangeFlag = false;
                }

                return true;
            }
        });

        new Thread(new RefreshFrame()).start();
    }

    //每帧刷新屏幕
    private class RefreshFrame implements Runnable {
        @Override
        public void run() {
            while (isIngame) {
                try {
                    Thread.sleep(20);  //每秒50帧
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate();  //调用Ondraw()函数进行刷新，可以直接放在线程中
                //invalidate();  //此函数不能放在线程中，只能使用线程sendmsg，handler中加invalidate()进行刷新
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //帧数增加
        frame++;
        backgroundManager.UpdateBackground();
        backgroundManager.Draw(canvas);

        //player.Move();  //由屏幕点击实现
        playerManager.Shoot(frame);  //定期射击
        playerManager.UpdateBullets(bossManager, enemyManager);  //即使没有子弹射出也要更新数据
        playerManager.Draw(canvas);
        playerManager.Restore();  //恢复正常状态

        bossManager.RandomMove(frame);
        bossManager.Shoot(frame);
        bossManager.UpdateBullets(playerManager);
        bossManager.Draw(canvas);
        bossManager.Restore();  //恢复正常状态

        enemyManager.EnemyRandomGenerate(frame);
        enemyManager.EnemyMove(playerManager);
        enemyManager.EnemyShoot(frame, playerManager);
        enemyManager.UpdateBullets(playerManager);
        enemyManager.UpdateProps(playerManager, bossManager);
        enemyManager.Draw(canvas);
        enemyManager.Restore();
    }
}
