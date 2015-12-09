package lesson9.mum.edu.shootingfly;

import android.graphics.Canvas;

/**
 * Created by 984391 on 12/7/2015.
 */
public abstract class GameObject {

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
