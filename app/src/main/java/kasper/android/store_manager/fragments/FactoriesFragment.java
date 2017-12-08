package kasper.android.store_manager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kasper.android.store_manager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FactoriesFragment extends Fragment {

    public FactoriesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_factories, container, false);
    }
}
