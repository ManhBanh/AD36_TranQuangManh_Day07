package vn.edu.devpro.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    SQLHelper sqlHelper;

    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sqlHelper = new SQLHelper(getBaseContext());

        btnadd = findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlHelper.insertIntoTable("tranmanh", "Matkhau_01");
                sqlHelper.insertIntoTable("nguyenhuy", "Matkhau_02");
                sqlHelper.insertIntoTable("tranlinh", "Matkhau_03");
                Toast.makeText(getBaseContext(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
