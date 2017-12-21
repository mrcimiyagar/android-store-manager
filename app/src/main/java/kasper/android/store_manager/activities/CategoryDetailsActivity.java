package kasper.android.store_manager.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import kasper.android.store_manager.R;
import kasper.android.store_manager.adapters.PagerAdapter;
import kasper.android.store_manager.fragments.lists.CategoriesListFragment;
import kasper.android.store_manager.fragments.lists.ItemTypesListFragment;
import kasper.android.store_manager.models.memory.Category;

public class CategoryDetailsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    String[] pageTitles;
    Fragment[] pageFragments;

    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        this.category = (Category) this.getIntent().getExtras().getSerializable("category");

        this.initViews();
        this.initPages();
    }

    private void initViews() {
        this.tabLayout = this.findViewById(R.id.activity_category_details_tab_layout);
        this.viewPager = this.findViewById(R.id.activity_category_details_view_pager);
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
