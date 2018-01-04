package kasper.android.store_manager.fragments.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.CustomerPickerActivity;
import kasper.android.store_manager.adapters.CustomersAdapter;
import kasper.android.store_manager.behaviours.UpdatablePage;
import kasper.android.store_manager.callbacks.OnCustomerSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Customer;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomersListFragment extends Fragment implements UpdatablePage {

    RecyclerView recyclerView;

    public CustomersListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_customers_list, container, false);

        recyclerView = contentView.findViewById(R.id.fragment_customers_list_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.recyclerView.addItemDecoration(new LinearDecoration((int) Core.getInstance().dpToPx(16), (int) Core.getInstance().dpToPx(16)));

        update();

        return contentView;
    }

    @Override
    public void update() {

        this.recyclerView.setAdapter(new CustomersAdapter((AppCompatActivity) getActivity(), Core.getInstance().getDatabaseHelper().getCustomers()
                , new OnCustomerSelectedListener() {
            @Override
            public void customerSelected(Customer customer) {

            }
        }));
    }
}
