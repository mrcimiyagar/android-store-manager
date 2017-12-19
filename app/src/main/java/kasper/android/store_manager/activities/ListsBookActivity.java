package kasper.android.store_manager.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.PagerAdapter;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.fragments.lists.CategoriesListFragment;
import kasper.android.store_manager.fragments.lists.CustomersListFragment;
import kasper.android.store_manager.fragments.lists.FactoriesListFragment;
import kasper.android.store_manager.fragments.lists.ItemsListFragment;
import kasper.android.store_manager.fragments.lists.OrdersListFragment;

public class ListsBookActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    Fragment[] pageFragments;
    String[] pageTitles;

    int pageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_book);

        this.collectArguments();
        this.initViews();
        this.initDecoration();
        this.initPages();
    }

    private void collectArguments() {
        this.pageIndex = getIntent().getExtras().getInt("list-index");
    }

    private void initViews() {
        this.tabLayout = this.findViewById(R.id.activity_lists_book_tab_layout);
        this.viewPager = this.findViewById(R.id.activity_lists_book_view_pager);
    }

    private void initDecoration() {
        this.tabLayout.getChildAt(0).setPadding(Core.getInstance().getScreenWidth() / 2
                , 0, Core.getInstance().getScreenWidth() / 2, 0);
    }

    private void initPages() {

        this.pageFragments = new Fragment[] {
                new CustomersListFragment(),
                new FactoriesListFragment(),
                new OrdersListFragment(),
                new CategoriesListFragment(),
                new ItemsListFragment()
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
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }
}
