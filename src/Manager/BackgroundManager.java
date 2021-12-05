package com.emberiris.airplanewargame.Manager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;

import com.emberiris.airplanewargame.R;
import com.emberiris.airplanewargame.Utils.BitmapHelper;

public class BackgroundManager {
    private int speed = 3;

    private Bitmap background1;
    private Bitmap background2;
    private float anchorY1;
    private float anchorY2;

    private int sceneWidth;
    private int sceneHeight;

    public BackgroundManager(Activity activity) {
        this.background1 = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.backgroud1), 0.3f);
        this.background2 = BitmapHelper.ScaleBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.backgroud2), 0.3f);

        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();  //获取屏幕分辨率
        this.sceneWidth = displayMetrics.widthPixels;
        this.sceneHeight = displayMetrics.heightPixels;

        anchorY1 = -background1.getHeight();
        anchorY2 = 0;
    }

    public void UpdateBackground() {
        anchorY1 += speed;
        anchorY2 += speed;
        if (anchorY2 >= sceneHeight) {
            anchorY2 = anchorY1 - background2.getHeight();
        } else if (anchorY1 > sceneHeight) {
            anchorY1 = anchorY2 - background1.getHeight();
        }
    }

    public void Draw(Canvas canvas) {
        canvas.drawBitmap(background1, 0, anchorY1, null);
        canvas.drawBitmap(background2, 0, anchorY2, null);
    }
}
