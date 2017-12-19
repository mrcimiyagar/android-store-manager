package kasper.android.store_manager.models.memory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class Order {

    private int id;
    private String title;
    private int customerId;

    public static Order getIntoMemory(kasper.android.store_manager.models.database.Order dOrder) {
        Order mOrder = new Order();
        mOrder.setId(dOrder.getId());
        mOrder.setTitle(dOrder.getTitle());
        mOrder.setCustomerId(dOrder.getCustomer().getId());
        return mOrder;
    }

    public static List<Order> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Order> dOrders) {
        List<Order> mOrders = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Order dOrder : dOrders) {
            Order mOrder = getIntoMemory(dOrder);
            mOrders.add(mOrder);
        }
        return mOrders;
    }

    public static List<Order> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Order> dOrders) {
        List<Order> mOrders = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Order dOrder : dOrders) {
            Order mOrder = getIntoMemory(dOrder);
            mOrders.add(mOrder);
        }
        return mOrders;
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

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}