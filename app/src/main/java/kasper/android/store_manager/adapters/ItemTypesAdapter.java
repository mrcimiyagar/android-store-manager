package kasper.android.store_manager.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.callbacks.OnItemTypeSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.ItemType;

/**
 * Created by keyhan1376 on 12/19/2017.
 */

public class ItemTypesAdapter extends RecyclerView.Adapter<ItemTypesAdapter.ItemVH> {

    private AppCompatActivity activity;
    private List<ItemType> itemTypes;
    private OnItemTypeSelectedListener callback;
    private boolean deleteAbility = false;

    public ItemTypesAdapter(List<ItemType> itemTypes, OnItemTypeSelectedListener callback) {
        this.itemTypes = itemTypes;
        this.callback = callback;
        this.deleteAbility = false;
        this.notifyDataSetChanged();
    }

    public ItemTypesAdapter(AppCompatActivity activity, List<ItemType> itemTypes, OnItemTypeSelectedListener callback) {
        this.activity = activity;
        this.itemTypes = itemTypes;
        this.callback = callback;
        this.deleteAbility = true;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_types, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemVH holder, final int position) {
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

        if (deleteAbility) {
            holder.deleteBTN.setVisibility(View.VISIBLE);
            holder.deleteBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Core.getInstance().getDatabaseHelper().deleteItemType(itemType.getId());
                    ((ListsBookActivity) activity).notifyDatabaseChange(4);
                    itemTypes.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
        else {
            holder.deleteBTN.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.itemTypes.size();
    }

    class ItemVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView priceTV;
        TextView countTV;
        ImageButton deleteBTN;

        ItemVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_item_types_name_text_view);
            this.priceTV = itemView.findViewById(R.id.adapter_item_types_price_text_view);
            this.countTV = itemView.findViewById(R.id.adapter_item_types_count_text_view);
            this.deleteBTN = itemView.findViewById(R.id.adapter_item_types_delete_button);
        }
    }
}
