package kr.or.dgit.it.db_study;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kr.or.dgit.it.db_study.database.DBHelper;

public class ReadDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        TextView titleView =findViewById(R.id.read_title);
        TextView contentView =findViewById(R.id.read_content);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title, content from tb_memo order by _id desc limit 1",null);
        while(cursor.moveToNext()){
            titleView.setText("Title :"+cursor.getString(0));
            contentView.setText("Content :"+cursor.getString(1));
        }
        db.close();
    }
}
