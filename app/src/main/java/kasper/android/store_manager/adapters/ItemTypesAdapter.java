package kasper.android.store_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.callbacks.OnItemTypeSelectedListener;
import kasper.android.store_manager.models.memory.ItemType;

/**
 * Created by keyhan1376 on 12/19/2017.
 */

public class ItemTypesAdapter extends RecyclerView.Adapter<ItemTypesAdapter.ItemVH> {

    private List<ItemType> itemTypes;
    private OnItemTypeSelectedListener callback;

    public ItemTypesAdapter(List<ItemType> itemTypes, OnItemTypeSelectedListener callback) {
        this.itemTypes = itemTypes;
        this.callback = callback;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_types, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        final ItemType itemType = this.itemTypes.get(position);

        holder.nameTV.setText(itemType.getTitle());
        holder.priceTV.setText(itemType.getPrice() + "");
        holder.countTV.setText(itemType.getItemCount() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.itemTypeSelected(itemType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemTypes.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView priceTV;
        TextView countTV;

        ItemVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_item_types_name_text_view);
            this.priceTV = itemView.findViewById(R.id.adapter_item_types_price_text_view);
            this.countTV = itemView.findViewById(R.id.adapter_item_types_count_text_view);
        }
    }
}
