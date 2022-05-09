package edu.qc.seclass.fim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    Context c;
    ArrayList<String> product_name, product_brand, product_qty, product_store, product_sqft, product_color, product_price, product_type, product_size;
    clickListener listclick;


    RecycleAdapter(Context c, ArrayList pn,
                       ArrayList pb, ArrayList qty,
                       ArrayList sqft, ArrayList store,
                       ArrayList pc, ArrayList pp,
                       ArrayList pt, ArrayList ps, clickListener lc) {
        this.c = c;
        product_name = pn;
        product_brand = pb;
        product_qty = qty;
        product_sqft = sqft;
        product_store = store;
        product_color = pc;
        product_price = pp;
        product_type = pt;
        product_size = ps;
        listclick = lc;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(c);
        return new ViewHolder(inflate.inflate(R.layout.search_result_display_recycle_row, parent, false), listclick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.product_name.setText(String.valueOf(product_name.get(position)));
        holder.product_brand.setText(String.valueOf(product_brand.get(position)));
        //holder.product_qty.setText(String.valueOf(product_qty.get(position)));
        holder.product_store.setText(String.valueOf(product_store.get(position)));
        holder.product_sqft.setText(String.valueOf(product_sqft.get(position)));

    }

    @Override
    public int getItemCount() {
        return product_brand.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView product_name;
        TextView product_brand;
        TextView product_qty;
        TextView product_store;
        TextView product_sqft;
        clickListener listen;
        public ViewHolder(@NonNull View itemView, clickListener l) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_brand = itemView.findViewById(R.id.product_brand);
            //product_qty = itemView.findViewById(R.id.product_qty);
            product_store = itemView.findViewById(R.id.product_store);
            product_sqft = itemView.findViewById(R.id.product_sqft_total);
            listen = l;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listen.onListClick(getAdapterPosition());
        }
    }
    public interface clickListener{
        void onListClick(int pos);
    }
}
