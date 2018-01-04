package kasper.android.store_manager.activities;

import android.graphics.Paint;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.PagerAdapter;
import kasper.android.store_manager.behaviours.UpdatablePage;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extensions.AppBarStateChangeListener;
import kasper.android.store_manager.fragments.lists.CategoriesListFragment;
import kasper.android.store_manager.fragments.lists.CustomersListFragment;
import kasper.android.store_manager.fragments.lists.FactoriesListFragment;
import kasper.android.store_manager.fragments.lists.ItemTypesListFragment;
import kasper.android.store_manager.fragments.lists.OrdersListFragment;

public class ListsBookActivity extends AppCompatActivity {

    AppBarLayout appBar;
    CardView logoContainer;

    TabLayout tabLayout;
    ViewPager viewPager;

    UpdatablePage[] pageFragments;
    String[] pageTitles;

    ImageView logoIV;

    int pageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_book);

        this.collectArguments();
        this.initViews();
        this.initDecoration();
        this.initListeners();
        this.initPages();
    }

    private void collectArguments() {
        this.pageIndex = getIntent().getExtras().getInt("list-index");
    }

    private void initViews() {
        this.appBar = this.findViewById(R.id.activity_lists_book_app_bar);
        this.logoContainer = this.findViewById(R.id.activity_lists_book_logo_container);
        this.tabLayout = this.findViewById(R.id.activity_lists_book_tab_layout);
        this.viewPager = this.findViewById(R.id.activity_lists_book_view_pager);
        this.logoIV = this.findViewById(R.id.activity_lists_book_logo_image_view);
    }

    private void initDecoration() {
        this.tabLayout.getChildAt(0).setPadding(Core.getInstance().getScreenWidth() / 2
                , 0, Core.getInstance().getScreenWidth() / 2, 0);
    }

    private void initListeners() {

        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    logoContainer.animate().cancel();
                    logoContainer.animate().alpha(0).setDuration(200).start();
                }
                else {
                    logoContainer.animate().cancel();
                    logoContainer.animate().alpha(1).setDuration(200).start();
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0: {
                        logoIV.setImageResource(R.drawable.ic_customer);
                        break;
                    }
                    case 1: {
                        logoIV.setImageResource(R.drawable.ic_factory);
                        break;
                    }
                    case 2: {
                        logoIV.setImageResource(R.drawable.ic_order);
                        break;
                    }
                    case 3: {
                        logoIV.setImageResource(R.drawable.ic_category);
                        break;
                    }
                    case 4: {
                        logoIV.setImageResource(R.drawable.ic_item_type);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPages() {

        this.pageFragments = new UpdatablePage[] {
                new CustomersListFragment(),
                new FactoriesListFragment(),
                new OrdersListFragment(),
                new CategoriesListFragment(),
                new ItemTypesListFragment()
        };

        this.pageTitles = new String[] {
                "مشتری ها",
                "کارخانه ها",
                "سفارش ها",
                "دسته ها",
                "کالا ها"
        };

        this.viewPager.setAdapter(new PagerAdapter(this.getSupportFragmentManager(),  this.pageTitles, this.pageFragments));
        this.tabLayout.setupWithViewPager(this.viewPager);

        this.viewPager.setCurrentItem(this.pageIndex);

        this.viewPager.setOffscreenPageLimit(0);
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }

    public void notifyDatabaseChange(int index) {
        for (int counter = 0; counter < pageFragments.length; counter++) {
            if (counter != index) {
                pageFragments[counter].update();
            }
        }
    }
}
