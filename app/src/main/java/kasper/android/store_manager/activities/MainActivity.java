package kasper.android.store_manager.activities;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.StorePagerAdapter;
import kasper.android.store_manager.extensions.AppBarStateChangeListener;
import kasper.android.store_manager.fragments.ClientsFragment;
import kasper.android.store_manager.fragments.FactoriesFragment;
import kasper.android.store_manager.fragments.ItemsFragment;

public class MainActivity extends AppCompatActivity {

    AppBarLayout appBar;
    TextView appBarTitleTV;
    TabLayout tabLayout;
    ViewPager viewPager;

    private String[] pageTitles;
    private Fragment[] pageFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.removeStatusBarBackground();
        this.initViews();
        this.initListeners();
        this.initPages();
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
    }

    private void initListeners() {
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    appBarTitleTV.animate().cancel();
                    appBarTitleTV.animate().alpha(1).setDuration(200).start();
                }
                else {
                    appBarTitleTV.animate().cancel();
                    appBarTitleTV.animate().alpha(0).setDuration(200).start();
                }
            }
        });
    }

    private void initPages() {

        pageTitles = new String[] {
                "کارخانه ها", "مشتریان", "کالا ها"
        };

        pageFragments = new Fragment[] {
                new FactoriesFragment(),
                new ClientsFragment(),
                new ItemsFragment()
        };

        viewPager.setAdapter(new StorePagerAdapter(getSupportFragmentManager(), pageTitles, pageFragments));
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(2);
    }
}
