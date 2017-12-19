package kasper.android.store_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.models.memory.Tag;

/**
 * Created by keyhan1376 on 12/10/2017.
 */

public class ItemsTagsAdapter extends RecyclerView.Adapter<ItemsTagsAdapter.TagVH> {

    private List<Tag> tags;

    public ItemsTagsAdapter(List<Tag> tags) {
        this.tags = tags;
        this.notifyDataSetChanged();
    }

    @Override
    public TagVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TagVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_items_tags, parent, false));
    }

    @Override
    public void onBindViewHolder(TagVH holder, int position) {

        Tag tag = this.tags.get(position);

        holder.nameTV.setText(tag.getName());
    }

    @Override
    public int getItemCount() {
        return this.tags.size();
    }

    class TagVH extends RecyclerView.ViewHolder {

        TextView nameTV;

        TagVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_items_tags_name_text_view);
        }
    }
}
