package kasper.android.store_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.callbacks.OnCategorySelectedListener;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Item;

/**
 * Created by keyhan1376 on 12/10/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CatVH> {

    List<Category> categories;
    OnCategorySelectedListener selectCallback;

    public CategoriesAdapter(List<Category> categories) {
        this.categories = categories;
        this.notifyDataSetChanged();
    }

    public CategoriesAdapter(List<Category> categories, OnCategorySelectedListener selectCallback) {
        this.categories = categories;
        this.selectCallback = selectCallback;
        this.notifyDataSetChanged();
    }

    @Override
    public CatVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_categories, parent, false));
    }

    @Override
    public void onBindViewHolder(CatVH holder, int position) {
        final Category category = this.categories.get(position);

        holder.nameTV.setText(category.getName());
        holder.categoriesCountTV.setText(category.getCategoryCount() + " دسته");
        holder.iTypeCountTV.setText(category.getItemTypeCount() + " نوع کالا");
        holder.iUnitCountTV.setText(category.getItemUnitCount() + " واحد کالا");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectCallback != null) {
                    selectCallback.categorySelected(category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    class CatVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView categoriesCountTV;
        TextView iTypeCountTV;
        TextView iUnitCountTV;

        CatVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_categories_name_text_view);
            this.categoriesCountTV = itemView.findViewById(R.id.adapter_categories_category_count);
            this.iTypeCountTV = itemView.findViewById(R.id.adapter_categories_item_type_count);
            this.iUnitCountTV = itemView.findViewById(R.id.adapter_categories_item_unit_count);
        }
    }
}
