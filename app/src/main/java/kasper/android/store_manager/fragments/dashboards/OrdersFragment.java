package kasper.android.store_manager.fragments.dashboards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.adapters.CategoriesAdapter;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    private CardView openFactoriesCV;
    private CardView openCustomersCV;
    private CardView openOrdersCV;

    public OrdersFragment() {

    }

    // نمودار تعداد سفارش ها - نمودار تعداد سفارش های موجود شده

    // نمودار تعداد سفارش های تازه ثبت شده

    // نمودار مشتریان تازه سفارش دهنده

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_orders, container, false);

        this.initViews(contentView);
        this.initButtons();

        return contentView;
    }

    private void initViews(View contentView) {
        openFactoriesCV = contentView.findViewById(R.id.fragment_orders_factories_open_container);
        openCustomersCV = contentView.findViewById(R.id.fragment_orders_customers_open_container);
        openOrdersCV = contentView.findViewById(R.id.fragment_orders_orders_open_container);
    }

    private void initButtons() {

        openCustomersCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ListsBookActivity.class).putExtra("list-index", 0));
            }
        });

        openFactoriesCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ListsBookActivity.class).putExtra("list-index", 1));
            }
        });

        openOrdersCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ListsBookActivity.class).putExtra("list-index", 2));
            }
        });
    }
}
