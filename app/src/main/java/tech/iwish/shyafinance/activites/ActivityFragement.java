package tech.iwish.shyafinance.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tech.iwish.shyafinance.Fragment.MainFragment;
import tech.iwish.shyafinance.R;

public class ActivityFragement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragement);
        ActivityAction(getIntent().getStringExtra("type"));

    }

    protected void ActivityAction(String type){
        switch (type){
            case "detail":
                MainFragment mainFragment = new MainFragment(getIntent().getStringExtra("loanid"),ActivityFragement.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();

        }

    }

}