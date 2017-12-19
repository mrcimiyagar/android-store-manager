package kasper.android.store_manager.models.memory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;
import kasper.android.store_manager.models.database.*;

public class Category implements Serializable {

    private int id;
    private String name;
    private int categoryCount;
    private int itemTypeCount;
    private int itemUnitCount;
    private int parentCategoryId;

    public static Category getIntoMemory(kasper.android.store_manager.models.database.Category dCategory) {
        Category mCategory = new Category();
        mCategory.setId(dCategory.getId());
        mCategory.setName(dCategory.getName());
        mCategory.setItemTypeCount(dCategory.getItems().size());

        int itemUnitCount = 0;
        for (kasper.android.store_manager.models.database.Item dItem : dCategory.getItems()) {
            itemUnitCount += dItem.getCount();
        }
        mCategory.setItemUnitCount(itemUnitCount);

        mCategory.setCategoryCount(dCategory.getCategories().size());
        if (!dCategory.isParent()) {
            mCategory.setParentCategoryId(dCategory.getParentCategory().getId());
        }
        else {
            mCategory.setParentCategoryId(-1);
        }
        return mCategory;
    }

    public static List<Category> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Category> dCategories) {
        List<Category> mCategories = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Category dCategory : dCategories) {
            Category mCategory = getIntoMemory(dCategory);
            mCategories.add(mCategory);
        }
        return mCategories;
    }

    public static List<Category> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Category> dCategories) {
        List<Category> mCategories = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Category dCategory : dCategories) {
            Category mCategory = getIntoMemory(dCategory);
            mCategories.add(mCategory);
        }
        return mCategories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    public int getItemTypeCount() {
        return itemTypeCount;
    }

    public void setItemTypeCount(int itemTypeCount) {
        this.itemTypeCount = itemTypeCount;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public int getItemUnitCount() {
        return itemUnitCount;
    }

    public void setItemUnitCount(int itemUnitCount) {
        this.itemUnitCount = itemUnitCount;
    }

}
