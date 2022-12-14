package kasper.android.store_manager.models.memory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;
import kasper.android.store_manager.models.database.*;

public class Customer implements Serializable {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private int orderCount;
    private int activeOrderCount;

    public static Customer getIntoMemory(kasper.android.store_manager.models.database.Customer dCustomer) {
        Customer mCustomer = new Customer();
        mCustomer.setId(dCustomer.getId());
        mCustomer.setName(dCustomer.getName());
        mCustomer.setPhoneNumber(dCustomer.getPhoneNumber());
        mCustomer.setEmail(dCustomer.getEmail());
        mCustomer.setAddress(dCustomer.getAddress());
        mCustomer.setOrderCount(dCustomer.getOrders().size());
        int activeOrders = 0;
        for (kasper.android.store_manager.models.database.Order dOrder : dCustomer.getOrders()) {
            if (dOrder.isActive()) {
                activeOrders++;
            }
        }
        mCustomer.setActiveOrderCount(activeOrders);
        return mCustomer;
    }

    public static List<Customer> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Customer> dCustomers) {
        List<Customer> mCustomers = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Customer dCustomer : dCustomers) {
            Customer mCustomer = getIntoMemory(dCustomer);
            mCustomers.add(mCustomer);
        }
        return mCustomers;
    }

    public static List<Customer> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Customer> dCustomers) {
        List<Customer> mCustomers = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Customer dCustomer : dCustomers) {
            Customer mCustomer = getIntoMemory(dCustomer);
            mCustomers.add(mCustomer);
        }
        return mCustomers;
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

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getActiveOrderCount() {
        return activeOrderCount;
    }

    public void setActiveOrderCount(int activeOrderCount) {
        this.activeOrderCount = activeOrderCount;
    }
}