package kasper.android.store_manager.fragments.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.CategoryDetailsActivity;
import kasper.android.store_manager.adapters.CategoriesAdapter;
import kasper.android.store_manager.callbacks.OnCategorySelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesListFragment extends Fragment {

    RecyclerView recyclerView;

    private int parentCategoryId = -1;
    public CategoriesListFragment setParentCategoryId(int id) {
        this.parentCategoryId = id;
        return this;
    }

    public CategoriesListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_categories_list, container, false);

        recyclerView = contentView.findViewById(R.id.fragment_categories_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new LinearDecoration((int) Core.getInstance().dpToPx(24), (int) Core.getInstance().dpToPx(16)));

        List<Category> categories;

        if (this.parentCategoryId >= 0) {

            categories = Core.getInstance().getDatabaseHelper().getCategoriesByParentId(this.parentCategoryId);
        }
        else {

            categories = Core.getInstance().getDatabaseHelper().getCategories();
        }

        recyclerView.setAdapter(new CategoriesAdapter(categories
                , new OnCategorySelectedListener() {
            @Override
            public void categorySelected(Category category) {

                startActivity(new Intent(getActivity(), CategoryDetailsActivity.class).putExtra("category", category));
            }
        }));

        return contentView;
    }
}
