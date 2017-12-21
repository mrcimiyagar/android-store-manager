package kasper.android.store_manager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.CategoriesAdapter;
import kasper.android.store_manager.adapters.CustomersAdapter;
import kasper.android.store_manager.callbacks.OnCategorySelectedListener;
import kasper.android.store_manager.callbacks.OnCustomerSelectedListener;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Customer;

public class CustomerPickerActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_picker);

        this.recyclerView = findViewById(R.id.activity_customer_picker_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.recyclerView.addItemDecoration(new LinearDecoration((int) Core.getInstance().dpToPx(16), (int) Core.getInstance().dpToPx(16)));
        this.recyclerView.setAdapter(new CustomersAdapter(Core.getInstance().getDatabaseHelper().getCustomers()
                , new OnCustomerSelectedListener() {
            @Override
            public void customerSelected(Customer customer) {
                setResult(RESULT_OK, new Intent().putExtra("customer", customer));
                CustomerPickerActivity.this.finish();
            }
        }));
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        this.finish();
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }
}
