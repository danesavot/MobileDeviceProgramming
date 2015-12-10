package fragmentexample.rcrater.example.com.fragmentexample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Fragment fragment;
    Button btnFragment1, btnFragment2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFragment1 = (Button)findViewById(R.id.displayfragment1);
        btnFragment2 = (Button)findViewById(R.id.displayfragment2);

        btnFragment1.setOnClickListener(btnFragmentOnClickListener);
        btnFragment2.setOnClickListener(btnFragmentOnClickListener);
    }

    Button.OnClickListener btnFragmentOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Fragment newFragment;

            // Create new fragment
            if(v == btnFragment1){
                newFragment = new Fragment();
            }else{
                newFragment = new MyFragment2();
                ArrayList<String> stringList = new ArrayList<String>();
                stringList.add("Choose Site");
                stringList.add("MUM Main Site");
                stringList.add("MUM MSCS Site");


                Bundle bundle = new Bundle();
                bundle.putStringArrayList("theList", stringList);
                newFragment.setArguments(bundle);
            }

            // Create new transaction
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack
            transaction.replace(R.id.myfragment, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }};
}
