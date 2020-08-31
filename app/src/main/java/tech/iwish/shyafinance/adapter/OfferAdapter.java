package tech.iwish.shyafinance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tech.iwish.shyafinance.Lists.EmiDetailList;
import tech.iwish.shyafinance.Lists.OfferLists;
import tech.iwish.shyafinance.R;
import tech.iwish.shyafinance.activites.OfferDetailActivity;

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

        holder.title.setText(offerLists.get(position).getTitle());
        holder.discription.setText(offerLists.get(position).getDiscriptiont());
        holder.title.setText(offerLists.get(position).getTitle());
        Glide.with(context).load(offerLists.get(position).getImage()).centerCrop().placeholder(R.drawable.girl).into(holder.image);
        holder.view.setOnClickListener(v -> {
             context.startActivity(new Intent(context, OfferDetailActivity.class).putExtra("id",offerLists.get(position).getId()).putExtra("title",offerLists.get(position).getTitle()).putExtra("image",offerLists.get(position).getImage()).putExtra("discription",offerLists.get(position).getDiscriptiont()));
        });
    }


    @Override
    public int getItemCount() {
        return offerLists.size();
//        return offerLists.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView title,discription;
        CircleImageView image;
        LinearLayout view;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.image);
            title= itemView.findViewById(R.id.title);
            discription= itemView.findViewById(R.id.discription);
            view= itemView.findViewById(R.id.view);
        }
    }
}
