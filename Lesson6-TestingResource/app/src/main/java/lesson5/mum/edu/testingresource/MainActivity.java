package lesson5.mum.edu.testingresource;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GameView gameView;
    private Button flowerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);

        flowerButton = new Button(this);
        flowerButton.setWidth(350);
        flowerButton.setHeight(100);
        flowerButton.setBackgroundColor(Color.LTGRAY);
        flowerButton.setTextColor(Color.RED);
        flowerButton.setTextSize(20);
        flowerButton.setText("Give Flower");
        flowerButton.setOnClickListener(this);
        flowerButton.setGravity(Gravity.CENTER);

        FrameLayout gameLayout = new FrameLayout(this);

        gameLayout.addView(gameView);

        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        buttonLayout.addView(flowerButton);

        gameLayout.addView(buttonLayout);

        setContentView(gameLayout);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        gameView.killThread(); //Notice this reaches into the GameView object and runs the killThread mehtod.
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        gameView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        gameView.giveFlower();
    }
}
