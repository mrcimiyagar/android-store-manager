package kasper.android.store_manager.models.memory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class Factory {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private int itemCount;

    public static Factory getIntoMemory(kasper.android.store_manager.models.database.Factory dFactory) {
        Factory mFactory = new Factory();
        mFactory.setId(dFactory.getId());
        mFactory.setName(dFactory.getName());
        mFactory.setEmail(dFactory.getEmail());
        mFactory.setPhoneNumber(dFactory.getPhoneNumber());
        mFactory.setAddress(dFactory.getAddress());
        mFactory.setItemCount(dFactory.getItems().size());
        return mFactory;
    }

    public static List<Factory> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Factory> dFactories) {
        List<Factory> mFactories = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Factory dFactory : dFactories) {
            Factory mFactory = getIntoMemory(dFactory);
            mFactories.add(mFactory);
        }
        return mFactories;
    }

    public static List<Factory> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Factory> dFactories) {
        List<Factory> mFactories = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Factory dFactory : dFactories) {
            Factory mFactory = getIntoMemory(dFactory);
            mFactories.add(mFactory);
        }
        return mFactories;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
