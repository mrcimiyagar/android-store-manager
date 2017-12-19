package kasper.android.store_manager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kasper.android.store_manager.R;

public class NotificationsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        recyclerView = this.findViewById(R.id.activity_notifications_recycler_view);
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }
}
