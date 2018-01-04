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

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.ItemTypesListActivity;
import kasper.android.store_manager.activities.ItemsListActivity;
import kasper.android.store_manager.activities.ListsBookActivity;
import kasper.android.store_manager.adapters.ItemsTagsAdapter;
import kasper.android.store_manager.behaviours.UpdatablePage;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.extras.HorizontalLinearDecoration;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.DayReport;
import kasper.android.store_manager.models.memory.Item;
import kasper.android.store_manager.models.memory.ItemType;
import kasper.android.store_manager.models.memory.Tag;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment implements UpdatablePage {

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
        this.initButtons();
        this.initContent();

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

        tagsRV.addItemDecoration(new HorizontalLinearDecoration((int) (16 * getResources()
                .getDisplayMetrics().density), (int) (16 * getResources().getDisplayMetrics()
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

    private void initContent() {

        List<ItemType> itemTypes = Core.getInstance().getDatabaseHelper().getItemTypes();
        List<Item> items = Core.getInstance().getDatabaseHelper().getItems();

        int totalItemsValue = 0;

        for (Item item : items) {
            totalItemsValue += item.getPrice() * item.getCount();
        }

        String totalValueStr = "";

        StringBuilder tempTotalValueStrBuilder = new StringBuilder(totalItemsValue + "");

        tempTotalValueStrBuilder = tempTotalValueStrBuilder.reverse();

        for (int counter = 0; counter < tempTotalValueStrBuilder.length(); counter += 3) {
            if (counter + 3 <= tempTotalValueStrBuilder.length()) {
                StringBuilder tempStrBuilder = new StringBuilder(tempTotalValueStrBuilder.substring(counter, counter + 3));
                tempStrBuilder = tempStrBuilder.append(',');
                totalValueStr = totalValueStr.concat(tempStrBuilder.toString());
            }
            else {
                StringBuilder tempStrBuilder = new StringBuilder(tempTotalValueStrBuilder.substring(counter));
                tempStrBuilder = tempStrBuilder.append(',');
                totalValueStr = totalValueStr.concat(tempStrBuilder.toString());
            }
        }

        if (totalValueStr.length() > 0) {
            if (totalValueStr.charAt(totalValueStr.length() - 1) == ',') {
                totalValueStr = totalValueStr.substring(0, totalValueStr.length() - 1);
            }
        }

        totalValueStr = new StringBuilder(totalValueStr).reverse().toString();

        totalValueTV.setText(totalValueStr);

        // ***

        itemsTypesCountTV.setText(itemTypes.size() + " نوع کالا");

        // ***

        int itemsUnitCount = 0;

        for (Item item : items) {
            itemsUnitCount += item.getCount();
        }

        itemsUnitsCountTV.setText(itemsUnitCount + " واحد کالا");

        // ***

        List<Category> categories = Core.getInstance().getDatabaseHelper().getCategories();

        categoriesCountTV.setText(categories.size() + " دسته کالا");

        // ***

        final ArrayList<Item> nearDeadlineItems = new ArrayList<>(Core.getInstance().getDatabaseHelper().getItemsNearDeadline());
        final ArrayList<Item> passedDeadlineItems = new ArrayList<>(Core.getInstance().getDatabaseHelper().getItemsPassedDeadline());
        final ArrayList<Item> recentlyRegedItems = new ArrayList<>(Core.getInstance().getDatabaseHelper().getItemsRecentlyReged());
        final ArrayList<ItemType> nearEndItemTypes = new ArrayList<>(Core.getInstance().getDatabaseHelper().getItemTypesNearEnd());

        int nearDeadlineCount = 0;

        for (Item item : nearDeadlineItems) {
            nearDeadlineCount += item.getCount();
        }

        if (itemsUnitCount == 0 || nearDeadlineCount == 0) {
            nearDeadLinePB.setProgress(0);
            nearDeadLineTV.setText("0" + "%");
        }
        else {
            int progress = (int)((float)nearDeadlineCount / (float)itemsUnitCount * 100);
            nearDeadLinePB.setProgress(progress);
            nearDeadLineTV.setText(progress + "%");
        }

        nearDeadLineCountTV.setText(nearDeadlineCount + " واحد کالا");

        nearDeadLinePB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ItemsListActivity.class);
                intent.putExtra("title", "نزدیک انقضا");
                intent.putExtra("items", nearDeadlineItems);
                startActivity(intent);
            }
        });

        // ***

        int passedDeadlineCount = 0;

        for (Item item : passedDeadlineItems) {
            passedDeadlineCount += item.getCount();
        }

        if (itemsUnitCount == 0 || passedDeadlineCount == 0) {
            passedDeadLinePB.setProgress(0);
            passedDeadLineTV.setText("0" + "%");
        }
        else {
            int progress = (int)((float)passedDeadlineCount / (float)itemsUnitCount * 100);
            passedDeadLinePB.setProgress(progress);
            passedDeadLineTV.setText(progress + "%");
        }

        passedDeadLineCountTV.setText(passedDeadlineCount + " واحد کالا");

        passedDeadLinePB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ItemsListActivity.class);
                intent.putExtra("title", "گدشته از انقضا");
                intent.putExtra("items", passedDeadlineItems);
                startActivity(intent);
            }
        });

        // ***

        int recentlyRegedCount = 0;

        for (Item item : recentlyRegedItems) {
            recentlyRegedCount += item.getCount();
        }

        if (itemsUnitCount == 0 || recentlyRegedCount == 0) {
            recentlyRegedPB.setProgress(0);
            recentlyRegedTV.setText("0" + "%");
        }
        else {
            int progress = (int)((float)recentlyRegedCount / (float)itemsUnitCount * 100);
            recentlyRegedPB.setProgress(progress);
            recentlyRegedTV.setText(progress + "%");
        }

        recentlyRegedCountTV.setText(recentlyRegedCount + " واحد کالا");

        recentlyRegedPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ItemsListActivity.class);
                intent.putExtra("title", "تازه وارد شده");
                intent.putExtra("items", recentlyRegedItems);
                startActivity(intent);
            }
        });

        // ***

        int nearEndCount = nearEndItemTypes.size();

        if (itemTypes.size() == 0 || nearEndCount == 0) {
            nearEndPB.setProgress(0);
            nearEndTV.setText("0" + "%");
        }
        else {
            int progress = (int)((float)nearEndCount / (float)itemTypes.size() * 100);
            nearEndPB.setProgress(progress);
            nearEndTV.setText(progress + "%");
        }

        nearEndCountTV.setText(nearEndCount + " واحد کالا");

        nearEndPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ItemTypesListActivity.class);
                intent.putExtra("title", "موجودی کم");
                intent.putExtra("itemTypes", nearEndItemTypes);
                startActivity(intent);
            }
        });

        // ***

        List<DayReport> dayReports = Core.getInstance().getDatabaseHelper().getDayReports();

        float[] nearDeadlineArr = new float[dayReports.size()];
        float[] passedDeadlineArr = new float[dayReports.size()];
        float[] recentlyRegedArr = new float[dayReports.size()];
        float[] nearEndArr = new float[dayReports.size()];
        long[] times = new long[dayReports.size()];

        int counter = 0;

        for (DayReport dayReport : dayReports) {
            nearDeadlineArr[counter] = dayReport.getNearDeadline();
            passedDeadlineArr[counter] = dayReport.getPassedDeadline();
            recentlyRegedArr[counter] = dayReport.getRecentlyReged();
            nearEndArr[counter] = dayReport.getNearEnd();
            times[counter] = dayReport.getTime();
            counter++;
        }

        initLineChartView(nearDeadLineCV, nearDeadlineArr);
        initLineChartView(passedDeadLineCV, passedDeadlineArr);
        initLineChartView(recentlyRegedCV, recentlyRegedArr);
        initLineChartView(nearEndCV, nearEndArr);
    }

    private void initLineChartView(LineChartView mChart, float[] values) {

        if (values.length > 0) {

            String[] labels = new String[values.length];

            for (int counter = 0; counter < labels.length; counter++) {
                labels[counter] = "";
            }

            LineSet dataSet = new LineSet(labels, values);
            dataSet.setColor(getResources().getColor(R.color.colorAccent))
                    .setFill(getResources().getColor(R.color.colorPrimary));
            mChart.addData(dataSet);

            mChart.show(new Animation());
        }
    }

    @Override
    public void update() {

    }
}
