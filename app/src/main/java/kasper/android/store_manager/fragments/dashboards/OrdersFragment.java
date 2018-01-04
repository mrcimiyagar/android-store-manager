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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.adapters.CategoriesAdapter;
import kasper.android.store_manager.adapters.ItemsTagsAdapter;
import kasper.android.store_manager.behaviours.UpdatablePage;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.HorizontalLinearDecoration;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Customer;
import kasper.android.store_manager.models.memory.Factory;
import kasper.android.store_manager.models.memory.Item;
import kasper.android.store_manager.models.memory.Order;
import kasper.android.store_manager.models.memory.Tag;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment implements UpdatablePage {


    private RecyclerView tagsRV;
    private CardView noTagsCV;

    private TextView totalValueTV;
    private TextView factoriesCountTV;
    private TextView ordersCountTV;
    private TextView customersCountTV;

    private TextView confirmedPriceProgressTV;
    private CircularProgressBar confirmedPriceProgressPB;
    private TextView confirmedPriceCountTV;

    private TextView arrivedProgressTV;
    private CircularProgressBar arrivedProgressPB;
    private TextView arrivedCountTV;

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
        this.initTags();
        this.initButtons();
        this.initContent();

        return contentView;
    }

    private void initViews(View contentView) {

        noTagsCV = contentView.findViewById(R.id.fragment_orders_no_tag_exist_container);
        tagsRV = contentView.findViewById(R.id.fragment_orders_tags_recycler_view);

        totalValueTV = contentView.findViewById(R.id.fragment_orders_total_value_text_view);
        factoriesCountTV = contentView.findViewById(R.id.fragment_orders_factories_count_text_view);
        ordersCountTV = contentView.findViewById(R.id.fragment_orders_orders_count_text_view);
        customersCountTV = contentView.findViewById(R.id.fragment_orders_customers_count_text_view);

        confirmedPriceProgressTV = contentView.findViewById(R.id.fragment_orders_confirmed_price_text_view);
        confirmedPriceProgressPB = contentView.findViewById(R.id.fragment_orders_confirmed_price_progress_bar);
        confirmedPriceCountTV = contentView.findViewById(R.id.fragment_orders_confirmed_price_count_text_view);

        arrivedProgressTV = contentView.findViewById(R.id.fragment_orders_arrived_text_view);
        arrivedProgressPB = contentView.findViewById(R.id.fragment_orders_arrived_progress_bar);
        arrivedCountTV = contentView.findViewById(R.id.fragment_orders_arrived_count_text_view);

        openFactoriesCV = contentView.findViewById(R.id.fragment_orders_factories_open_container);
        openCustomersCV = contentView.findViewById(R.id.fragment_orders_customers_open_container);
        openOrdersCV = contentView.findViewById(R.id.fragment_orders_orders_open_container);
    }

    private void initTags() {

        noTagsCV.setVisibility(View.GONE);

        tagsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        tagsRV.addItemDecoration(new HorizontalLinearDecoration((int)(16 * getResources()
                .getDisplayMetrics().density),(int)(16 * getResources().getDisplayMetrics()
                .density)));

        List<Tag> tags = new ArrayList<>();
        for (int counter = 0; counter < 10; counter++) {
            Tag tag = new Tag();
            tag.setId(counter);
            tag.setName("#تگ " + counter);
            tags.add(tag);
        }

        tagsRV.setAdapter(new ItemsTagsAdapter(tags));
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

    private void initContent() {

        List<Order> orders = Core.getInstance().getDatabaseHelper().getOrders();
        List<Customer> customers = Core.getInstance().getDatabaseHelper().getCustomers();
        List<Factory> factories = Core.getInstance().getDatabaseHelper().getFactories();

        int totalItemsValue = 0;

        /*for (Order item : orders) {
            totalItemsValue += item.getPrice() * item.getCount();
        }*/

        String totalValueStr = "";

        StringBuilder tempTotalValueStrBuilder = new StringBuilder(totalItemsValue + "");

        tempTotalValueStrBuilder = tempTotalValueStrBuilder.reverse();

        for (int counter = 0; counter < tempTotalValueStrBuilder.length(); counter += 3) {
            if (counter + 3 <= tempTotalValueStrBuilder.length()) {
                StringBuilder tempStrBuilder = new StringBuilder(tempTotalValueStrBuilder.substring(counter, counter + 3));
                tempStrBuilder = tempStrBuilder.append(',');
                totalValueStr = totalValueStr.concat(tempStrBuilder.toString());
            }
            else {
                StringBuilder tempStrBuilder = new StringBuilder(tempTotalValueStrBuilder.substring(counter));
                tempStrBuilder = tempStrBuilder.append(',');
                totalValueStr = totalValueStr.concat(tempStrBuilder.toString());
            }
        }

        if (totalValueStr.length() > 0) {
            if (totalValueStr.charAt(totalValueStr.length() - 1) == ',') {
                totalValueStr = totalValueStr.substring(0, totalValueStr.length() - 1);
            }
        }

        totalValueStr = new StringBuilder(totalValueStr).reverse().toString();

        totalValueTV.setText(totalValueStr);
    }

    @Override
    public void update() {

    }
}
