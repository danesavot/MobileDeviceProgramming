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
public class Background extends GameObject {


    Bitmap background;
    GameEngine gameEngine;

    public Background(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public boolean handleTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onInit() {

        background = BitmapFactory.decodeResource(gameEngine.gameView.getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(background, gameEngine.gameView.getWidth(), gameEngine.gameView.getHeight(), false);


    }

    @Override
    public void onUpdate(long elapsedMillis) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(background,0,0,null);

    }
}
