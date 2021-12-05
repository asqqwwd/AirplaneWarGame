package com.emberiris.airplanewargame.Manager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import com.emberiris.airplanewargame.Model.Bullet;
import com.emberiris.airplanewargame.Model.Effect;
import com.emberiris.airplanewargame.Model.Enemy;
import com.emberiris.airplanewargame.Model.Prop;
import com.emberiris.airplanewargame.R;
import com.emberiris.airplanewargame.Utils.BitmapHelper;
import com.emberiris.airplanewargame.Utils.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class EnemyManager {
    public ArrayList<Enemy> enemys;  //所有敌人
    private ArrayList<Prop> props;  //所有道具
    private ArrayList<Bullet> bullets;  //所有子弹
    private ArrayList<Effect> effects;  //所有特效

    private int sceneWidth;
    private int sceneHeight;
    private int difficult = 233;

    //飞机
    private Bitmap[] airplaneBitmaps;
    private Bitmap[] airplaneBitmapsH;

    //道具
    private Bitmap[] propBitmaps;  //道具图片

    //子弹
    private Bitmap[] bulletBitmaps;

    //特效
    private Bitmap[] bulletExplosionBitmaps;  //子弹爆炸特效图片
    private Bitmap[] airplaneExplosionBitmaps;  //飞机爆炸特效

    public EnemyManager(Activity activity) {
        this.enemys = new ArrayList<>();
        this.props = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.effects = new ArrayList<>();

        //飞机位图加载
        this.airplaneBitmaps = new Bitmap[2];
        this.airplaneBitmapsH = new Bitmap[2];
        this.airplaneBitmaps[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship5), 0.04f), 180);
        this.airplaneBitmaps[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship19), 0.06f), 180);
        this.airplaneBitmapsH[0] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship5h), 0.04f), 180);
        this.airplaneBitmapsH[1] = BitmapHelper.RotateBitmap(BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ship19h), 0.06f), 180);

        //道具位图加载
        float scala1 = 0.06f;
        this.propBitmaps = new Bitmap[5];
        propBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.prop1), scala1);
        propBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.prop2), scala1);
        propBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.prop3), scala1);
        propBitmaps[3] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.prop4), scala1);
        propBitmaps[4] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.prop5), scala1);


        //子弹位图加载
        this.bulletBitmaps = new Bitmap[2];
        this.bulletBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet14), 0.1f);
        this.bulletBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.bullet20), 0.1f);

        //特效位图
        float scala2 = 0.2f;
        this.bulletExplosionBitmaps = new Bitmap[6];  //子弹爆炸特效
        bulletExplosionBitmaps[0] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo1), scala2);
        bulletExplosionBitmaps[1] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo2), scala2);
        bulletExplosionBitmaps[2] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo3), scala2);
        bulletExplosionBitmaps[3] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo4), scala2);
        bulletExplosionBitmaps[4] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo5), scala2);
        bulletExplosionBitmaps[5] = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.blue_bullet_explo6), scala2);
        float scala3 = 0.2f;
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
    }

    public void EnemyRandomGenerate(int frame) {
        Random random = new Random();
        if (frame % difficult == 0 || frame % (difficult + 33) == 0) {
            //随机方向
            float randomAngle = random.nextInt(10) * 10;
            //随机位置
            int randomPos = random.nextInt(11);  //十个位置
            //随机飞机
            int randomPlane = random.nextInt(2);
            int randomSide = random.nextInt(3);

            Bitmap img = airplaneBitmaps[randomPlane];

            //随机边
            if (randomSide == 0) {  //出现在上方
                enemys.add(new Enemy(img, new Vector2f(sceneWidth * randomPos / 20.0f + sceneWidth / 4.0f, -img.getHeight()), new Vector2f(Math.cos(Math.toRadians(randomAngle + 45)), Math.sin(Math.toRadians(randomAngle + 45))), 100, 3 + randomPlane * 3, 5, randomPlane + 1));
            } else if (randomSide == 1) {  //左侧
                enemys.add(new Enemy(img, new Vector2f(-img.getWidth(), sceneHeight * randomPos / 20.0f + sceneHeight / 4.0f), new Vector2f(Math.cos(Math.toRadians(randomAngle - 45)), Math.sin(Math.toRadians(randomAngle - 45))), 100, 3 + randomPlane * 3, 5, randomPlane + 1));
            } else {  //右侧
                enemys.add(new Enemy(img, new Vector2f(img.getWidth() + sceneWidth, sceneHeight * randomPos / 20.0f + sceneHeight / 4.0f), new Vector2f(Math.cos(Math.toRadians(randomAngle + 135)), Math.sin(Math.toRadians(randomAngle + 135))), 100, 3 + randomPlane * 3, 5, randomPlane + 1));
            }
        }

    }

    public void EnemyMove(PlayerManager playerManager) {
        Iterator<Enemy> iterator = enemys.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();

            enemy.UpdateEnemy();
            if (enemy.pos.x < -enemy.img.getWidth() || enemy.pos.x > sceneWidth + enemy.img.getWidth() || enemy.pos.y < -enemy.img.getHeight() || enemy.pos.y > sceneHeight + enemy.img.getHeight()) {
                iterator.remove();  //飞机飞出边界将其删除
                continue;
            }

            if (playerManager.player.isInColliderRange(enemy.pos)) {
                playerManager.getDamage(10);  //撞到飞机直接扣除10点血
                effects.add(new Effect(new Vector2f(enemy.pos.x, enemy.pos.y), 11, 2));
                iterator.remove();
            }
        }
    }

    public void EnemyShoot(int frame, PlayerManager playerManager) {
        for (Enemy enemy : enemys) {
            if (frame % 100 == 0 || frame % 133 == 0) {
                float xd = playerManager.player.pos.x - enemy.pos.x;
                float yd = playerManager.player.pos.y - enemy.pos.y;
                bullets.add(new Bullet(new Vector2f(enemy.pos.x, enemy.pos.y), new Vector2f(xd / Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2)), yd / Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2))), 6, 1));
            }
        }
    }

    public void UpdateBullets(PlayerManager playerManager) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();


            bullet.UpdateBullet();
            if (bullet.pos.x < 0 || bullet.pos.x > sceneWidth || bullet.pos.y < 0 || bullet.pos.y > sceneHeight) {
                iterator.remove();  //最为安全的删除方式，超过边界，删除此子弹
                continue;
            }

            if (playerManager.player.isInColliderRange(bullet.pos)) {  //打中敌人，扣血并且播放爆炸特效
                playerManager.getDamage(1);
                effects.add(new Effect(new Vector2f(bullet.pos.x, bullet.pos.y), 6, 1));  //添加子弹爆炸效果
                iterator.remove();
            }
        }
    }

    public void PropGenerate(Vector2f pos) {
        Random random = new Random();
        if (random.nextInt(4) == 0) {
            props.add(new Prop(pos, new Vector2f(0, 1), 10, random.nextInt(5) + 1));
        }
    }

    public void UpdateProps(PlayerManager playerManager, BossManager bossManager) {
        Iterator<Prop> iterator = props.iterator();
        while (iterator.hasNext()) {
            Prop prop = iterator.next();
            prop.UpdateProp();

            if (prop.pos.y < 0 || prop.pos.y > sceneHeight) {
                iterator.remove();  //最为安全的删除方式，超过边界，删除此子弹
                continue;
            }

            if (playerManager.player.isInColliderRange(prop.pos)) {  //玩家接到道具
                switch (prop.type) {
                    case 1:
                        playerManager.AddHealth();
                        break;
                    case 2:
                        bossManager.ClearBullet();
                        for (Enemy enemy : enemys) {
                            effects.add(new Effect(new Vector2f(enemy.pos.x, enemy.pos.y), 11, 2));
                        }
                        enemys.clear();
                        bullets.clear();
                        playerManager.NuclearExplosion();
                        break;
                    case 3:
                        playerManager.SpeedUp();
                        //playerManager.SuperMode();
                        break;
                    case 4:
                        playerManager.SpeedUp();
                        break;
                    case 5:
                        playerManager.LevelUp();
                        break;
                    default:
                        break;
                }
                iterator.remove();
            }
        }
    }

    //根据位置数据绘制飞机和子弹和特效
    public void Draw(Canvas canvas) {
        //绘制飞机
        for (Enemy enemy : enemys) {
            DrawImg(canvas, enemy.img, enemy.pos);
        }

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
                case 2:  //飞机爆炸特效
                    DrawImg(canvas, airplaneExplosionBitmaps[effect.count], effect.pos);
                    break;
                default:
                    break;
            }

            effect.count++;
            //特效播放完毕，将其删除
            if (effect.count == effect.maxCount) {
                if (effect.type == 2) {  //如果是飞机爆炸，则随机生成道具
                    PropGenerate(new Vector2f(effect.pos.x, effect.pos.y));
                }
                iterator.remove();

            }
        }

        //绘制道具
        for (Prop prop : props) {
            DrawImg(canvas, propBitmaps[prop.type - 1], prop.pos);
        }
    }

    public void Restore() {
        for (Enemy enemy : enemys) {
            enemy.img = airplaneBitmaps[enemy.type - 1];
        }
    }

    public void UpDifficult() {
        if (difficult > 66) {
            difficult -= 66;
        }
    }

    //将图像对齐到重心画出
    private void DrawImg(Canvas canvas, Bitmap img, Vector2f pos) {
        canvas.drawBitmap(img, pos.x - (float) img.getWidth() / 2, pos.y - (float) img.getHeight() / 2, null);
    }

    public void getDamage(Enemy enemy, int damage) {
        if (enemy.health > 0) {
            enemy.health -= damage;

            enemy.img = airplaneBitmapsH[enemy.type - 1];
        } else {
            effects.add(new Effect(new Vector2f(enemy.pos.x, enemy.pos.y), 11, 2));
            enemys.remove(enemy);
        }
    }
}
