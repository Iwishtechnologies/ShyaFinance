package tech.iwish.shyafinance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import tech.iwish.shyafinance.R;
import tech.iwish.shyafinance.activites.ActivityFragement;
import tech.iwish.shyafinance.model.ClientLoanList;

public class SilderAdapter extends PagerAdapter {


    private LayoutInflater layoutInflater;
    List<ClientLoanList> clientLoanLists ;
    Context context;
    LinearLayout mainview;
    TextView accountNumber,type,name;

    public SilderAdapter(List<ClientLoanList> clientLoanLists,Context context) {
        this.clientLoanLists = clientLoanLists;
        this.context=context;
    }


    @Override
    public int getCount() {
        return clientLoanLists.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_card_layout, container, false);
        InitializeAdapter(view);
        SetAdapterData(position);
        AdapterAction(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }


    protected void InitializeAdapter(View view){
         mainview= view.findViewById(R.id.view);
         accountNumber = view.findViewById(R.id.account);
         type = view.findViewById(R.id.type);
         name = view.findViewById(R.id.name);
    }

    protected void SetAdapterData(int position){
        ClientLoanList list = clientLoanLists.get(position);
        accountNumber.setText("Account No. "+list.getAccount_no());
        name.setText(list.getName());
        type.setText("Account Type - "+list.getClient_type());
    }

    protected void AdapterAction(int position){
         mainview.setOnClickListener(v -> {context.startActivity(new Intent(context, ActivityFragement.class).putExtra("type","detail").putExtra("loanid",clientLoanLists.get(position).getAccount_no())); });
    }
}
