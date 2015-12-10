package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import lesson9.mum.edu.shootingfly.GameEngine;
import lesson9.mum.edu.shootingfly.R;

/**
 * Created by noname on 12/9/2015.
 */
public class Bullet extends GameObject {

    private int width =50;
    private int height =50;
    Bitmap bullet;
    GameEngine gameEngine;
    GameObject spaceship;
    public Bullet (GameEngine gameEngine, GameObject spaceship) {
        this.gameEngine = gameEngine;
        this.spaceship = spaceship;

    }
    @Override
    public boolean handleTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onInit() {

        bullet = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.bullet);
        bullet = Bitmap.createScaledBitmap(bullet, getWidth(), getHeight(), false);

        bitmapX = getSpaceshipBulletX();
        bitmapY = getSpaceshipBulletY();

    }

    public float getSpaceshipBulletX() {
        return spaceship.getX() + spaceship.getWidth()/2;
    }
    public float getSpaceshipBulletY() {
        return spaceship.getY() - spaceship.getHeight();
    }
    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        if (getY() < -50) {

            setY(getSpaceshipBulletY());
            setX(getSpaceshipBulletX());
        }
        setY(getY() -6 );
        canvas.drawBitmap(bullet,getX(),getY(),null);

    }
}
