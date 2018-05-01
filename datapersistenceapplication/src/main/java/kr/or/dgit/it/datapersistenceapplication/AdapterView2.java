package kr.or.dgit.it.datapersistenceapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdapterView2 extends AppCompatActivity {
    int mYear, mMonth, mDay, mHour, mMinute;
    TextView mTxtDate;
    TextView mTxtTime;
    WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view2);
        setTitle(getIntent().getStringExtra("title"));

        mTxtDate = (TextView) findViewById(R.id. txtdate );
        mTxtTime = (TextView) findViewById(R.id. txttime );
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar. YEAR );
        mMonth = cal.get(Calendar. MONTH );
        mDay = cal.get(Calendar. DAY_OF_MONTH );
        mHour = cal.get(Calendar. HOUR_OF_DAY );
        mMinute = cal.get(Calendar. MINUTE );

        updateNow();

        /*위젯*/
        mWeb = (WebView) findViewById(R.id. web );
        mWeb.setWebViewClient(new MyWebClient());
        WebSettings set = mWeb.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        mWeb.loadUrl("http://www.naver.com");

        /*추가위젯*/
        NumberPicker picker1 = (NumberPicker)findViewById(R.id. picker1 );
        picker1.setMinValue(0);
        picker1.setMaxValue(5);
        picker1.setWrapSelectorWheel(false);
        NumberPicker picker2 = (NumberPicker)findViewById(R.id. picker2 );
        picker2.setMinValue(0);
        picker2.setMaxValue(20);
        picker2.setValue(10);
        picker2.setOnLongPressUpdateInterval(100);
        NumberPicker picker3 = (NumberPicker)findViewById(R.id. picker3 );
        picker3.setMinValue(0);
        picker3.setMaxValue(6);
        picker3.setDisplayedValues(new String[] {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"});

        NumberPicker.Formatter mFormatter = new NumberPicker.Formatter() {
            public String format(int value) {
                switch (value) { case 0: return "Zerg"; case 1: return "Terran"; case 2: return "Protoss";
                }
                return null;
            }
        };

        NumberPicker picker4 = (NumberPicker)findViewById(R.id. picker4 );
        picker4.setMinValue(0); picker4.setMaxValue(2);
        picker4.setFormatter(mFormatter);
        NumberPicker picker5 = (NumberPicker)findViewById(R.id. picker5 );
        picker5.setMinValue(100); picker5.setMaxValue(200);
        picker5.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String text = "Value : " + newVal;
                Toast. makeText (AdapterView2.this, text, Toast. LENGTH_LONG ).show();
            }
        });


    }

    private void updateNow() {
        String dateTxt = String. format ("%d/%d/%d", mYear, mMonth+1, mDay);
        mTxtDate.setText(dateTxt);
        String timeTxt = String. format ("%d:%d", mHour, mMinute); mTxtTime.setText(timeTxt);
    }
    public void mOnClick(View view){
        switch(view.getId()){
            case R.id. btnchangedate :
                new DatePickerDialog(AdapterView2.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id. btnchangetime :
                new TimePickerDialog(AdapterView2.this, mTimeSetListener, mHour, mMinute, false).show();
                break;
        }
    }
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateNow();
        }
    };
    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            updateNow();
        }
    };

    public void mOn2Click(View view) {
        switch(view.getId()){
            case R.id. btngo :
                EditText addr = (EditText) findViewById(R.id. address );
                String url = addr.getText().toString();
                mWeb.loadUrl(url);
                break;
            case R.id. btnback :
                if (mWeb.canGoBack()) mWeb.goBack();
                break;
            case R.id. btnforward :
                if (mWeb.canGoForward())mWeb.goForward();
                break;
                case R.id. btnlocal :
                    mWeb.loadUrl("file:///android_asset/test2.html");
                    break;
        }
    }
}
    class MyWebClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }
}
