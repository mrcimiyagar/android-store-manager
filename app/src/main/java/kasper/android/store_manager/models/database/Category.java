package kasper.android.store_manager.models.database;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Category extends RealmObject {

    private int id;
    private String name;
    private RealmList<Category> categories;
    private RealmList<ItemType> itemTypes;
    private Category parentCategory;
    private boolean parent;

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

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    public RealmList<ItemType> getItemTypes() {
        return itemTypes;
    }

    public void setItemTypes(RealmList<ItemType> itemTypes) {
        this.itemTypes = itemTypes;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }
}
