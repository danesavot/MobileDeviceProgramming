package lesson9.mum.edu.shootingfly.gameengine;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lesson9.mum.edu.shootingfly.GameView;
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
    private Timer timer;
    private Thread lock = new Thread();
    private boolean gameIsRunning,pauseGame;

    private Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            long previousTimeMillis;
            long currentTimeMillis;
            long elapsedMillis;
            previousTimeMillis = System.currentTimeMillis();

            while (gameIsRunning) {
                currentTimeMillis = System.currentTimeMillis();
                elapsedMillis = currentTimeMillis - previousTimeMillis;
                if (pauseGame) {
                    while (pauseGame) {
                        try {
                            synchronized (lock) {
                                lock.wait();
                            }
                        } catch (InterruptedException e) {
// We stay on the loop
                        }
                    }
                    currentTimeMillis = System.currentTimeMillis();
                }
                onUpdate(elapsedMillis);
                previousTimeMillis = currentTimeMillis;
            }
        }
    };


    public  GameEngine(GameView gameView) {

        this.gameView = gameView;

    }

    public boolean isGameIsRunning() {
        return gameIsRunning;
    }

    public void startGame() {
    // Stop a game if it is running
        stopGame();
    // Setup the game objects
        gameIsRunning = true;
        pauseGame = false;
        int numGameObjects = getGameObjects().size();
        for (int i = 0; i < numGameObjects; i++) {
            getGameObjects().get(i).onInit();
        }

        Thread thread = new Thread(updateThread);
        thread.start();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!pauseGame)
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

        //release pausegame if currently in pause state
        resumeGame();

        if (timer != null) {
            timer.cancel();
            timer.purge();
            gameIsRunning = false;
        }
    }
    public void pauseGame() {

        pauseGame = true;

    }
    public void resumeGame() {
        if (pauseGame == true) {
            pauseGame = false;
            synchronized (lock) {
                lock.notify();
            }

        }
        //startGame();
    }

    public void addGameObject(final GameObject gameObject) {
        //if (gameIsRunning()){
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
*/
    public void onUpdate(long elapsedMillis) {
        int numGameObjects = gameObjects.size();
        for (int i=0; i<numGameObjects; i++) {
            gameObjects.get(i).onUpdate(elapsedMillis);
        }
/*        synchronized (gameObjects) {
            while (!mObjectsToRemove.isEmpty()) {
                mGameObjects.remove(mObjectsToRemove.remove(0));
            }
            while (!objectsToAdd.isEmpty()) {
                mGameObjects.add(mObjectsToAdd.remove(0));
            }
        }*/
    }
}
