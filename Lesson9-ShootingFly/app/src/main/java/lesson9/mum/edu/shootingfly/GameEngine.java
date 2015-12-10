package lesson9.mum.edu.shootingfly;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lesson9.mum.edu.shootingfly.gameobject.GameObject;

/**
 * Created by 984391 on 12/7/2015.
 */
public class GameEngine {
    private static int EXPECTED_FPS = 30;
    private static final long TIME_BETWEEN_DRAWS = 1000 / EXPECTED_FPS;

    public GameView gameView;
    private List<GameObject> gameObjects =
            new ArrayList<GameObject>();
    private List<GameObject> objectsToAdd =
            new ArrayList<GameObject>();
    Timer timer;

    public  GameEngine(GameView gameView) {

        this.gameView = gameView;

    }

    public void startGame() {
    // Stop a game if it is running
        stopGame();
    // Setup the game objects

        int numGameObjects = getGameObjects().size();
        for (int i = 0; i < numGameObjects; i++) {
            getGameObjects().get(i).onInit();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onDraw();
            }
        }, 0, TIME_BETWEEN_DRAWS);

    }

    public void onDraw() {

        Canvas c = null;
        try {
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
                gameView.getHolder().unlockCanvasAndPost(c);
            }
        }

    }
    public void stopGame() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }
    public void pauseGame() {
        stopGame();
    }
    public void resumeGame() {
        startGame();
    }

    public void addGameObject(final GameObject gameObject) {
        //if (isRunning()){
            gameObjects.add(gameObject);
        //}
        //else {
        //   gameObjects.add(gameObject);
        //}
        //activity.runOnUiThread(gameObject.mOnAddedRunnable);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
/*    public void removeGameObject(final GameObject gameObject) {
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
    }*/
}
