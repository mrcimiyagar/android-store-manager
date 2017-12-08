package kasper.android.store_manager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;

import java.util.Random;

import kasper.android.store_manager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment {

    public ItemsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_items, container, false);

        LineChartView[] lineCharts = new LineChartView[4];

        lineCharts[0] = contentView.findViewById(R.id.fragment_items_line_chart_view_1);
        lineCharts[1] = contentView.findViewById(R.id.fragment_items_line_chart_view_2);
        lineCharts[2] = contentView.findViewById(R.id.fragment_items_line_chart_view_3);
        lineCharts[3] = contentView.findViewById(R.id.fragment_items_line_chart_view_4);

        for (LineChartView lineChartView : lineCharts) {
            this.initLineChartView(lineChartView);
        }

        return contentView;
    }

    private void initLineChartView(LineChartView mChart) {

        String[] labels = new String[25];
        float[] values = new float[25];

        for (int counter = 0; counter < labels.length; counter++) {
            labels[counter] = counter % 5 == 0 ? counter + "" : "";
            values[counter] = (counter * 10 + 10) + new Random().nextInt(100) - 50;
        }

        LineSet dataSet = new LineSet(labels, values);
        dataSet.setColor(getResources().getColor(R.color.colorAccent))
                .setFill(getResources().getColor(R.color.colorPrimary));
        mChart.addData(dataSet);

        mChart.show(new Animation());
    }
}
