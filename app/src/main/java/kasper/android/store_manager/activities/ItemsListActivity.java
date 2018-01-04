package kasper.android.store_manager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.ItemsAdapter;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;
import kasper.android.store_manager.models.memory.Item;

public class ItemsListActivity extends AppCompatActivity {

    TextView titleTV;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        String title = getIntent().getExtras().getString("title");
        ArrayList<Item> items = (ArrayList<Item>) getIntent().getExtras().getSerializable("items");

        this.titleTV = this.findViewById(R.id.activity_items_list_title_text_view);
        this.recyclerView = this.findViewById(R.id.activity_items_list_recycler_view);

        titleTV.setText(title);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new LinearDecoration((int)Core.getInstance().dpToPx(8), 0));
        recyclerView.setAdapter(new ItemsAdapter(items));
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }
}
