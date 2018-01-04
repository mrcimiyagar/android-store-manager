package kasper.android.store_manager.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kasper.android.store_manager.behaviours.UpdatablePage;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;
    private UpdatablePage[] fragments;

    public PagerAdapter(FragmentManager fm, String[] titles, UpdatablePage[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) this.fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return this.fragments.length;
    }
}