package kasper.android.store_manager.fragments.lists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.CustomersAdapter;
import kasper.android.store_manager.adapters.OrdersAdapter;
import kasper.android.store_manager.behaviours.UpdatablePage;
import kasper.android.store_manager.callbacks.OnCustomerSelectedListener;
import kasper.android.store_manager.callbacks.OnOrderSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Customer;
import kasper.android.store_manager.models.memory.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersListFragment extends Fragment implements UpdatablePage {

    RecyclerView recyclerView;

    public OrdersListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_orders_list, container, false);

        recyclerView = contentView.findViewById(R.id.fragment_orders_list_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        this.recyclerView.addItemDecoration(new LinearDecoration((int) Core.getInstance().dpToPx(16), (int) Core.getInstance().dpToPx(16)));

        update();

        return contentView;
    }

    @Override
    public void update() {

        this.recyclerView.setAdapter(new OrdersAdapter((AppCompatActivity) getActivity(), Core.getInstance().getDatabaseHelper().getOrders()
                , new OnOrderSelectedListener() {
            @Override
            public void orderSelected(Order order) {

            }
        }));
    }
}
