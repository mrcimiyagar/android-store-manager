package kasper.android.store_manager.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.vipulasri.timelineview.TimelineView;

import kasper.android.store_manager.R;

/**
 * Created by keyhan1376 on 12/16/2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineVH> {

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
    public void onBindViewHolder(TimeLineVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class TimeLineVH extends RecyclerView.ViewHolder {

        TimelineView mTimelineView;

        TimeLineVH(View itemView, int viewType) {
            super(itemView);
            mTimelineView = itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);
        }
    }
}
