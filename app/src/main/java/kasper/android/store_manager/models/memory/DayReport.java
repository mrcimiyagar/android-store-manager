package kasper.android.store_manager.models.memory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by keyhan1376 on 12/26/2017.
 */

public class DayReport {
    private int id;
    private int nearDeadline;
    private int passedDeadline;
    private int recentlyReged;
    private int nearEnd;
    private long time;

    public static DayReport getIntoMemory(kasper.android.store_manager.models.database.DayReport dDayReport) {
        DayReport dayReport = new DayReport();
        dayReport.setId(dDayReport.getId());
        dayReport.setNearDeadline(dDayReport.getNearDeadline());
        dayReport.setPassedDeadline(dDayReport.getPassedDeadline());
        dayReport.setRecentlyReged(dDayReport.getRecentlyReged());
        dayReport.setNearEnd(dDayReport.getNearEnd());
        dayReport.setTime(dDayReport.getTime());
        return dayReport;
    }

    public static List<DayReport> getIntoMemory(RealmList<kasper.android.store_manager.models.database.DayReport> dDayReports) {
        List<DayReport> mDayReports = new ArrayList<>();
        for (kasper.android.store_manager.models.database.DayReport dDayReport : dDayReports) {
            DayReport mDayReport = getIntoMemory(dDayReport);
            mDayReports.add(mDayReport);
        }
        return mDayReports;
    }

    public static List<DayReport> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.DayReport> dDayReports) {
        List<DayReport> mDayReports = new ArrayList<>();
        for (kasper.android.store_manager.models.database.DayReport dDayReport : dDayReports) {
            DayReport mDayReport = getIntoMemory(dDayReport);
            mDayReports.add(mDayReport);
        }
        return mDayReports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNearDeadline() {
        return nearDeadline;
    }

    public void setNearDeadline(int nearDeadline) {
        this.nearDeadline = nearDeadline;
    }

    public int getPassedDeadline() {
        return passedDeadline;
    }

    public void setPassedDeadline(int passedDeadline) {
        this.passedDeadline = passedDeadline;
    }

    public int getRecentlyReged() {
        return recentlyReged;
    }

    public void setRecentlyReged(int recentlyReged) {
        this.recentlyReged = recentlyReged;
    }

    public int getNearEnd() {
        return nearEnd;
    }

    public void setNearEnd(int nearEnd) {
        this.nearEnd = nearEnd;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
