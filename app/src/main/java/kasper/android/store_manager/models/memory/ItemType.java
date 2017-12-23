package kasper.android.store_manager.models.memory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import kasper.android.store_manager.models.database.*;
import kasper.android.store_manager.models.database.Category;

/**
 * Created by keyhan1376 on 12/21/2017.
 */

public class ItemType implements Serializable {

    private int id;
    private String title;
    private float price;
    private int categoryId;
    private int itemCount;

    public static ItemType getIntoMemory(kasper.android.store_manager.models.database.ItemType dItemType) {
        ItemType mItemType = new ItemType();
        mItemType.setId(dItemType.getId());
        mItemType.setCategoryId(dItemType.getCategory().getId());

        int tempItemCount = 0;

        for (kasper.android.store_manager.models.database.Item dItem : dItemType.getItems()) {
            tempItemCount += dItem.getCount();
        }

        mItemType.setItemCount(tempItemCount);
        mItemType.setTitle(dItemType.getTitle());
        mItemType.setPrice(dItemType.getPrice());
        return mItemType;
    }

    public static List<ItemType> getIntoMemory(RealmList<kasper.android.store_manager.models.database.ItemType> dItemTypes) {

        List<ItemType> mItemTypes = new ArrayList<>();

        for (kasper.android.store_manager.models.database.ItemType dItemType : dItemTypes) {
            ItemType mItemType = getIntoMemory(dItemType);
            mItemTypes.add(mItemType);
        }

        return mItemTypes;
    }

    public static List<ItemType> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.ItemType> dItemTypes) {

        List<ItemType> mItemTypes = new ArrayList<>();

        for (kasper.android.store_manager.models.database.ItemType dItemType : dItemTypes) {
            ItemType mItemType = getIntoMemory(dItemType);
            mItemTypes.add(mItemType);
        }

        return mItemTypes;
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

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
