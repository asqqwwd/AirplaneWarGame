package com.emberiris.airplanewargame.Manager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.emberiris.airplanewargame.Model.Bullet;
import com.emberiris.airplanewargame.Model.Effect;
import com.emberiris.airplanewargame.Model.Enemy;
import com.emberiris.airplanewargame.Model.Player;
import com.emberiris.airplanewargame.R;
import com.emberiris.airplanewargame.Utils.BitmapHelper;
import com.emberiris.airplanewargame.Utils.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerManager {
    public Player player;

    private int sceneWidth;
    private int sceneHeight;

    private ArrayList<Bullet> bullets;  //所有子弹
    private ArrayList<Effect> effects;  //所有特效

    //飞机
    private Bitmap[] airplaneBitmaps;
    private Bitmap[] airplaneBitmapsH;

    //子弹
    private Bitmap[] bulletBitmaps;

    //特效
    private Bitmap[] bulletExplosionBitmaps;  //子弹爆炸特效图片
    private Bitmap[] shootFireBitmaps;  //枪焰特效
    private Bitmap[] nuclearExplosionBitmaps;  //核弹情场
    private Bitmap[] missileShootFireBitmaps;  //导弹的特殊枪焰


    public PlayerManager(Activity activity) {
        this.bullets = new ArrayList<>();
        this.effects = new ArrayList<>();

        //飞机
        this.airplaneBitmaps = new Bitmap[3];
        this.airplaneBitmapsH = new Bitmap[3];
        this.airplaneBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship6), 0.05f);
        this.airplaneBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship9), 0.04f);
        this.airplaneBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship0), 0.04f);
        this.airplaneBitmapsH[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship6h), 0.05f);
        this.airplaneBitmapsH[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship9h), 0.04f);
        this.airplaneBitmapsH[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship0h), 0.04f);

        //子弹
        this.bulletBitmaps = new Bitmap[3];
        this.bulletBitmaps[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet7), 0.05f), -90);
        this.bulletBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet18), 0.05f), -90);
        this.bulletBitmaps[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet6), 0.05f), -90);

        //特效
        float scala1 = 0.2f;
        this.bulletExplosionBitmaps = new Bitmap[6];
        bulletExplosionBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.orange_bullet_explo1), scala1);
        bulletExplosionBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.orange_bullet_explo2), scala1);
        bulletExplosionBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.orange_bullet_explo3), scala1);
        bulletExplosionBitmaps[3] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.orange_bullet_explo4), scala1);
        bulletExplosionBitmaps[4] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.orange_bullet_explo5), scala1);
        bulletExplosionBitmaps[5] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.orange_bullet_explo6), scala1);
        float scala2 = 0.05f;
        shootFireBitmaps = new Bitmap[7];  //枪焰特效
        shootFireBitmaps[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0001), scala2), -90);
        shootFireBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0002), scala2), -90);
        shootFireBitmaps[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0003), scala2), -90);
        shootFireBitmaps[3] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0004), scala2), -90);
        shootFireBitmaps[4] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0005), scala2), -90);
        shootFireBitmaps[5] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0006), scala2), -90);
        shootFireBitmaps[6] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.pistol_0007), scala2), -90);
        float scala3 = 1.5f;
        nuclearExplosionBitmaps = new Bitmap[5];
        nuclearExplosionBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.tksj_001), scala3);
        nuclearExplosionBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.tksj_002), scala3);
        nuclearExplosionBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.tksj_003), scala3);
        nuclearExplosionBitmaps[3] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.tksj_004), scala3);
        nuclearExplosionBitmaps[4] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.tksj_005), scala3);
        float scala4 = 0.05f;
        missileShootFireBitmaps = new Bitmap[10];  //导弹枪焰特效
        missileShootFireBitmaps[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0001), scala4), -90);
        missileShootFireBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0002), scala4), -90);
        missileShootFireBitmaps[2] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0003), scala4), -90);
        missileShootFireBitmaps[3] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0004), scala4), -90);
        missileShootFireBitmaps[4] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0005), scala4), -90);
        missileShootFireBitmaps[5] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0006), scala4), -90);
        missileShootFireBitmaps[6] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0007), scala4), -90);
        missileShootFireBitmaps[7] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0008), scala4), -90);
        missileShootFireBitmaps[8] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0009), scala4), -90);
        missileShootFireBitmaps[9] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.fire_0010), scala4), -90);


        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();  //获取屏幕分辨率
        this.sceneWidth = displayMetrics.widthPixels;  //场景宽度等同于屏幕宽度
        this.sceneHeight = displayMetrics.heightPixels;  //场景高度等同于屏幕高度

        //Player数据初始化
        this.player = new Player(airplaneBitmaps[0], new Vector2f((float) sceneWidth / 2, sceneHeight - 200), 100, 100, 300, 1);
    }

    public boolean isInTouchRange(float x, float y) {
        return Math.pow(player.pos.x - x, 2) + Math.pow(player.pos.y - y, 2) <= Math.pow(player.touchR, 2);
    }

    public void Move(float x, float y) {  //根据绝对位置进行移动
        if (player.health <= 0) {
            return;
        }

        if (x < player.img.getWidth() / 2.0f || x > sceneWidth - player.img.getWidth() / 2.0f || y < player.img.getHeight() / 2.0f || y > sceneHeight - player.img.getHeight() / 2.0f) {
            return;
        }
        player.pos.x = x;
        player.pos.y = y;
    }

    public void Shoot(int frame) {
        if (frame % player.shootIntervel == 0) {
            switch (player.level) {
                case 1:
                    bullets.add(new Bullet(new Vector2f(player.pos.x - 60, player.pos.y - 80), new Vector2f(0, -1), player.bulletSpeed, 1));
                    bullets.add(new Bullet(new Vector2f(player.pos.x + 60, player.pos.y - 80), new Vector2f(0, -1), player.bulletSpeed, 1));
                    effects.add(new Effect(new Vector2f(player.pos.x - 60, player.pos.y - 80), 7, 2));
                    effects.add(new Effect(new Vector2f(player.pos.x + 60, player.pos.y - 80), 7, 2));
                    break;
                case 2:
                case 3:
                    bullets.add(new Bullet(new Vector2f(player.pos.x - 5, player.pos.y - 95), new Vector2f(0, -1), player.bulletSpeed, 2));
                    bullets.add(new Bullet(new Vector2f(player.pos.x - 55, player.pos.y - 95), new Vector2f(0, -1), player.bulletSpeed, 2));
                    bullets.add(new Bullet(new Vector2f(player.pos.x + 45, player.pos.y - 95), new Vector2f(0, -1), player.bulletSpeed, 2));

                    effects.add(new Effect(new Vector2f(player.pos.x - 5, player.pos.y - 95), 7, 2));
                    effects.add(new Effect(new Vector2f(player.pos.x - 55, player.pos.y - 95), 7, 2));
                    effects.add(new Effect(new Vector2f(player.pos.x + 45, player.pos.y - 95), 7, 2));
                    break;
                default:
                    break;
            }

        }
        if ((frame % 41 == 0 || frame % 31 == 0) && player.level == 3) {
            bullets.add(new Bullet(new Vector2f(player.pos.x - 105, player.pos.y - 115), new Vector2f(0, -1), player.bulletSpeed, 3));
            bullets.add(new Bullet(new Vector2f(player.pos.x + 105, player.pos.y - 115), new Vector2f(0, -1), player.bulletSpeed, 3));
            effects.add(new Effect(new Vector2f(player.pos.x - 105, player.pos.y - 115), 7, 4));
            effects.add(new Effect(new Vector2f(player.pos.x + 105, player.pos.y - 115), 7, 4));
        }
    }

    //更新所有子弹数据，包含相应碰撞检测
    public void UpdateBullets(BossManager bossManager, EnemyManager enemyManager) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();

            bullet.UpdateBullet();
            if (bullet.pos.x < 0 || bullet.pos.x > sceneWidth || bullet.pos.y < 0 || bullet.pos.y > sceneHeight) {
                iterator.remove();  //最为安全的删除方式，超过边界，删除此子弹
                continue;
            }

            //检查是否打中Boss
            if (bossManager.boss.isInColliderRange(bullet.pos)) {  //打中敌人，扣血并且播放爆炸特效
                bossManager.getDamage(1);
                effects.add(new Effect(new Vector2f(bullet.pos.x, bullet.pos.y), 6, 1));  //添加爆炸效果
                iterator.remove();
                continue;
            }

            //检查是否打中敌人
            for (Enemy enemy : enemyManager.enemys) {
                if (enemy.isInColliderRange(bullet.pos)) {  //打中敌人，扣血并且播放爆炸特效
                    if (bullet.type == 3) {
                        enemyManager.getDamage(enemy, 3);
                    } else {
                        enemyManager.getDamage(enemy, 1);
                    }
                    effects.add(new Effect(new Vector2f(bullet.pos.x, bullet.pos.y), 6, 1));  //添加爆炸效果
                    iterator.remove();
                    break;
                }
            }

        }
    }

    //根据位置数据绘制飞机和子弹和特效
    public void Draw(Canvas canvas) {
        //绘制人物血条
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.RED);
        canvas.drawRect(80, 100, 100, 400, paint2);

        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        canvas.drawRect(80, 100, 100, 100 + player.health * 3, paint1);


        //绘制飞机
        if (player.level == 0) {
            return;
        }
        DrawImg(canvas, player.img, player.pos);

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
                case 2:  //枪焰特效
                    DrawImg(canvas, shootFireBitmaps[effect.count], effect.pos);
                    break;
                case 3:  //核爆特效
                    DrawImg(canvas, nuclearExplosionBitmaps[effect.count], effect.pos);
                    break;
                case 4:  //导弹枪焰特效
                    DrawImg(canvas, missileShootFireBitmaps[effect.count], effect.pos);
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

    public void Restore() {
        if (player.level == 0) {
            return;
        }
        player.img = airplaneBitmaps[player.level - 1];  //恢复正常状态
    }

    public void getDamage(int damage) {
        if (player.level == 0) {
            return;
        }

        if (player.health > 0) {
            player.health -= damage;
            player.img = airplaneBitmapsH[player.level - 1];

        } else {

            //玩家死亡
            player.level = 0;  //第0级别不会有任何操作
            bullets.clear();
            effects.clear();
            //effects.add(new Effect(new Vector2f(pos.x, pos.y), 11, 4));
            player.pos.x = -1000;  //防止碰撞检测
            player.pos.y = -1000;
        }
    }

    public void AddHealth() {
        player.health = 100;
    }


    //核爆
    public void NuclearExplosion() {
        effects.add(new Effect(new Vector2f(player.pos.x, player.pos.y), 5, 3));
    }

    //无敌模式
    public void SuperMode() {

    }

    public void SpeedUp() {
        if (player.shootIntervel >= 8) {
            player.shootIntervel -= 4;
        }
        if (player.bulletSpeed < 100) {
            player.bulletSpeed += 15;
        }
    }

    //升级
    public void LevelUp() {
        if (player.level <= 2) {
            player.level++;

        }
    }

    //将图像对齐到重心画出
    private void DrawImg(Canvas canvas, Bitmap img, Vector2f pos) {
        canvas.drawBitmap(img, pos.x - (float) img.getWidth() / 2, pos.y - (float) img.getHeight() / 2, null);
    }

}
