package kr.or.dgit.it.db_study;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import kr.or.dgit.it.db_study.database.DBHelper;

public class BasicSQLiteActivity extends AppCompatActivity {
    EditText titleView;
    EditText contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_sqlite);
        setTitle(getIntent().getStringExtra("title"));
        titleView= findViewById(R.id.add_title);
        contentView=findViewById(R.id.add_content);
    }

    public void addOnclick(View view) {
        String title =titleView.getText().toString();
        String content = contentView.getText().toString();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into tb_memo(title,content) values (?,?)",new String[]{title,content});
        db.close();
        Intent intent = new Intent(this, ReadDBActivity.class);
        startActivity(intent);
    }
}
