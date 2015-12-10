package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import lesson9.mum.edu.shootingfly.GameEngine;
import lesson9.mum.edu.shootingfly.R;

/**
 * Created by noname on 12/8/2015.
 */
public class Spaceship extends GameObject {

    private int width =200;
    private int height =200;

    Bitmap spaceShip;
    GameEngine gameEngine;
    public Spaceship(GameEngine gameEngine) {
        this.gameEngine= gameEngine;

    }

    @Override
    public boolean handleTouchEvent(MotionEvent event) {

        float x,y;
        x= event.getX();
        y = event.getY();

        this.setX(x);
        return false;
    }

    @Override
    public void onInit() {

        spaceShip = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.spaceship);
        spaceShip = Bitmap.createScaledBitmap(spaceShip, width, height, false);

        bitmapX = (gameEngine.gameView.getWidth()-width)/2;
        bitmapY = gameEngine.gameView.getHeight()-200;

    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(spaceShip,bitmapX, bitmapY,null );

    }
}
