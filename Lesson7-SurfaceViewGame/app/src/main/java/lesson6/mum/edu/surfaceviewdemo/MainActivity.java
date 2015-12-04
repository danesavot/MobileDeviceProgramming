package lesson6.mum.edu.surfaceviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    GameThread gameThread;
    Button startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        startGame = new Button(this);
        startGame.setWidth(350);
        startGame.setHeight(100);
        startGame.setBackgroundColor(Color.LTGRAY);
        startGame.setTextColor(Color.RED);
        startGame.setTextSize(20);
        startGame.setText("Play");
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gameThread != null) {
                    if (gameThread.isAlive()) {
                        killThread();
                    }
                }

                gameThread = new GameThread(gameView);

                gameThread.setRunning(true);
                gameThread.start();
            }
        });
        startGame.setGravity(Gravity.CENTER);

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        buttonLayout.addView(startGame);

        gameView = new

                GameView(this);

        FrameLayout gameLayout = new FrameLayout(this);
        gameLayout.addView(gameView);
        gameLayout.addView(buttonLayout);

        setContentView(gameLayout);

    }

    @Override
    protected void onPause() {
        super.onPause();
        killThread();
    }

    private void killThread() {

        boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameView.onDestroy();
    }


}
