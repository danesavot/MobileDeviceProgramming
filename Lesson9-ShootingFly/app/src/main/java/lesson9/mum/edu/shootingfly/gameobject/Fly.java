package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import lesson9.mum.edu.shootingfly.GameEngine;
import lesson9.mum.edu.shootingfly.R;

/**
 * Created by 984391 on 12/9/2015.
 */
public class Fly extends GameObject {
    float bitmapX, bitmapY;
    private int width =100;
    private int height =100;

    Bitmap fly;
    GameEngine gameEngine;
    public Fly(GameEngine gameEngine) {
        this.gameEngine= gameEngine;

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public float getX() {
        return bitmapX;
    }

    @Override
    public float getY() {
        return bitmapY;
    }

    @Override
    public void setX(float x) {
        bitmapX = x;
    }

    @Override
    public void setY(float y) {
        bitmapY = y;
    }

    @Override
    public void onInit() {

        fly = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.fly);
        fly = Bitmap.createScaledBitmap(fly, width, height, false);
        bitmapX = (gameEngine.gameView.getWidth()-width)/2;
        bitmapY = 100;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {

    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(fly,bitmapX,bitmapY,null);
    }
}
