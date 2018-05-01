package kr.or.dgit.it.datapersistenceapplication;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class AdapterView extends AppCompatActivity {
    ProgressBar progressBar;
    RatingBar mRating;
    TextView mRateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view);
        setTitle(getIntent().getStringExtra("title"));

        Spinner spinner = findViewById(R.id.spinner);
        String[] datas = getResources().getStringArray(R.array.array_voice);

        ArrayAdapter<String> aa= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,datas);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);


        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.auto);
        String[] autoDatas = getResources().getStringArray(R.array.array_voice);
        ArrayAdapter<String> autoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autoDatas);

        autoCompleteTextView.setAdapter(autoAdapter);

        progressBar = findViewById(R.id.progress);
        ProgressThread thread = new ProgressThread();
        thread.start();


        mRating = (RatingBar) findViewById(R.id. ratingbar01 );
        mRateText = (TextView) findViewById(R.id. ratetext );
        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRateText.setText("Now Rate : " + rating);
            }
        });


    }
    class ProgressThread extends  Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                SystemClock.sleep(1000);
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(15);
            }
        }
    }
}
