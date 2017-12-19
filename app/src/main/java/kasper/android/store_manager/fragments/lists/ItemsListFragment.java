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
import kasper.android.store_manager.adapters.ItemsAdapter;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Item;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsListFragment extends Fragment {

    RecyclerView recyclerView;

    int parentCategoryId = -1;
    public ItemsListFragment setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
        return this;
    }

    public ItemsListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_items_list, container, false);

        recyclerView = contentView.findViewById(R.id.fragment_items_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new LinearDecoration((int) Core.getInstance().dpToPx(16), (int) Core.getInstance().dpToPx(16)));

        List<Item> items;

        if (this.parentCategoryId >= 0) {
            items = Core.getInstance().getDatabaseHelper().getItemsByParentId(this.parentCategoryId);
        }
        else {
            items = Core.getInstance().getDatabaseHelper().getItems();
        }

        recyclerView.setAdapter(new ItemsAdapter(items));

        return contentView;
    }
}
