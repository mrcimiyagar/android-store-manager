package kasper.android.store_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.models.memory.Item;
import kasper.android.store_manager.models.memory.ItemType;

/**
 * Created by keyhan1376 on 12/19/2017.
 */

public class ItemTypesAdapter extends RecyclerView.Adapter<ItemTypesAdapter.ItemVH> {

    private List<ItemType> itemTypes;

    public ItemTypesAdapter(List<ItemType> itemTypes) {
        this.itemTypes = itemTypes;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_items, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        ItemType itemType = this.itemTypes.get(position);

        holder.nameTV.setText(itemType.getTitle());
        holder.priceTV.setText(itemType.getPrice() + "");
        holder.countTV.setText(itemType.getItemCount() + "");
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
            this.nameTV = itemView.findViewById(R.id.adapter_items_name_text_view);
            this.priceTV = itemView.findViewById(R.id.adapter_items_price_text_view);
            this.countTV = itemView.findViewById(R.id.adapter_items_count_text_view);
        }
    }
}
