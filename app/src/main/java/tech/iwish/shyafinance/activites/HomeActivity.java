package tech.iwish.shyafinance.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tech.iwish.shyafinance.Fragment.InvestWithUsFragment;
import tech.iwish.shyafinance.Fragment.MainFragment;
import tech.iwish.shyafinance.Fragment.OfferFragment;
import tech.iwish.shyafinance.Fragment.ProfileFragment;
import tech.iwish.shyafinance.Fragment.WalletFragment;
import tech.iwish.shyafinance.R;
import tech.iwish.shyafinance.adapter.SilderAdapter;
import tech.iwish.shyafinance.config.Config;
import tech.iwish.shyafinance.config.JsonHelper;
import tech.iwish.shyafinance.model.ClientLoanList;

public class HomeActivity extends AppCompatActivity {
    LinearLayout home,wallet,offer,profile,Investwithus;
    ViewPager viewpager;
    List<ClientLoanList> clientLoanLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        InitializeActivity();
        AccountDetails();
        ActivityAction();

    }




    public void InitializeActivity(){
        viewpager = findViewById(R.id.viewpager);
        home = findViewById(R.id.home);
        wallet = findViewById(R.id.loan);
        offer = findViewById(R.id.offer);
        profile = findViewById(R.id.profile);
        Investwithus = findViewById(R.id.investwithus);
    }

    private void SetActivityData(){

    }

    protected void ActivityAction(){
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                MainFragment mainFragment = new MainFragment(clientLoanLists.get(position).getAccount_no(),HomeActivity.this);
                getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        profile.setOnClickListener(v -> {
            viewpager.setVisibility(View.GONE);
            ProfileFragment mainFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();});
        offer.setOnClickListener(v -> {
            viewpager.setVisibility(View.GONE);
            OfferFragment mainFragment = new OfferFragment(HomeActivity.this);
            getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();});
        Investwithus.setOnClickListener(v -> {
//            InvestWithUsFragment mainFragment = new InvestWithUsFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();






        });
        wallet.setOnClickListener(v -> {
                    Toast.makeText(this, "This feature will come soon", Toast.LENGTH_SHORT).show();

//            WalletFragment mainFragment = new WalletFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();
            });
        home.setOnClickListener(v -> {
            MainFragment mainFragment = new MainFragment(clientLoanLists.get(0).getAccount_no(),HomeActivity.this);
            getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();
            });
    }

    private void AccountDetails() {

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clientId", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request1 = new Request.Builder().url(Config.ACCOUNT_DETAILS).post(body).build();
        okHttpClient.newCall(request1).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.e("result", result);
                    JsonHelper jsonHelper = new JsonHelper(result);
                    if (jsonHelper.isValidJson()) {
                        String responses = jsonHelper.GetResult("response");
                        if (responses.equals("TRUE")) {

                            JSONArray jsonArray = jsonHelper.setChildjsonArray(jsonHelper.getCurrentJsonObj(), "data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonHelper.setChildjsonObj(jsonArray, i);
                                clientLoanLists.add(new ClientLoanList(
                                        jsonHelper.GetResult("sno")
                                        , jsonHelper.GetResult("client_id")
                                        , jsonHelper.GetResult("account_no")
                                        , jsonHelper.GetResult("client_type")
                                        , jsonHelper.GetResult("name")
                                ));
                            }


                            HomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    SilderAdapter silderAdapter = new SilderAdapter(clientLoanLists,HomeActivity.this);
                                    viewpager.setAdapter(silderAdapter);

                                    MainFragment mainFragment = new MainFragment(clientLoanLists.get(0).getAccount_no(),HomeActivity.this);
                                    getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();
                                }
                            });

//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    new Handler().post(new Runnable() {
//                                        @Override
//                                        public void run() {
//
//                                        }
//                                    });
//                                }
//                            });
                        }
                    }
                }
            }
        });


    }


}