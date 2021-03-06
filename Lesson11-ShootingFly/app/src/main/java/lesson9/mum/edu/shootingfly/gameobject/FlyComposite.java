package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import lesson9.mum.edu.shootingfly.gameengine.GameEngine;

/**
 * Created by 984391 on 12/9/2015.
 */
public class FlyComposite extends GameObject {

    private List<GameObject> flies = new ArrayList<>();

    int space = 130;
    int moveX =0, factor = 1;

    GameEngine gameEngine;
    public FlyComposite(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void add(GameObject fly)
    {
        flies.add(fly);
    }

    public void remove(GameObject fly)
    {
        flies.remove(fly);
    }

    @Override
    public int getWidth() {
        int width=0;
        for (int i = 0; i < flies.size(); i++) {
            width +=flies.get(i).getWidth();
        }
        return width;
    }

    @Override
    public int getHeight() {
        int height=0;
        for (int i = 0; i < flies.size(); i++) {
            height +=flies.get(i).getHeight();
        }
        return height;
    }

    @Override
    public boolean handleTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onInit() {
        GameObject fly;

        int x;
        for (int i = 0; i < flies.size(); i++) {
            fly = flies.get(i);
            fly.onInit();
            x = (gameEngine.gameView.getWidth() - fly.getWidth() - (flies.size() -1) * space)/2;
            fly.setX( x + i*space);

        }
    }

    @Override
    public void onUpdate(long elapsedMillis) {

    }

    @Override
    public void onDraw(Canvas canvas) {

        GameObject fly;

        for (int i = 0; i < flies.size(); i++) {

            fly = flies.get(i);

            moveX += 2 * factor;
            if (moveX <0 || moveX >500)
                factor *= -1;

            fly.setX( fly.getX() + 2 * factor);
            fly.onDraw(canvas);

        }

    }

}
