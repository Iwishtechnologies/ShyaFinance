package tech.iwish.shyafinance.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tech.iwish.shyafinance.R;

public class OfferDetailActivity extends AppCompatActivity {
    TextView title,discription;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);
        InitaiteActivity();
        ActivityAction();
        SetActivityData();

    }

    public void InitaiteActivity(){
        title= findViewById(R.id.title);
        discription= findViewById(R.id.discription);
        image= findViewById(R.id.image);
    }
    public void ActivityAction(){

    }
    public void SetActivityData(){
      title.setText(getIntent().getStringExtra("title").toString());
      discription.setText(getIntent().getStringExtra("discription").toString());
      Glide.with(OfferDetailActivity.this).load(getIntent().getStringExtra("image").toString()).centerCrop().placeholder(R.drawable.girl).into(image);

    }
}