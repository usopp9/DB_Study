package kr.or.dgit.it.datapersistenceapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kr.or.dgit.it.datapersistenceapplication.database.DBHelper;

public class Mission02Result extends AppCompatActivity {
    TextView tname;
    TextView tPhone;
    TextView tEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02_result);

        tname = findViewById(R.id.t_name);
        tPhone = findViewById(R.id.t_phone);
        tEmail = findViewById(R.id.t_mail);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name,phone,email from m_memo order by _id desc limit 1",null);

        while(cursor.moveToNext()){
            tname.setText(cursor.getString(0));
            tPhone.setText(cursor.getString(1));
            tEmail.setText(cursor.getString(2));
        }
        db.close();
    }
}
