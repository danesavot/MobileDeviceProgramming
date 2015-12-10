package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import lesson9.mum.edu.shootingfly.GameEngine;

/**
 * Created by 984391 on 12/10/2015.
 */
public class BulletComposite extends GameObject {

    private List<GameObject> bullets = new ArrayList<>();

    @Override
    public boolean handleTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onInit() {

        for (int i = 0; i < bullets.size() ; i++) {

            bullets.get(i).onInit();

        }
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {

    }

    @Override
    public void onDraw(Canvas canvas) {



    }
}
