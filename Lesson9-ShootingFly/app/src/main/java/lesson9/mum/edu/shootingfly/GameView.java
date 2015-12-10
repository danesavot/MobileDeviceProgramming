package lesson9.mum.edu.shootingfly;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 984391 on 12/7/2015.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;

    GameEngine gameEngine;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }

    public void setGameEngine(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        if (gameEngine != null) {
            gameEngine.startGame();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        for (int i = 0; i < gameEngine.getGameObjects().size() ; i++) {
            gameEngine.getGameObjects().get(i).handleTouchEvent(event);
        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int numGameObjects = gameEngine.getGameObjects().size();
        for (int i = 0; i < numGameObjects; i++) {
            gameEngine.getGameObjects().get(i).onDraw(canvas);
        }

    }
}
