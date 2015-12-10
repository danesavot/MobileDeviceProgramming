package lesson9.mum.edu.shootingfly;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import lesson9.mum.edu.shootingfly.gameobject.Background;
import lesson9.mum.edu.shootingfly.gameobject.Bullet;
import lesson9.mum.edu.shootingfly.gameobject.Fly;
import lesson9.mum.edu.shootingfly.gameobject.FlyComposite;
import lesson9.mum.edu.shootingfly.gameobject.GameObject;
import lesson9.mum.edu.shootingfly.gameobject.Spaceship;

public class MainActivity extends AppCompatActivity {

    GameEngine gameEngine;
    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        gameEngine = new GameEngine(gameView);
        gameEngine.addGameObject(new Background(gameEngine));
        GameObject spaceship = new Spaceship(gameEngine);
        gameEngine.addGameObject(spaceship);

        FlyComposite flyComposite = new FlyComposite(gameEngine);
        flyComposite.add(new Fly(gameEngine));
        flyComposite.add(new Fly(gameEngine));
        flyComposite.add(new Fly(gameEngine));
        flyComposite.add(new Fly(gameEngine));
        flyComposite.add(new Fly(gameEngine));

        gameEngine.addGameObject(new Bullet(gameEngine,spaceship));


        gameEngine.addGameObject(flyComposite);


        gameView.setGameEngine(gameEngine);
        setContentView(gameView);

    }

    @Override
    protected void onPause() {
        super.onPause();

        gameEngine.pauseGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameEngine.isRunning())
            gameEngine.resumeGame();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LOW_PROFILE);
            }
            else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
