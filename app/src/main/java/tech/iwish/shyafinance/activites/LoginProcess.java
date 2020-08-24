package tech.iwish.shyafinance.activites;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import tech.iwish.shyafinance.R;

public class LoginProcess extends AppCompatActivity {

    private Button next_button;
    EditText mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_process);
        InitializeActivity();
        ActivityAction();
        SetActivityData();


    }

    public void InitializeActivity() {
        next_button = findViewById(R.id.next_button);
        mobile = findViewById(R.id.et_mobile_number);
    }

    public void SetActivityData() {

    }

    public void ActivityAction() {

    }

    public void RequestOTP(String mobile) {

    }


}