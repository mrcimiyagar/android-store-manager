package kasper.android.store_manager.fragments.lists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kasper.android.store_manager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomersListFragment extends Fragment {

    RecyclerView recyclerView;

    public CustomersListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_customers_list, container, false);

        recyclerView = contentView.findViewById(R.id.fragment_customers_list_recycler_view);

        return contentView;
    }

}
