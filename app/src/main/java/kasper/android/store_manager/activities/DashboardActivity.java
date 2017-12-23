package kasper.android.store_manager.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.PagerAdapter;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extensions.AppBarStateChangeListener;
import kasper.android.store_manager.fragments.dashboards.ItemsFragment;
import kasper.android.store_manager.fragments.dashboards.OrdersFragment;

public class DashboardActivity extends AppCompatActivity {

    AppBarLayout appBar;
    TextView appBarTitleTV;
    LinearLayout profileOpenMiniView;

    FloatingActionButton addFAB;

    TabLayout tabLayout;
    ViewPager viewPager;

    String[] pageTitles;
    Fragment[] pageFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.removeStatusBarBackground();
        this.initViews();
        this.initListeners();
        this.initPages();
    }

    public void onCreateBtnClicked(View view) {

        View menuView = LayoutInflater.from(this).inflate(R.layout.activity_create_options, null, false);

        menuView.findViewById(R.id.create_menu_item_type_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashboardActivity.this, ItemTypeCreateActivity.class));
            }
        });

        menuView.findViewById(R.id.create_menu_item_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashboardActivity.this, ItemCreateActivity.class));
            }
        });

        menuView.findViewById(R.id.create_menu_category_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashboardActivity.this, CategoryCreateActivity.class));
            }
        });

        menuView.findViewById(R.id.create_menu_order_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashboardActivity.this, OrderCreateActivity.class));
            }
        });

        menuView.findViewById(R.id.create_menu_factory_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashboardActivity.this, FactoryCreateActivity.class));
            }
        });

        menuView.findViewById(R.id.create_menu_customer_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashboardActivity.this, CustomerCreateActivity.class));
            }
        });

        new BottomSheet.Builder(this)
                .setStyle(R.style.AppTheme_BottomSheet)
                .setView(menuView)
                .show();
    }

    public void onNotificationBtnClicked(View view) {
        startActivity(new Intent(this, NotificationsActivity.class));
    }

    public void onHistoryBtnClicked(View view) {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    public void onProfileBtnClicked(View view) {

    }

    private void removeStatusBarBackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private void initViews() {
        appBar = findViewById(R.id.activity_main_app_bar);
        appBarTitleTV = findViewById(R.id.activity_main_app_bar_title_text_view);
        tabLayout = findViewById(R.id.activity_main_tab_layout);
        viewPager = findViewById(R.id.activity_main_view_pager);
        addFAB = findViewById(R.id.activity_main_add_fab);
        profileOpenMiniView = findViewById(R.id.activity_main_profile_open_mini_view);
    }

    private void initListeners() {
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    appBarTitleTV.animate().cancel();
                    appBarTitleTV.animate().alpha(1).setDuration(200).start();
                    tabLayout.animate().cancel();
                    tabLayout.animate().x(Core.getInstance().dpToPx(32)).setDuration(200).start();
                    profileOpenMiniView.animate().cancel();
                    profileOpenMiniView.animate().alpha(1).setDuration(200).start();
                    addFAB.hide();
                }
                else {
                    appBarTitleTV.animate().cancel();
                    appBarTitleTV.animate().alpha(0).setDuration(200).start();
                    tabLayout.animate().cancel();
                    tabLayout.animate().x(Core.getInstance().getScreenWidth()
                            - Core.getInstance().dpToPx(40) - tabLayout.getMeasuredWidth())
                            .setDuration(200).start();
                    profileOpenMiniView.animate().cancel();
                    profileOpenMiniView.animate().alpha(0).setDuration(200).start();
                    addFAB.show();
                }
            }
        });
    }

    private void initPages() {

        this.pageFragments = new Fragment[] {
                new OrdersFragment(),
                new ItemsFragment()
        };

        this.pageTitles = new String[] {
                //"سفارش ها",
                //"کالا ها"
                "", ""
        };

        this.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), pageTitles, pageFragments));
        this.tabLayout.setupWithViewPager(this.viewPager);

        this.tabLayout.getTabAt(0).setIcon(R.drawable.ic_order);
        this.tabLayout.getTabAt(1).setIcon(R.drawable.ic_item);

        this.viewPager.setCurrentItem(1);
    }
}
