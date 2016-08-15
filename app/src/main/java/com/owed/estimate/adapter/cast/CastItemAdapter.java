package com.owed.estimate.adapter.cast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.owed.estimate.R;
import com.owed.estimate.model.cast.CastItem;

import java.util.ArrayList;

public class CastItemAdapter extends RecyclerView.Adapter<CastItemAdapter.CastItemViewHolder> {

    private LayoutInflater inflater;
    private RequestManager mGlideRequestManager;

    private ArrayList<CastItem> items;

    //  ======================================================================================

    public CastItemAdapter(Context context, ArrayList<CastItem> items) {
        this.items = items;
        mGlideRequestManager = Glide.with(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.holder_cast_item, parent, false);
        return new CastItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CastItemViewHolder holder, int position) {
        CastItem item = items.get(position);
        holder.titleField.setText(item.title);

        mGlideRequestManager.load(item.image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if( items == null )
            return 0;

        return items.size();
    }

    //  ======================================================================================

    public void addItems(ArrayList<CastItem> castItems) {
        int start = items.size();
        int end = start + castItems.size();
        items.addAll(castItems);
        notifyItemRangeChanged(start, end);
    }

    public CastItem getItem(int position) {
        if( items != null ) {
            if( items.size() > position )
                return items.get(position);
        }

        return null;
    }

    //  ======================================================================================
    //  ======================================================================================

    public class CastItemViewHolder extends RecyclerView.ViewHolder {

        public TextView titleField;
        public ImageView imageView;

        //  ==================================================================================

        public CastItemViewHolder(View view) {
            super(view);

            titleField = (TextView) view.findViewById(R.id.titleField);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

}
