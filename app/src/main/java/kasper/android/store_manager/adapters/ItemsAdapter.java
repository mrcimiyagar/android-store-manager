package kasper.android.store_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.models.memory.Item;

/**
 * Created by keyhan1376 on 12/22/2017.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemVH> {

    private List<Item> items;

    public ItemsAdapter(List<Item> items) {
        this.items = items;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_items, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        Item item = this.items.get(position);
        holder.nameTV.setText(item.getTitle());
        holder.countTV.setText(item.getCount() + "");
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView countTV;

        ItemVH(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.adapter_items_name_text_view);
            countTV = itemView.findViewById(R.id.adapter_items_count_text_view);
        }
    }
}
