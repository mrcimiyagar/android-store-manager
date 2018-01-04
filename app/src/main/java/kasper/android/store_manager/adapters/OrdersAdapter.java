package kasper.android.store_manager.adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.callbacks.OnOrderSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.Order;

/**
 * Created by keyhan1376 on 12/21/2017.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderVH> {

    private AppCompatActivity activity;
    private List<Order> orders;
    private OnOrderSelectedListener callback;

    public OrdersAdapter(AppCompatActivity activity, List<Order> orders, OnOrderSelectedListener callback) {
        this.activity = activity;
        this.orders = orders;
        this.callback = callback;
        this.notifyDataSetChanged();
    }

    @Override
    public OrderVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(final OrderVH holder, final int position) {
        final Order order = this.orders.get(position);

        holder.nameTV.setText(order.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.orderSelected(order);
            }
        });

        holder.deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Core.getInstance().getDatabaseHelper().deleteOrder(order.getId());
                ((ListsBookActivity) activity).notifyDatabaseChange(2);
                orders.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.orders.size();
    }

    class OrderVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        ImageButton deleteBTN;

        OrderVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_orders_name_text_view);
            this.deleteBTN = itemView.findViewById(R.id.adapter_orders_delete_button);
        }
    }
}
