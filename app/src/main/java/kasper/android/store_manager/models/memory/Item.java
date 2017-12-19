package kasper.android.store_manager.models.memory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;
import kasper.android.store_manager.models.database.*;

public class Item {

    private int id;
    private String title;
    private float price;
    private int count;
    private int categoryId;
    private long registerTime;
    private long deadLineTime;
    private long lastModifiedTime;

    public static Item getIntoMemory(kasper.android.store_manager.models.database.Item dItem) {
        Item mItem = new Item();
        mItem.setId(dItem.getId());
        mItem.setTitle(dItem.getTitle());
        mItem.setPrice(dItem.getPrice());
        mItem.setCount(dItem.getCount());
        mItem.setCategoryId(dItem.getCategory().getId());
        mItem.setDeadLineTime(dItem.getDeadLineTime());
        mItem.setRegisterTime(dItem.getRegisterTime());
        mItem.setLastModifiedTime(dItem.getLastModifiedTime());
        return mItem;
    }

    public static List<Item> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Item> dItems) {
        List<Item> mItems = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Item dItem : dItems) {
            Item mItem = getIntoMemory(dItem);
            mItems.add(mItem);
        }
        return mItems;
    }

    public static List<Item> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Item> dItems) {
        List<Item> mItems = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Item dItem : dItems) {
            Item mItem = getIntoMemory(dItem);
            mItems.add(mItem);
        }
        return mItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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