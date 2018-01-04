package kasper.android.store_manager.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.DashboardActivity;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.callbacks.OnCustomerSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.Customer;

/**
 * Created by keyhan1376 on 12/21/2017.
 */

public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.CustomerVH> {

    AppCompatActivity activity;
    private List<Customer> customers;
    private OnCustomerSelectedListener customerSelectionCallback;

    private boolean deleteAbility = false;

    public CustomersAdapter(List<Customer> customers, OnCustomerSelectedListener callback) {
        this.customers = customers;
        this.customerSelectionCallback = callback;
        this.deleteAbility = false;
        this.notifyDataSetChanged();
    }

    public CustomersAdapter(AppCompatActivity activity, List<Customer> customers, OnCustomerSelectedListener callback) {
        this.activity = activity;
        this.customers = customers;
        this.customerSelectionCallback = callback;
        this.deleteAbility = true;
        this.notifyDataSetChanged();
    }

    @Override
    public CustomerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomerVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_customers, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomerVH holder, final int position) {
        final Customer customer = this.customers.get(position);

        holder.nameTV.setText(customer.getName());
        holder.totalOrdersCountTV.setText(customer.getOrderCount() + "");
        holder.activeOrdersCountTV.setText(customer.getActiveOrderCount() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customerSelectionCallback.customerSelected(customer);
            }
        });

        if (deleteAbility) {
            holder.deleteBtn.setVisibility(View.VISIBLE);
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Core.getInstance().getDatabaseHelper().deleteCustomer(customer.getId());
                    ((ListsBookActivity) activity).notifyDatabaseChange(0);
                    customers.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
        else {
            holder.deleteBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.customers.size();
    }

    class CustomerVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView activeOrdersCountTV;
        TextView totalOrdersCountTV;
        ImageButton deleteBtn;

        CustomerVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_customers_name_text_view);
            this.totalOrdersCountTV = itemView.findViewById(R.id.adapter_customers_total_order_count_text_view);
            this.activeOrdersCountTV = itemView.findViewById(R.id.adapter_customers_active_order_count_text_view);
            this.deleteBtn = itemView.findViewById(R.id.adapter_customers_delete_button);
        }
    }
}
