package kasper.android.store_manager.models.database;

import io.realm.RealmObject;

public class Item extends RealmObject {

    private int id;
    private ItemType itemType;
    private int count;
    private long registerTime;
    private long deadLineTime;
    private long lastModifiedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    public long getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(long deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}