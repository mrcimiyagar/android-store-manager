package kasper.android.store_manager.models.database;

import io.realm.RealmObject;

/**
 * Created by keyhan1376 on 12/21/2017.
 */

public class ItemType extends RealmObject {

    private int id;
    private String title;
    private float price;
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
