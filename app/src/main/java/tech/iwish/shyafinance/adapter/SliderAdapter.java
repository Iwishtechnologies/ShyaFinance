package tech.iwish.shyafinance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import tech.iwish.shyafinance.Lists.SliderItem;
import tech.iwish.shyafinance.R;

public class SliderAdapter extends  SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
    private Context context;
    private List<SliderItem> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context, List<SliderItem> mSliderItems){
        this.context=context;
        this.mSliderItems=mSliderItems;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }


    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.row_slider_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        SliderItem sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getURL())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }


    @Override
    public int getCount() {
        return mSliderItems.size();
    }


    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image);
            this.itemView = itemView;
        }
    }
}
