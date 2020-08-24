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
import tech.iwish.shyafinance.R;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.Viewholder> {
    Context context;
    List<EmiDetailList> emiDetailLists= new ArrayList<>();


    public LoanAdapter( List<EmiDetailList>emiDetailLists,Context context){
     this.context=context;
     this.emiDetailLists=emiDetailLists;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_installment_design , null);
        Viewholder viewholder =  new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
      holder.amount.setText("Amount to pay ₹"+emiDetailLists.get(position).getEmiamount());
      holder.date.setText("LAST DATE "+emiDetailLists.get(position).getEmidate());
      holder.installment.setText("Installment "+position+1);
    }


    @Override
    public int getItemCount() {
        return emiDetailLists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView amount,date,installment;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            amount= itemView.findViewById(R.id.amount);
            date= itemView.findViewById(R.id.date);
            installment= itemView.findViewById(R.id.installment);
        }
    }
}
