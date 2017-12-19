package kasper.android.store_manager.models.database;

import io.realm.RealmObject;

/**
 * Created by keyhan1376 on 12/10/2017.
 */

public class Tag extends RealmObject {

    private int id;
    private String name;

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
}
