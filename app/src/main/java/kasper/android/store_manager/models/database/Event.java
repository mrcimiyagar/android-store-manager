package kasper.android.store_manager.models.database;

import io.realm.RealmObject;

/**
 * Created by keyhan1376 on 12/24/2017.
 */

public class Event extends RealmObject {
    private int id;
    private String message;
    private long time;
    private short attachmentType;
    private String attachmentIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public short getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(short attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(String attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
