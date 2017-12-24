package kasper.android.store_manager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.TimeLineAdapter;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.LinearDecoration;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        this.recyclerView = this.findViewById(R.id.activity_history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        int padding = (int)(16 * getResources().getDisplayMetrics().density);
        recyclerView.addItemDecoration(new LinearDecoration(padding, padding));
        recyclerView.setAdapter(new TimeLineAdapter(Core.getInstance().getDatabaseHelper().getEvents()));
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }
}
