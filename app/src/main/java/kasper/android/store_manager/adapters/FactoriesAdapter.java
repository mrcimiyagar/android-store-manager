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
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.callbacks.OnFactorySelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.Factory;

/**
 * Created by keyhan1376 on 12/21/2017.
 */

public class FactoriesAdapter extends RecyclerView.Adapter<FactoriesAdapter.FactoryVH> {

    private AppCompatActivity activity;
    private List<Factory> factories;
    private OnFactorySelectedListener callback;

    public FactoriesAdapter(AppCompatActivity activity, List<Factory> factories, OnFactorySelectedListener callback) {
        this.activity = activity;
        this.factories = factories;
        this.callback = callback;
        this.notifyDataSetChanged();
    }

    @Override
    public FactoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FactoryVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_factories, parent, false));
    }

    @Override
    public void onBindViewHolder(FactoryVH holder, final int position) {
        final Factory factory = this.factories.get(position);

        holder.nameTV.setText(factory.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.factorySelected(factory);
            }
        });

        holder.deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Core.getInstance().getDatabaseHelper().deleteFactory(factory.getId());
                ((ListsBookActivity) activity).notifyDatabaseChange(1);
                factories.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.factories.size();
    }

    class FactoryVH extends RecyclerView.ViewHolder {

        TextView nameTV;
        ImageButton deleteBTN;

        FactoryVH(View itemView) {
            super(itemView);
            this.nameTV = itemView.findViewById(R.id.adapter_factories_name_text_view);
            this.deleteBTN = itemView.findViewById(R.id.adapter_factories_delete_button);
        }
    }
}
