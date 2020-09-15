package tech.iwish.shyafinance.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

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
import tech.iwish.shyafinance.Fragment.MainFragment;
import tech.iwish.shyafinance.Lists.SliderItem;
import tech.iwish.shyafinance.R;
import tech.iwish.shyafinance.adapter.SilderAdapter;
import tech.iwish.shyafinance.adapter.SliderAdapter;
import tech.iwish.shyafinance.config.Config;
import tech.iwish.shyafinance.config.JsonHelper;
import tech.iwish.shyafinance.model.ClientLoanList;

public class MainActivity extends AppCompatActivity {
    private List<SliderItem> mSliderItems = new ArrayList<>();
    List<ClientLoanList> clientLoanLists = new ArrayList<>();
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeActivity();
        SetActivityData();
        ActivityAction();
        SetAlider();
        AccountDetails();
    }

    private void AccountDetails() {

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put( "clientId", "1");
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
                                        , jsonHelper.GetResult("account_type")
                                        , jsonHelper.GetResult("name")
                                ));
                            }


                            MainActivity.this.runOnUiThread(() -> {
                                SilderAdapter silderAdapter = new SilderAdapter(clientLoanLists,MainActivity.this);
                                viewPager.setAdapter(silderAdapter);

//                                    MainFragment mainFragment = new MainFragment(clientLoanLists.get(0).getAccount_no(),MainActivity.this);
//                                    getSupportFragmentManager().beginTransaction().replace(R.id.LoanFrameLayout, mainFragment).commit();
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

    protected void SetAlider(){
        mSliderItems.add(new SliderItem("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.C6q8im59YJPT0M0kuRRtywHaDt%26pid%3DApi&f=1"));
        mSliderItems.add(new SliderItem("https://i.pinimg.com/originals/23/77/9c/23779c59cb16ed5519ca4c0711f85e3c.jpg"));
        mSliderItems.add(new SliderItem("https://media.istockphoto.com/illustrations/floral-horizontal-border-watercolor-meadow-flowers-grass-herbs-frame-illustration-id518522602"));
        mSliderItems.add(new SliderItem("https://www.flowersforever.ie/wp-content/uploads/2014/04/Wedding-Flowers-5.jpg"));

        SliderView sliderView = findViewById(R.id.imageSlider);
        SliderAdapter adapter = new SliderAdapter(MainActivity.this,mSliderItems);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();
    }

    protected void InitializeActivity(){
        viewPager= findViewById(R.id.viewpager1);
    }

    protected void SetActivityData(){

    }

    protected void ActivityAction(){}

}