package vn.edu.devpro.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import vn.edu.devpro.login.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    SQLHelper sqlHelper;
    ArrayList<Account> accountArrayList;
    Account account1, account2, account3;
    final Pattern patternSo = Pattern.compile("^" + "(?=.*[0-9])" + ".{6,}" + "$");
    final Pattern patternChuHoa = Pattern.compile("^" + "(?=.*[A-Z])" + ".{6,}" + "$");
    final Pattern patternChuThuong = Pattern.compile("^" + "(?=.*[a-z])" + ".{6,}" + "$");
    final Pattern patternKiTuDacBiet = Pattern.compile("^" + "(?=.*[@#$%!&*_+=])" + ".{6,}" + "$");
    final Pattern patternKhoangTrang = Pattern.compile("^" + "(?=\\S+$)" + ".{6,}" + "$");
//    final String abc = "((?=.*[a-z]))";

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setTitle("Login");
        
        sqlHelper = new SQLHelper(getBaseContext());

        accountArrayList = sqlHelper.getAllAccount();


//        pattern = Pattern.compile("(?=.*[0-9])");

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            int dem = 0;
            @Override
            public void onClick(View view) {
                String password = binding.ePassword.getText().toString();
                if(binding.eUsername.getText().length() == 0 || binding.ePassword.getText().length() == 0){
                    Toast.makeText(getBaseContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(binding.ePassword.getText().length() <6){
                        Toast.makeText(getBaseContext(), "Mật khẩu phải có ít nhất 6 kí tự.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(!patternSo.matcher(password).matches()) {
                            Toast.makeText(getBaseContext(), "Mật khẩu phải bao gồm ít nhất một số.", Toast.LENGTH_SHORT).show();
                        }
                        else if(!patternChuHoa.matcher(password).matches()){
                            Toast.makeText(getBaseContext(), "Mật khẩu phải bao gồm ít nhất một chữ hoa.", Toast.LENGTH_SHORT).show();
                        }
                        else if(!patternChuThuong.matcher(password).matches()){
                            Toast.makeText(getBaseContext(), "Mật khẩu phải bao gồm ít nhất một chữ thường.", Toast.LENGTH_SHORT).show();
                        }
                        else if(!patternKiTuDacBiet.matcher(password).matches()){
                            Toast.makeText(getBaseContext(), "Mật khẩu phải bao gồm ít nhất một kí tự đặc biệt.", Toast.LENGTH_SHORT).show();
                        }
                        else if(!patternKhoangTrang.matcher(password).matches()){
                            Toast.makeText(getBaseContext(), "Mật khẩu không được có khoảng trắng.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (accountArrayList != null) {
                                for (int i = 0; i < accountArrayList.size(); i++) {
                                    if ((binding.eUsername.getText().toString().equals(accountArrayList.get(i).getUsername()) == true)
                                            && (password.equals(accountArrayList.get(i).getPassword()) == true)) {

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
            }
        });
    }
}
