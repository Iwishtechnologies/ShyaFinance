package tech.iwish.shyafinance.adapter;

import android.content.Context;
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
import tech.iwish.shyafinance.model.ClientLoanList;

public class SilderAdapter extends PagerAdapter {


    private LayoutInflater layoutInflater;
    List<ClientLoanList> clientLoanLists ;

    public SilderAdapter(List<ClientLoanList> clientLoanLists) {
        this.clientLoanLists = clientLoanLists;
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
        View view = layoutInflater.inflate(R.layout.row_silder, container, false);

        ClientLoanList list = clientLoanLists.get(position);

        TextView accountNumber = view.findViewById(R.id.accountNumber);

        accountNumber.setText(list.getAccount_no());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
