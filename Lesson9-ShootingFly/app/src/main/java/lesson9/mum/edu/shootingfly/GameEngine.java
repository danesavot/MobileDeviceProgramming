package lesson9.mum.edu.shootingfly;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 984391 on 12/7/2015.
 */
public class GameEngine {

    GameView gameView;
    private List<GameObject> gameObjects =
            new ArrayList<GameObject>();
    private List<GameObject> objectsToAdd =
            new ArrayList<GameObject>();
    private UpdateThread updateThread;
    private DrawThread drawThread;

    public  GameEngine(GameView gameView) {

        this.gameView = gameView;

    }


    public void startGame() {
    // Stop a game if it is running
        stopGame();
    // Setup the game objects
        int numGameObjects = gameObjects.size();
        for (int i = 0; i < numGameObjects; i++) {
            gameObjects.get(i).onDraw();
        }

        Canvas c = null;
        try
        {
            c = gameView.getHolder().lockCanvas();
            synchronized (gameView.getHolder())
            {
                gameView.onDraw(c);
            }
        }
        finally
        {
            if (c != null)
            {
                view.getHolder().unlockCanvasAndPost(c);
            }
        }

    // Start the update thread
        updateThread = new UpdateThread(this);
        updateThread.start();
    // Start the drawing thread
        drawThread = new DrawThread(this);
        drawThread.start();
    }

    public void stopGame() {
        if (mUpdateThread != null) {
            mUpdateThread.stopGame();
        }
        if (mDrawThread != null) {
            mDrawThread.stopGame();
        }
    }
    public void addGameObject(final GameObject gameObject) {
        if (isRunning()){
            objectsToAdd.add(gameObject);
        }
        else {
            gameObjects.add(gameObject);
        }
        activity.runOnUiThread(gameObject.mOnAddedRunnable);
    }
    public void removeGameObject(final GameObject gameObject) {
        mObjectsToRemove.add(gameObject);
        mActivity.runOnUiThread(gameObject.mOnRemovedRunnable);
    }

    public void onUpdate(long elapsedMillis) {
        int numGameObjects = gameObjects.size();
        for (int i=0; i<numGameObjects; i++) {
            gameObjects.get(i).onUpdate(elapsedMillis, this);
        }
        synchronized (gameObjects) {
            while (!mObjectsToRemove.isEmpty()) {
                mGameObjects.remove(mObjectsToRemove.remove(0));
            }
            while (!objectsToAdd.isEmpty()) {
                mGameObjects.add(mObjectsToAdd.remove(0));
            }
        }
    }
}
