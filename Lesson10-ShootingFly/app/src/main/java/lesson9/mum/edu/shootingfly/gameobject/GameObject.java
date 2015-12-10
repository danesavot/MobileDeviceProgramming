package lesson9.mum.edu.shootingfly.gameobject;

import android.graphics.Canvas;
import android.view.MotionEvent;

import lesson9.mum.edu.shootingfly.GameEngine;

/**
 * Created by 984391 on 12/7/2015.
 */
public abstract class GameObject {

    float bitmapX, bitmapY;
    private int width =100;
    private int height =100;

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public float getX() {
        return  bitmapX;
    }
    public float getY() {
        return  bitmapY;
    }
    public void setX(float x) {
        bitmapX = x;
    }
    public void setY(float y) {
        bitmapY = y;
    }

    public abstract boolean handleTouchEvent(MotionEvent event);


    //used for the initialization of the object before a game can start.
    public abstract void onInit();
    //called by the game engine as fast as possible, providing the
    //number of milliseconds that have passed since the previous call and a
    //reference to the GameEngine itself for future uses such as accessing user input
    public abstract void onUpdate(long elapsedMillis,
                                  GameEngine gameEngine);
    //makes the component render itself.
    public abstract void onDraw(Canvas canvas);
    public final Runnable mOnAddedRunnable = new Runnable() {
        @Override
        public void run() {
            onAddedToGameUiThread();
        }
    };
    public final Runnable mOnRemovedRunnable = new Runnable() {
        @Override
        public void run() {
            onRemovedFromGameUiThread();
        }
    };
    //contains code that must be run on the
    //UIThread when the object is removed from the game.
    public void onRemovedFromGameUiThread(){
    }
    //contains code that must be run on the UIThread
    //when the object is added to the game.
    public void onAddedToGameUiThread(){
    }

}
