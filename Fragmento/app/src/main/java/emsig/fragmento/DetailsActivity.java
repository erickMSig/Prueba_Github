package emsig.fragmento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private String textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getIntent().getExtras()!=null){
            textView = getIntent().getStringExtra("text");
        }

        DetailsFragment detailsFragment = (DetailsFragment)getSupportFragmentManager()
                .findFragmentById(R.id.detailsFragment);
        detailsFragment.renderText(textView);

    }
}
