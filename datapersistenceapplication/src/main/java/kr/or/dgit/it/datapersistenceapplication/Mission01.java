package kr.or.dgit.it.datapersistenceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Mission01 extends AppCompatActivity {
    EditText eName;
    EditText ePhone;
    EditText eMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);
        setTitle(getIntent().getStringExtra("title"));

        eName = findViewById(R.id.edit_name);
        ePhone = findViewById(R.id.edit_phone);
        eMail = findViewById(R.id.edit_email);
    }

    public void addBtnClick(View view) {
        Intent intent = new Intent(this, Mission01Result.class);
        intent.putExtra("name",eName.getText().toString());
        intent.putExtra("phone",ePhone.getText().toString());
        intent.putExtra("mail",eMail.getText().toString());
        startActivity(intent);
    }
}
