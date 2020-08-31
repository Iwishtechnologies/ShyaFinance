package tech.iwish.shyafinance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tech.iwish.shyafinance.Lists.EmiDetailList;
import tech.iwish.shyafinance.Lists.OfferLists;
import tech.iwish.shyafinance.R;

public class OfferAdapter  extends RecyclerView.Adapter<OfferAdapter.Viewholder> {
    Context context;
    int installment=1;
    List<OfferLists> offerLists= new ArrayList<>();


    public OfferAdapter( List<OfferLists>offerLists,Context context){
        this.context=context;
        this.offerLists=offerLists;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_offer_design , null);
        Viewholder viewholder =  new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
//        installment=position+1;
//        holder.amount.setText("Amount to pay ₹"+emiDetailLists.get(position).getEmiamount());
//        holder.date.setText("LAST DATE "+emiDetailLists.get(position).getEmidate());
//        holder.installment.setText("Installment "+installment);
//        holder.pay.setText("PAY ₹ "+emiDetailLists.get(position).getEmiamount());

    }


    @Override
    public int getItemCount() {
        return 500;
//        return offerLists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView amount,date,installment,pay;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            amount= itemView.findViewById(R.id.amount);
            date= itemView.findViewById(R.id.date);
            installment= itemView.findViewById(R.id.installment);
            pay= itemView.findViewById(R.id.pay);
        }
    }
}
