package kr.or.dgit.it.datapersistenceapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import kr.or.dgit.it.datapersistenceapplication.database.DBHelper;

public class Mission02 extends AppCompatActivity {
    EditText eName;
    EditText ePhone;
    EditText eMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);
        setTitle(getIntent().getStringExtra("title"));

        eName = findViewById(R.id.edit_name);
        ePhone = findViewById(R.id.edit_phone);
        eMail = findViewById(R.id.edit_email);

    }

    public void addDBBtnClick(View view) {
        String name = eName.getText().toString();
        String phone = ePhone.getText().toString();
        String mail = eMail.getText().toString();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into m_memo (name,phone,email) values (?,?,?)",new String[]{name,phone,mail});
        db.close();

        Intent intent = new Intent(this,Mission02Result.class);
        startActivity(intent);
    }
}
