package kasper.android.store_manager.fragments.lists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.ItemTypesAdapter;
import kasper.android.store_manager.callbacks.OnItemTypeSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.ItemType;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemTypesListFragment extends Fragment {

    RecyclerView recyclerView;

    int parentCategoryId = -1;
    public ItemTypesListFragment setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
        return this;
    }

    public ItemTypesListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_item_types_list, container, false);

        recyclerView = contentView.findViewById(R.id.fragment_items_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new LinearDecoration((int) Core.getInstance().dpToPx(16), (int) Core.getInstance().dpToPx(16)));

        List<ItemType> items;

        if (this.parentCategoryId >= 0) {
            items = Core.getInstance().getDatabaseHelper().getItemTypesByParentId(this.parentCategoryId);
        }
        else {
            items = Core.getInstance().getDatabaseHelper().getItemTypes();
        }

        recyclerView.setAdapter(new ItemTypesAdapter(items, new OnItemTypeSelectedListener() {
            @Override
            public void itemTypeSelected(ItemType itemType) {

            }
        }));

        return contentView;
    }
}
