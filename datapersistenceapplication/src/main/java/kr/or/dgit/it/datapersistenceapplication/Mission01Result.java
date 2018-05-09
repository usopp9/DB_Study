package kr.or.dgit.it.datapersistenceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Mission01Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01_result);

        TextView tName = findViewById(R.id.t_name);
        TextView tPhone = findViewById(R.id.t_phone);
        TextView tMail = findViewById(R.id.t_mail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String mail = intent.getStringExtra("mail");
        tName.setText(name);
        tPhone.setText(phone);
        tMail.setText(mail);

    }
}
