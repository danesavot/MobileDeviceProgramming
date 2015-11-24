package example.mum.edu.linealayoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setGravity(Gravity.TOP);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView titleTextView = new TextView(this);
        titleTextView.setText("This is Title");
        titleTextView.setTextColor(Color.CYAN);
        titleTextView.setBackgroundColor(Color.RED);
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);

        TextView bodyTextView = new TextView(this);
        bodyTextView.setGravity(Gravity.CENTER);
        bodyTextView.setTextColor(Color.BLUE);
        bodyTextView.setBackgroundColor(Color.GRAY);
        bodyTextView.setText("This is Body");
        bodyTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        TextView remarkTextView = new TextView(this);
        remarkTextView.setGravity(Gravity.CENTER);
        remarkTextView.setTextColor(Color.RED);
        remarkTextView.setBackgroundColor(Color.MAGENTA);
        remarkTextView.setText("This is remark");
        remarkTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        //EditText editText = new EditText(this);

        linearLayout.addView(titleTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(bodyTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(remarkTextView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));

                setContentView(linearLayout);
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
