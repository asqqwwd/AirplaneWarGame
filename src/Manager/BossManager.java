package com.emberiris.airplanewargame.Manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

import com.emberiris.airplanewargame.Activity.GameActivity;
import com.emberiris.airplanewargame.Activity.MainActivity;
import com.emberiris.airplanewargame.Model.Boss;
import com.emberiris.airplanewargame.Model.Bullet;
import com.emberiris.airplanewargame.Model.Effect;
import com.emberiris.airplanewargame.R;
import com.emberiris.airplanewargame.Utils.BitmapHelper;
import com.emberiris.airplanewargame.Utils.Vector2f;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class BossManager {
    public Boss boss;
    private boolean isInMove = false;
    private Vector2f direct = new Vector2f();  //随机移动向量

    private int sceneWidth;
    private int sceneHeight;
    private EnemyManager enemyManager;
    private Activity activity;

    private ArrayList<Bullet> bullets;  //所有子弹
    private ArrayList<Effect> effects;  //所有特效

    //飞机
    private Bitmap[] airplaneBitmaps;
    private Bitmap[] airplaneBitmapsH;

    //子弹
    private Bitmap[] bulletBitmaps;

    //特效
    private Bitmap[] bulletExplosionBitmaps;  //子弹爆炸特效
    private Bitmap[] shootFireBitmaps;  //枪焰特效
    private Bitmap[] airplaneExplosionBitmaps;  //飞机爆炸特效

    public BossManager(Activity activity, EnemyManager enemyManager) {
        this.activity = activity;
        this.enemyManager = enemyManager;

        this.bullets = new ArrayList<>();
        this.effects = new ArrayList<>();

        //飞机
        this.airplaneBitmaps = new Bitmap[3];
        this.airplaneBitmapsH = new Bitmap[3];
        this.airplaneBitmaps[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship26), 0.12f), 180);
        this.airplaneBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship10), 0.15f), 180);
        this.airplaneBitmaps[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship23), 0.15f), 180);
        this.airplaneBitmapsH[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship26h), 0.12f), 180);
        this.airplaneBitmapsH[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship10h), 0.15f), 180);
        this.airplaneBitmapsH[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship23h), 0.15f), 180);

        //子弹
        this.bulletBitmaps = new Bitmap[3];
        this.bulletBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet16), 0.16f);
        this.bulletBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet19), 0.12f), -90);
        this.bulletBitmaps[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet11), 0.12f), -90);


        //特效
        float scala1 = 0.2f;
        this.bulletExplosionBitmaps = new Bitmap[6];  //子弹爆炸特效
        bulletExplosionBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo1), scala1);
        bulletExplosionBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo2), scala1);
        bulletExplosionBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo3), scala1);
        bulletExplosionBitmaps[3] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo4), scala1);
        bulletExplosionBitmaps[4] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo5), scala1);
        bulletExplosionBitmaps[5] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo6), scala1);
        float scala2 = 0.2f;
        shootFireBitmaps = new Bitmap[12];  //枪焰特效
        shootFireBitmaps[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0001), scala2), 90);
        shootFireBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0002), scala2), 90);
        shootFireBitmaps[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0003), scala2), 90);
        shootFireBitmaps[3] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0004), scala2), 90);
        shootFireBitmaps[4] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0005), scala2), 90);
        shootFireBitmaps[5] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0006), scala2), 90);
        shootFireBitmaps[6] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0007), scala2), 90);
        shootFireBitmaps[7] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0008), scala2), 90);
        shootFireBitmaps[8] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0009), scala2), 90);
        shootFireBitmaps[9] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0010), scala2), 90);
        shootFireBitmaps[10] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0011), scala2), 90);
        shootFireBitmaps[11] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.plazma_0012), scala2), 90);
        float scala3 = 0.8f;
        airplaneExplosionBitmaps = new Bitmap[11];
        airplaneExplosionBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha10), scala3);
        airplaneExplosionBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha11), scala3);
        airplaneExplosionBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha12), scala3);
        airplaneExplosionBitmaps[3] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha13), scala3);
        airplaneExplosionBitmaps[4] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha14), scala3);
        airplaneExplosionBitmaps[5] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha15), scala3);
        airplaneExplosionBitmaps[6] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha16), scala3);
        airplaneExplosionBitmaps[7] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha17), scala3);
        airplaneExplosionBitmaps[8] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha18), scala3);
        airplaneExplosionBitmaps[9] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha19), scala3);
        airplaneExplosionBitmaps[10] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.guaiwubaozha20), scala3);

        //基本属性初始化
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();  //获取屏幕分辨率
        this.sceneWidth = displayMetrics.widthPixels;  //场景宽度等同于屏幕宽度
        this.sceneHeight = displayMetrics.heightPixels;  //场景高度等同于屏幕高度

        //Boss数据初始化
        this.boss = new Boss(airplaneBitmaps[0], new Vector2f(-1000, -1000), 200, -1, 6, 0);
        boss.HideBoss();
    }

    public void RandomMove(int frame) {
        if (boss.health <= 0 && frame % 1400 == 0) {
            LevelUpAndDisplay();  //升级并且重新显示Boss
        }

        if (boss.health <= 0) {
            return;
        }

        if (!isInMove) {
            Random rand = new Random();
            int randomDirect = rand.nextInt(25);
            direct.x = (float) Math.cos(Math.toRadians(randomDirect * 15.0f));
            direct.y = (float) Math.sin(Math.toRadians(randomDirect * 15.0f));
            isInMove = true;
        } else {
            float tmpX = boss.pos.x + direct.x * boss.speed;
            float tmpY = boss.pos.y + direct.y * boss.speed;
            if (tmpX < boss.img.getWidth() / 2.0f || tmpX > sceneWidth - boss.img.getWidth() / 2.0f || tmpY < boss.img.getHeight() / 2.0f || tmpY > sceneHeight / 3.0f) {
                isInMove = false;
                return;
            }
            boss.pos.x = tmpX;
            boss.pos.y = tmpY;
        }
    }

    public void Shoot(int frame) {
        if (boss.health <= 0) {
            return;
        }

        switch (boss.level) {
            case 1:
                if (frame % 120 == 0) {
                    for (int i = 0; i < 360; i += 15) {
                        bullets.add(new Bullet(new Vector2f(boss.pos.x + 120, boss.pos.y + 100), new Vector2f(Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i))), 10, 1));
                    }
                    effects.add(new Effect(new Vector2f(boss.pos.x + 120, boss.pos.y + 100), 12, 2));
                } else if (frame % 160 == 0) {
                    for (int i = 0; i < 360; i += 15) {
                        bullets.add(new Bullet(new Vector2f(boss.pos.x - 120, boss.pos.y + 100), new Vector2f(Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i))), 10, 1));
                    }
                    effects.add(new Effect(new Vector2f(boss.pos.x - 120, boss.pos.y + 100), 12, 2));
                }
                break;
            case 2:
                if (frame % 30 == 0) {
                    for (int i = 0; i < 360; i += 15) {
                        bullets.add(new Bullet(new Vector2f(boss.pos.x, boss.pos.y + 100), new Vector2f(Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i))), 15, 2));
                    }
                    effects.add(new Effect(new Vector2f(boss.pos.x, boss.pos.y + 100), 12, 2));
                }
                if (frame % 233 == 0) {
                    for (int i = 0; i < 360; i += 15) {
                        bullets.add(new Bullet(new Vector2f(boss.pos.x, boss.pos.y + 100), new Vector2f(Math.cos(Math.toRadians(i)), Math.sin(Math.toRadians(i))), 10, 1));
                    }
                    effects.add(new Effect(new Vector2f(boss.pos.x, boss.pos.y + 100), 12, 2));
                }
                break;
            case 3:

                break;
            default:
                break;
        }
    }

    //更新所有子弹数据，包含相应碰撞检测
    public void UpdateBullets(PlayerManager playerManager) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();


            bullet.UpdateBullet();
            if (bullet.pos.x < 0 || bullet.pos.x > sceneWidth || bullet.pos.y < 0 || bullet.pos.y > sceneHeight) {
                iterator.remove();  //最为安全的删除方式，超过边界，删除此子弹
            }

            if (playerManager.player.isInColliderRange(bullet.pos)) {  //打中敌人，扣血并且播放爆炸特效
                playerManager.getDamage(1);
                effects.add(new Effect(new Vector2f(bullet.pos.x, bullet.pos.y), 6, 1));  //添加爆炸效果
                iterator.remove();
            }
        }
    }


    //根据位置数据绘制飞机和子弹和特效
    public void Draw(Canvas canvas) {
        //绘制飞机
        DrawImg(canvas, boss.img, boss.pos);

        //绘制子弹
        for (Bullet bullet : bullets) {
            DrawImg(canvas, bulletBitmaps[bullet.type - 1], bullet.pos);
        }

        //绘制特效
        Iterator<Effect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();

            switch (effect.type) {
                case 1:  //子弹爆炸特效
                    DrawImg(canvas, bulletExplosionBitmaps[effect.count], effect.pos);
                    break;
                case 2:  //子弹发出时的特效
                    DrawImg(canvas, shootFireBitmaps[effect.count], effect.pos);
                    break;
                case 4:  //飞机爆炸特效
                    DrawImg(canvas, airplaneExplosionBitmaps[effect.count], effect.pos);
                    break;
                default:
                    break;
            }

            effect.count++;
            //特效播放完毕，将其删除
            if (effect.count == effect.maxCount) {
                iterator.remove();
            }
        }
    }


    public void getDamage(int damage) {
        if (boss.health > 0) {
            boss.health -= damage;

            boss.img = airplaneBitmapsH[boss.level - 1];
        } else {
            if (bullets.size() == 0) {
                return;
            }
            bullets.clear();
            effects.clear();
            effects.add(new Effect(new Vector2f(boss.pos.x, boss.pos.y), 11, 4));
            boss.HideBoss();  //Boss死亡，暂时将其隐藏起来

            if (boss.level == 2) {
                Dialog successDialog = new AlertDialog.Builder(activity)
                        .setTitle("胜利")
                        .setPositiveButton("回到主菜单", new DialogInterface.OnClickListener() {//添加重新开始按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent_restart = new Intent(activity, MainActivity.class);

                                activity.finish();
                                activity.startActivity(intent_restart);

                            }
                        })
                        .setNegativeButton("重新开始", new DialogInterface.OnClickListener() {  //添加按钮
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent mainIntent = new Intent(activity, GameActivity.class);

                                activity.finish();
                                activity.startActivity(mainIntent);

                            }
                        }).setCancelable(false)
                        .create();
                successDialog.show();
            }
        }
    }

    public void Restore() {
        if (boss.health <= 0) {
            return;
        }
        boss.img = airplaneBitmaps[boss.level - 1];
    }

    public void ClearBullet() {
        bullets.clear();
    }

    public void LevelUpAndDisplay() {
        if (boss.level <= 1) {
            boss.level += 1;
            boss.health = boss.level * 150;
            boss.colliderR = 200;

            isInMove = true;
            direct.x = 0;
            direct.y = 1;
            boss.img = airplaneBitmaps[boss.level - 1];
            boss.pos.x = sceneWidth / 2.0f;
            boss.pos.y = boss.img.getHeight() / 2.0f + 5;

            //增加刷怪难度
            enemyManager.UpDifficult();
        }
    }

    private boolean isInSceneRange(Vector2f pos) {
        return !(pos.x < 0) && !(pos.x > sceneWidth) && !(pos.y < 0) && !(pos.y > sceneHeight);
    }

    //将图像对齐到重心画出
    private void DrawImg(Canvas canvas, Bitmap img, Vector2f pos) {
        canvas.drawBitmap(img, pos.x - (float) img.getWidth() / 2, pos.y - (float) img.getHeight() / 2, null);
    }
}
