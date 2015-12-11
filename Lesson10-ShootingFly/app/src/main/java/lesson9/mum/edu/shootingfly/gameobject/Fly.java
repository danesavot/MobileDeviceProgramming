package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import lesson9.mum.edu.shootingfly.R;
import lesson9.mum.edu.shootingfly.gameengine.GameEngine;

/**
 * Created by 984391 on 12/9/2015.
 */
public class Fly extends GameObject {


    Bitmap fly;
    GameEngine gameEngine;
    public Fly(GameEngine gameEngine) {
        this.gameEngine= gameEngine;

    }
    @Override
    public boolean handleTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onInit() {

        fly = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.fly);
        fly = Bitmap.createScaledBitmap(fly, getWidth(), getHeight(), false);
        bitmapX = (gameEngine.gameView.getWidth()-getWidth())/2;
        bitmapY = 100;
    }

    @Override
    public void onUpdate(long elapsedMillis) {

    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(fly,bitmapX,bitmapY,null);
    }
}
