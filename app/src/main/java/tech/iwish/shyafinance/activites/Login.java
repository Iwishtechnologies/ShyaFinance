package tech.iwish.shyafinance.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tech.iwish.shyafinance.R;

public class Login extends AppCompatActivity {

    private Button sign_in_truecaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sign_in_truecaller = findViewById(R.id.sign_in_truecaller);

        sign_in_truecaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,LoginProcess.class);
                startActivity(intent);

            }
        });
    }
}