package tech.iwish.shyafinance.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import tech.iwish.shyafinance.Lists.EmiDetailList;
import tech.iwish.shyafinance.R;
import tech.iwish.shyafinance.activites.ActivityFragement;
import tech.iwish.shyafinance.activites.HomeActivity;
import tech.iwish.shyafinance.adapter.LoanAdapter;
import tech.iwish.shyafinance.config.Config;
import tech.iwish.shyafinance.config.JsonHelper;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    String loanId;
    Context context;
    List<EmiDetailList>emiDetailLists= new ArrayList<>();

    public MainFragment(String loanId, Context context){
        this.loanId = loanId;
        this.context=context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        InitaiteFragment(view);
        SetFragmentData();
        FragmentAction();
        return view;
    }

    public void InitaiteFragment(View view){
        recyclerView= view.findViewById(R.id.recycle);
    }
    public void FragmentAction(){

    }
    public void SetFragmentData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",loanId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder().post(body)
                .url(Config.EMIDETAIL)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    JsonHelper jsonHelper = new JsonHelper(result);
                    if (jsonHelper.isValidJson()) {
                        String responses = jsonHelper.GetResult("response");
                        if (responses.equals("TRUE")) {
                          JSONArray jsonArray = jsonHelper.setChildjsonArray(jsonHelper.getCurrentJsonObj(), "data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonHelper.setChildjsonObj(jsonArray, i);
                                emiDetailLists.add(new EmiDetailList(jsonHelper.GetResult("loandate"),jsonHelper.GetResult("emi")));
                                 }
                            Handler mainHandler = new Handler(context.getMainLooper());
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        LoanAdapter loanAdapter= new LoanAdapter(emiDetailLists,context);
                                        recyclerView.setAdapter(loanAdapter);
                                    }
                                });

                        }
                    }

                }
            }
        });

    }
}
