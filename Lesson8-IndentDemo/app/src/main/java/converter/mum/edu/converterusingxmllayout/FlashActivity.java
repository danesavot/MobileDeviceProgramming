package converter.mum.edu.converterusingxmllayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by noname on 12/6/2015.
 */
public class FlashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_screen);
    }

    public void textViewClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
