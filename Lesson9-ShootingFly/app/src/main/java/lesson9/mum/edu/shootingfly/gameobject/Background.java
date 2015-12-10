package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import lesson9.mum.edu.shootingfly.GameEngine;
import lesson9.mum.edu.shootingfly.R;

/**
 * Created by 984391 on 12/9/2015.
 */
public class Background extends GameObject {

    float bitmapX, bitmapY;

    Bitmap background;
    GameEngine gameEngine;

    public Background(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public void setX(float x) {

    }

    @Override
    public void setY(float y) {

    }

    @Override
    public void onInit() {

        background = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(background, gameEngine.gameView.getWidth(), gameEngine.gameView.getHeight(), false);


    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(background,0,0,null);

    }
}
