package vn.edu.devpro.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.devpro.login.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    SQLHelper sqlHelper;
    ArrayList<Account> accountArrayList;
    Account account1, account2, account3;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setTitle("Login");
        
        sqlHelper = new SQLHelper(getBaseContext());

//        sqlHelper.insertIntoTable("tranmanh", "Matkhau_01");

        accountArrayList = sqlHelper.getAllAccount();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            int dem = 0;
            @Override
            public void onClick(View view) {
                if(binding.eUsername.getText().length() == 0 || binding.ePassword.getText().length() == 0){
                    Toast.makeText(getBaseContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(binding.ePassword.getText().length() <6){
                        Toast.makeText(getBaseContext(), "Mật khẩu phải có ít nhất 6 kí tự.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (accountArrayList != null) {
                            for (int i = 0; i < accountArrayList.size(); i++) {
                                if ((binding.eUsername.getText().toString().equals(accountArrayList.get(i).getUsername()) == true)
                                        && (binding.ePassword.getText().toString().equals(accountArrayList.get(i).getPassword()) == true)) {

                                    dem = 1;
                                    break;
                                }
                            }
                            if (dem != 1) {
                                Toast.makeText(getBaseContext(), "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            }
                            dem = 0;
                        }
                    }
                }
            }
        });
        
    }
}
