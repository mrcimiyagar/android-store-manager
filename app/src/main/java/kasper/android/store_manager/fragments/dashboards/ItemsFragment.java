package kasper.android.store_manager.fragments.dashboards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.adapters.ItemsTagsAdapter;
import kasper.android.store_manager.extras.HorizontalLinearDecoration;
import kasper.android.store_manager.models.memory.Tag;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment {

    CardView noTagsCV;
    RecyclerView tagsRV;

    TextView totalValueTV;
    TextView itemsTypesCountTV;
    TextView itemsUnitsCountTV;
    TextView categoriesCountTV;

    CardView openCatsCV;
    CardView openItemsCV;

    CircularProgressBar nearDeadLinePB;
    CircularProgressBar passedDeadLinePB;
    CircularProgressBar recentlyRegedPB;
    CircularProgressBar nearEndPB;

    TextView nearDeadLineTV;
    TextView passedDeadLineTV;
    TextView recentlyRegedTV;
    TextView nearEndTV;

    TextView nearDeadLineCountTV;
    TextView passedDeadLineCountTV;
    TextView recentlyRegedCountTV;
    TextView nearEndCountTV;

    LineChartView nearDeadLineCV;
    LineChartView passedDeadLineCV;
    LineChartView recentlyRegedCV;
    LineChartView nearEndCV;

    public ItemsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_items, container, false);

        this.initViews(contentView);
        this.initTags();
        this.initMainCard();
        this.initTimeCard();
        this.initLineChartView(nearDeadLineCV);
        this.initLineChartView(passedDeadLineCV);
        this.initLineChartView(recentlyRegedCV);
        this.initLineChartView(nearEndCV);
        this.initButtons();

        return contentView;
    }

    private void initViews(View contentView) {

        tagsRV = contentView.findViewById(R.id.fragment_items_tags_recycler_view);
        noTagsCV = contentView.findViewById(R.id.fragment_items_no_tag_exist_container);

        totalValueTV = contentView.findViewById(R.id.fragment_items_total_value_text_view);
        itemsTypesCountTV = contentView.findViewById(R.id.fragment_items_item_types_count_text_view);
        itemsUnitsCountTV = contentView.findViewById(R.id.fragment_items_item_units_count_text_view);
        categoriesCountTV = contentView.findViewById(R.id.fragment_items_categories_count_text_view);

        openItemsCV = contentView.findViewById(R.id.fragment_items_items_open_container);
        openCatsCV = contentView.findViewById(R.id.fragment_items_categories_open_container);

        nearDeadLinePB = contentView.findViewById(R.id.fragment_items_near_dead_line_progress_bar);
        passedDeadLinePB = contentView.findViewById(R.id.fragment_items_passed_dead_line_progress_bar);
        recentlyRegedPB = contentView.findViewById(R.id.fragment_items_recently_registered_progress_bar);
        nearEndPB = contentView.findViewById(R.id.fragment_items_near_end_progress_bar);

        nearDeadLineTV = contentView.findViewById(R.id.fragment_items_near_dead_line_text_view);
        passedDeadLineTV = contentView.findViewById(R.id.fragment_items_passed_dead_line_text_view);
        recentlyRegedTV = contentView.findViewById(R.id.fragment_items_recently_registered_text_view);
        nearEndTV = contentView.findViewById(R.id.fragment_items_near_end_text_view);

        nearDeadLineCountTV = contentView.findViewById(R.id.fragment_items_near_dead_line_count_text_view);
        passedDeadLineCountTV = contentView.findViewById(R.id.fragment_items_passed_dead_line_count_text_view);
        recentlyRegedCountTV = contentView.findViewById(R.id.fragment_items_recently_registered_count_text_view);
        nearEndCountTV = contentView.findViewById(R.id.fragment_items_near_end_count_text_view);

        nearDeadLineCV = contentView.findViewById(R.id.fragment_items_line_chart_view_1);
        passedDeadLineCV = contentView.findViewById(R.id.fragment_items_line_chart_view_2);
        recentlyRegedCV = contentView.findViewById(R.id.fragment_items_line_chart_view_3);
        nearEndCV = contentView.findViewById(R.id.fragment_items_line_chart_view_4);
    }

    private void initTags() {

        noTagsCV.setVisibility(View.GONE);

        tagsRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        tagsRV.addItemDecoration(new HorizontalLinearDecoration((int)(16 * getResources()
                .getDisplayMetrics().density),(int)(16 * getResources().getDisplayMetrics()
                .density)));

        List<Tag> tags = new ArrayList<>();
        for (int counter = 0; counter < 10; counter++) {
            Tag tag = new Tag();
            tag.setId(counter);
            tag.setName("#تگ " + counter);
            tags.add(tag);
        }

        tagsRV.setAdapter(new ItemsTagsAdapter(tags));
    }

    private void initMainCard() {

    }

    private void initTimeCard() {

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

    private void initButtons() {

        openItemsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ListsBookActivity.class).putExtra("list-index", 4));
            }
        });

        openCatsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), ListsBookActivity.class).putExtra("list-index", 3));
            }
        });
    }
}
