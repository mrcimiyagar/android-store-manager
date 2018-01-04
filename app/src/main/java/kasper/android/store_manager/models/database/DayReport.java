package kasper.android.store_manager.models.database;

import io.realm.RealmObject;

/**
 * Created by keyhan1376 on 12/26/2017.
 */

public class DayReport extends RealmObject {
    private int id;
    private int nearDeadline;
    private int passedDeadline;
    private int recentlyReged;
    private int nearEnd;
    private long time;

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
