package kasper.android.store_manager.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.List;

import kasper.android.store_manager.R;
import kasper.android.store_manager.activities.CategoryDetailsActivity;
import kasper.android.store_manager.core.Core;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Event;

/**
 * Created by keyhan1376 on 12/16/2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineVH> {

    private List<Event> events;

    public TimeLineAdapter(List<Event> events) {
        this.events = events;
        this.notifyDataSetChanged();
    }

    @Override
    public TimeLineVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_time_line, null);
        return new TimeLineVH(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public void onBindViewHolder(final TimeLineVH holder, int position) {

        final Event event = this.events.get(position);

        PersianCalendar persianCalendar = new PersianCalendar(event.getTime());

        holder.contentTV.setText(persianCalendar.getPersianShortDateTime() + "\n\n" + event.getMessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (event.getAttachmentType() == Event.EventAttachmentTypes.CATEGORY) {
                    if (event.getAttachmentIds().size() > 0) {
                        Category category = Core.getInstance().getDatabaseHelper()
                                .getCategoryById(event.getAttachmentIds().get(0));
                        holder.itemView.getContext().startActivity(new Intent(holder
                                .itemView.getContext(), CategoryDetailsActivity.class)
                                .putExtra("category", category));
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.events.size();
    }

    class TimeLineVH extends RecyclerView.ViewHolder {

        TimelineView mTimelineView;
        TextView contentTV;

        TimeLineVH(View itemView, int viewType) {
            super(itemView);
            mTimelineView = itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
            contentTV = itemView.findViewById(R.id.adapter_time_line_content_text_view);
        }
    }
}
