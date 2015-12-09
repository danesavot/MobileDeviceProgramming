package lesson9.mum.edu.shootingfly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by noname on 12/8/2015.
 */
public class Spaceship extends GameObject {

    float bitmapX, bitmapY;

    Bitmap spaceShip;
    GameEngine gameEngine;
    public Spaceship(GameEngine gameEngine) {
        this.gameEngine= gameEngine;
    }
    @Override
    public void onInit() {

        spaceShip = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.daemon);
        spaceShip = Bitmap.createScaledBitmap(spaceShip, 200, 200, false);

    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(spaceShip,gameEngine.gameView.getWidth()/2, gameEngine.gameView.getHeight(),null );

    }
}
