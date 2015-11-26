package example.mum.edu.lesson3_converter;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

//http://android--examples.blogspot.com/2015/01/textview-bold-text-in-android.html

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText cupEditText;
    TextView tspTextView;
    TextView tbspTextView;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        cupEditText = new EditText(this);
        tspTextView = new TextView(this);
        tbspTextView = new TextView(this);
        calculateButton = new Button(this);

        LinearLayout.LayoutParams layoutParamsMatchWidth =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

/*        LinearLayout.LayoutParams layoutParamsMatchHeight =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);*/

        TextView cupTextView = new TextView(this);
        cupTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        cupTextView.setTypeface(cupTextView.getTypeface(), Typeface.BOLD);
        cupTextView.setText("Input number of Cups:");

        cupEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        calculateButton.setText("Calculate");
        calculateButton.setOnClickListener(this);

        //LinearLayout tspLinearLayout = new LinearLayout(this);
        //tspLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        tspTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);
        tspTextView.setTypeface(cupTextView.getTypeface(), Typeface.BOLD);
        tspTextView.setText("Number of Teaspoon:");

        tbspTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);
        tbspTextView.setTypeface(cupTextView.getTypeface(), Typeface.BOLD);
        tbspTextView.setText("Number of Tablespoon:");

        linearLayout.addView(cupTextView, layoutParamsMatchWidth);
        linearLayout.addView(cupEditText, layoutParamsMatchWidth);
        linearLayout.addView(calculateButton, layoutParamsMatchWidth);
        linearLayout.addView(tspTextView, layoutParamsMatchWidth);
        linearLayout.addView(tbspTextView, layoutParamsMatchWidth);

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

    @Override
    public void onClick(View v) {

        if (!cupEditText.getText().toString().isEmpty()) {

            Integer numCup = Integer.parseInt(cupEditText.getText().toString());

            int numTSP = 48 * numCup;
            int numTBSP = 16 * numCup;
            tspTextView.setText("Number of Teaspoon: " + numTSP);
            tbspTextView.setText("Number of Tablespoon: " + numTBSP);
        }
    }
}
