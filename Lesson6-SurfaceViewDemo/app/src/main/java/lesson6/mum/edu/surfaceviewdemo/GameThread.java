package lesson6.mum.edu.surfaceviewdemo;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

import java.util.Timer;

/**
 * Created by 984391 on 12/2/2015.
 */
public class GameThread extends Thread {

    private GameView view;
    private boolean running = false;
    private long millisRun = 30000;
    long startMillis;

    public GameThread(GameView viewIn) {

        this.view = viewIn;
    }
    public void setRunning(boolean run)
    {
        running = run;
    }
    @SuppressLint("WrongCall")
    @Override
    public void run()
    {
        startMillis = System.currentTimeMillis();
        long currentMillis = startMillis;
        view.startMillis = startMillis;
        view.reset();

        while (running &&  currentMillis <= startMillis + millisRun )
        {
            currentMillis = System.currentTimeMillis();

            Canvas c = null;
            try
            {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                    view.onDraw(c);
                }
            }
            finally
            {
                if (c != null)
                {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
