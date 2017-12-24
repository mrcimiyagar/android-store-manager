package kasper.android.store_manager.activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.PagerAdapter;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extensions.AppBarStateChangeListener;
import kasper.android.store_manager.fragments.lists.CategoriesListFragment;
import kasper.android.store_manager.fragments.lists.ItemTypesListFragment;
import kasper.android.store_manager.models.memory.Category;

public class CategoryDetailsActivity extends AppCompatActivity {

    AppBarLayout appBar;
    TextView maxTitleTV;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView minTitleTV;

    String[] pageTitles;
    Fragment[] pageFragments;

    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        this.category = (Category) this.getIntent().getExtras().getSerializable("category");

        this.initViews();
        this.initToolbar();
        this.initListeners();
        this.initPages();
    }

    private void initViews() {
        this.appBar = this.findViewById(R.id.activity_category_details_app_bar_layout);
        this.maxTitleTV = this.findViewById(R.id.activity_category_details_max_title_text_view);
        this.tabLayout = this.findViewById(R.id.activity_category_details_tab_layout);
        this.viewPager = this.findViewById(R.id.activity_category_details_view_pager);
        this.minTitleTV = this.findViewById(R.id.activity_category_details_app_bar_title_text_view);
    }

    private void initToolbar() {
        this.maxTitleTV.setText(this.category.getName());
        this.minTitleTV.setText(this.category.getName());
    }

    private void initListeners() {
        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    minTitleTV.animate().cancel();
                    minTitleTV.animate().alpha(1).setDuration(200).start();
                    maxTitleTV.animate().cancel();
                    maxTitleTV.animate().alpha(0).setDuration(200).start();
                }
                else {
                    minTitleTV.animate().cancel();
                    minTitleTV.animate().alpha(0).setDuration(200).start();
                    maxTitleTV.animate().cancel();
                    maxTitleTV.animate().alpha(1).setDuration(200).start();
                }
            }
        });
    }

    private void initPages() {
        this.pageTitles = new String[] {
                "کالا ها", "دسته ها"
        };

        this.pageFragments = new Fragment[] {
                new ItemTypesListFragment().setParentCategoryId(category.getId()),
                new CategoriesListFragment().setParentCategoryId(category.getId())
        };

        this.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), pageTitles, pageFragments));
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.viewPager.setCurrentItem(1);
    }

    public void onBackBtnClicked(View view) {
        this.onBackPressed();
    }
}
