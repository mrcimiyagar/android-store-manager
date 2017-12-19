package kasper.android.store_manager.helpers;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import kasper.android.store_manager.models.memory.Tag;
import kasper.android.store_manager.models.memory.Category;
import kasper.android.store_manager.models.memory.Customer;
import kasper.android.store_manager.models.memory.Item;
import kasper.android.store_manager.models.memory.Order;
import kasper.android.store_manager.models.memory.Factory;

/**
 * Created by keyhan1376 on 12/10/2017.
 */

public class DatabaseHelper {

    public DatabaseHelper(Context context) {
        Realm.init(context);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        realm.commitTransaction();
        realm.close();
    }

    public void addCategory(String name, Category parentCategory) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Category dCategory =
                realm.createObject(kasper.android.store_manager.models.database.Category.class);
        dCategory.setId(generateId(realm, kasper.android.store_manager.models.database.Category.class));
        dCategory.setName(name);

        if (parentCategory != null) {
            kasper.android.store_manager.models.database.Category dParentCategory =
                    realm.where(kasper.android.store_manager.models.database.Category.class)
                            .equalTo("id", parentCategory.getId()).findFirst();
            dCategory.setParentCategory(dParentCategory);
            dParentCategory.getCategories().add(dCategory);
            dCategory.setParent(false);
        }
        else {
            dCategory.setParentCategory(null);
            dCategory.setParent(true);
        }

        realm.commitTransaction();
        realm.close();
    }

    public void addItem(String name, int price, int count, Category category, String barcode, long deadlineTime) {

        long currentMillis = System.currentTimeMillis();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Item dItem =
                realm.createObject(kasper.android.store_manager.models.database.Item.class);
        dItem.setId(generateId(realm, kasper.android.store_manager.models.database.Item.class));
        dItem.setTitle(name);
        dItem.setPrice(price);
        dItem.setCount(count);
        dItem.setDeadLineTime(deadlineTime);
        dItem.setLastModifiedTime(currentMillis);
        dItem.setRegisterTime(currentMillis);

        if (category != null) {
            kasper.android.store_manager.models.database.Category dParentCategory =
                    realm.where(kasper.android.store_manager.models.database.Category.class)
                            .equalTo("id", category.getId()).findFirst();
            dItem.setCategory(dParentCategory);
            dParentCategory.getItems().add(dItem);
        }
        else {
            dItem.setCategory(null);
        }

        realm.commitTransaction();
        realm.close();
    }

    public void addOrder(String name, Customer mCustomer) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Order dOrder =
                realm.createObject(kasper.android.store_manager.models.database.Order.class);
        dOrder.setId(generateId(realm, kasper.android.store_manager.models.database.Order.class));
        dOrder.setTitle(name);

        kasper.android.store_manager.models.database.Customer dCustomer =
                realm.where(kasper.android.store_manager.models.database.Customer.class)
                .equalTo("id", mCustomer.getId()).findFirst();

        dOrder.setCustomer(dCustomer);

        realm.commitTransaction();
        realm.close();
    }

    public void addFactory(String name, String phoneNumber, String email, String address) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Factory dFactory =
                realm.createObject(kasper.android.store_manager.models.database.Factory.class);

        dFactory.setId(generateId(realm, kasper.android.store_manager.models.database.Factory.class));
        dFactory.setName(name);
        dFactory.setPhoneNumber(phoneNumber);
        dFactory.setEmail(email);
        dFactory.setAddress(address);

        realm.commitTransaction();
        realm.close();
    }

    public void addCustomer(String name, String phoneNumber, String email, String address) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Customer dCustomer =
                realm.createObject(kasper.android.store_manager.models.database.Customer.class);

        dCustomer.setId(generateId(realm, kasper.android.store_manager.models.database.Customer.class));
        dCustomer.setName(name);
        dCustomer.setPhoneNumber(phoneNumber);
        dCustomer.setEmail(email);
        dCustomer.setAddress(address);

        realm.commitTransaction();
        realm.close();
    }

    public void addTag(String name) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Tag dTag =
                realm.createObject(kasper.android.store_manager.models.database.Tag.class);
        dTag.setName(name);

        realm.commitTransaction();
        realm.close();
    }

    // ***

    public List<Category> getCategories() {

        List<Category> mCategories;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Category> dCategories =
                realm.where(kasper.android.store_manager.models.database.Category.class).findAll();

        mCategories = Category.getIntoMemory(dCategories);

        realm.close();

        return mCategories;
    }

    public List<Item> getItems() {

        List<Item> mItems;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Item> dItems =
                realm.where(kasper.android.store_manager.models.database.Item.class).findAll();

        mItems = Item.getIntoMemory(dItems);

        realm.close();

        return mItems;
    }

    public List<Order> getOrders() {

        List<Order> mOrders;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Order> dOrders =
                realm.where(kasper.android.store_manager.models.database.Order.class).findAll();

        mOrders = Order.getIntoMemory(dOrders);

        realm.close();

        return mOrders;
    }

    public List<Factory> getFactories() {

        List<Factory> mFactories;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Factory> dFactories =
                realm.where(kasper.android.store_manager.models.database.Factory.class).findAll();

        mFactories = Factory.getIntoMemory(dFactories);

        realm.close();

        return mFactories;
    }

    public List<Customer> getCustomers() {

        List<Customer> mCustomers;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Customer> dCustomers =
                realm.where(kasper.android.store_manager.models.database.Customer.class).findAll();

        mCustomers = Customer.getIntoMemory(dCustomers);

        realm.close();

        return mCustomers;
    }

    public List<Tag> getTags() {

        List<Tag> mTags;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Tag> dTags =
                realm.where(kasper.android.store_manager.models.database.Tag.class).findAll();

        mTags = Tag.getIntoMemory(dTags);

        realm.close();

        return mTags;
    }

    // ***

    public List<Category> getCategoriesByParentId(int parentCategoryId) {

        List<Category> mCategories;

        Realm realm = Realm.getDefaultInstance();

        RealmList<kasper.android.store_manager.models.database.Category> dCategories =
                realm.where(kasper.android.store_manager.models.database.Category.class)
                .equalTo("id", parentCategoryId).findFirst().getCategories();

        mCategories = Category.getIntoMemory(dCategories);

        realm.close();

        return mCategories;
    }

    public List<Category> getParentCategories() {

        List<Category> mCategories;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Category> dCategories =
                realm.where(kasper.android.store_manager.models.database.Category.class)
                .equalTo("parent", true).findAll();

        mCategories = Category.getIntoMemory(dCategories);

        realm.close();

        return mCategories;
    }

    public List<Item> getItemsByParentId(int parentCategoryId) {

        List<Item> mItems;

        Realm realm = Realm.getDefaultInstance();

        RealmList<kasper.android.store_manager.models.database.Item> dItems =
                realm.where(kasper.android.store_manager.models.database.Category.class)
                .equalTo("id", parentCategoryId).findFirst().getItems();

        mItems = Item.getIntoMemory(dItems);

        return mItems;
    }

    public List<Item> getItemsByFactoryId(int factoryId) {

        List<Item> mItems;

        Realm realm = Realm.getDefaultInstance();

        RealmList<kasper.android.store_manager.models.database.Item> dItems =
                realm.where(kasper.android.store_manager.models.database.Factory.class)
                .equalTo("id", factoryId).findFirst().getItems();

        mItems = Item.getIntoMemory(dItems);

        return mItems;
    }

    // ***

    private int generateId(Realm realm, Class classObj) {
        Number maxGeneratedId = realm.where(classObj)
                .findAll().max("id");
        int newId;
        if (maxGeneratedId == null) {
            newId = 1;
        }
        else {
            newId = maxGeneratedId.intValue() + 1;
        }
        return newId;
    }
}