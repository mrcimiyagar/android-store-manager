package kasper.android.store_manager.models.memory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by keyhan1376 on 12/10/2017.
 */

public class Tag {

    private int id;
    private String name;

    public static Tag getIntoMemory(kasper.android.store_manager.models.database.Tag dTag) {
        Tag mTag = new Tag();
        mTag.setId(dTag.getId());
        mTag.setName(dTag.getName());
        return mTag;
    }

    public static List<Tag> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Tag> dTags) {
        List<Tag> mTags = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Tag dTag : dTags) {
            Tag mTag = getIntoMemory(dTag);
            mTags.add(mTag);
        }
        return mTags;
    }

    public static List<Tag> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Tag> dTags) {
        List<Tag> mTags = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Tag dTag : dTags) {
            Tag mTag = getIntoMemory(dTag);
            mTags.add(mTag);
        }
        return mTags;
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
}
