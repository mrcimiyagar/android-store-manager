package kasper.android.store_manager.helpers;

import android.content.Context;
import android.graphics.Paint;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.log.RealmLog;
import kasper.android.store_manager.models.memory.DayReport;
import kasper.android.store_manager.models.memory.Event;
import kasper.android.store_manager.models.memory.ItemType;
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

    public int addCategory(String name, Category parentCategory) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Category.class);

        kasper.android.store_manager.models.database.Category dCategory =
                realm.createObject(kasper.android.store_manager.models.database.Category.class);
        dCategory.setId(id);
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

        return id;
    }

    public int addItem(ItemType itemType, int count, String barcode, long deadlineTime) {

        long currentMillis = System.currentTimeMillis();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Item.class);

        kasper.android.store_manager.models.database.ItemType dItemType =
                realm.where(kasper.android.store_manager.models.database.ItemType.class)
                .equalTo("id", itemType.getId()).findFirst();

        kasper.android.store_manager.models.database.Item dItem =
                realm.createObject(kasper.android.store_manager.models.database.Item.class);
        dItem.setId(id);
        dItem.setItemType(dItemType);
        dItem.setCount(count);
        dItem.setDeadLineTime(deadlineTime);
        dItem.setLastModifiedTime(currentMillis);
        dItem.setRegisterTime(currentMillis);

        dItemType.getItems().add(dItem);

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addItemType(String name, float price, Category category) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.ItemType.class);

        kasper.android.store_manager.models.database.ItemType dItemType =
                realm.createObject(kasper.android.store_manager.models.database.ItemType.class);

        dItemType.setId(id);
        dItemType.setTitle(name);
        dItemType.setPrice(price);

        if (category != null) {
            kasper.android.store_manager.models.database.Category dParentCategory =
                    realm.where(kasper.android.store_manager.models.database.Category.class)
                            .equalTo("id", category.getId()).findFirst();
            dItemType.setCategory(dParentCategory);
            dParentCategory.getItemTypes().add(dItemType);
        }
        else {
            dItemType.setCategory(null);
        }

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addOrder(String name, Customer mCustomer) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Order.class);

        kasper.android.store_manager.models.database.Order dOrder =
                realm.createObject(kasper.android.store_manager.models.database.Order.class);
        dOrder.setId(id);
        dOrder.setTitle(name);

        kasper.android.store_manager.models.database.Customer dCustomer =
                realm.where(kasper.android.store_manager.models.database.Customer.class)
                .equalTo("id", mCustomer.getId()).findFirst();

        dOrder.setCustomer(dCustomer);
        dOrder.setActive(true);

        dCustomer.getOrders().add(dOrder);

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addFactory(String name, String phoneNumber, String email, String address) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Factory.class);

        kasper.android.store_manager.models.database.Factory dFactory =
                realm.createObject(kasper.android.store_manager.models.database.Factory.class);

        dFactory.setId(id);
        dFactory.setName(name);
        dFactory.setPhoneNumber(phoneNumber);
        dFactory.setEmail(email);
        dFactory.setAddress(address);

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addCustomer(String name, String phoneNumber, String email, String address) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Customer.class);

        kasper.android.store_manager.models.database.Customer dCustomer =
                realm.createObject(kasper.android.store_manager.models.database.Customer.class);

        dCustomer.setId(id);
        dCustomer.setName(name);
        dCustomer.setPhoneNumber(phoneNumber);
        dCustomer.setEmail(email);
        dCustomer.setAddress(address);

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addTag(String name) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Tag.class);

        kasper.android.store_manager.models.database.Tag dTag =
                realm.createObject(kasper.android.store_manager.models.database.Tag.class);
        dTag.setId(id);
        dTag.setName(name);

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addEvent(String message, Event.EventAttachmentTypes attType, List<Integer> attIds) {

        long time = System.currentTimeMillis();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.Event.class);

        kasper.android.store_manager.models.database.Event dEvent =
                realm.createObject(kasper.android.store_manager.models.database.Event.class);
        dEvent.setId(id);
        dEvent.setMessage(message);
        dEvent.setTime(time);
        dEvent.setAttachmentType(mapEventAttachmentTypesToNums(attType));
        dEvent.setAttachmentIds(convertIdListToString(attIds));

        realm.commitTransaction();
        realm.close();

        return id;
    }

    public int addEvent(String message, Event.EventAttachmentTypes attType, Integer attId) {

        List<Integer> ids = new ArrayList<>();
        ids.add(attId);

        return addEvent(message, attType, ids);
    }

    public void addEvent(String message) {
        long time = System.currentTimeMillis();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Event dEvent =
                realm.createObject(kasper.android.store_manager.models.database.Event.class);
        dEvent.setId(generateId(realm, Event.class));
        dEvent.setMessage(message);
        dEvent.setTime(time);
        dEvent.setAttachmentType((short) 0);
        dEvent.setAttachmentIds("");

        realm.commitTransaction();
        realm.close();
    }

    public int addDayReport(int nearDeadline, int passedDeadline, int recentlyReged, int nearEnd) {

        long time = System.currentTimeMillis();

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int id = generateId(realm, kasper.android.store_manager.models.database.DayReport.class);

        kasper.android.store_manager.models.database.DayReport dDayReport =
                realm.createObject(kasper.android.store_manager.models.database.DayReport.class);
        dDayReport.setId(id);
        dDayReport.setNearDeadline(nearDeadline);
        dDayReport.setPassedDeadline(passedDeadline);
        dDayReport.setRecentlyReged(recentlyReged);
        dDayReport.setNearEnd(nearEnd);
        dDayReport.setTime(time);

        realm.commitTransaction();
        realm.close();

        return id;
    }

    // ***

    public void deleteCategory(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        kasper.android.store_manager.models.database.Category dCategory = realm
                .where(kasper.android.store_manager.models.database.Category.class)
                .equalTo("id", id).findFirst();

        if (dCategory != null) {
            deleteCategoryInOpenDb(dCategory);
        }

        realm.commitTransaction();
        realm.close();
    }

    private void deleteCategoryInOpenDb(kasper.android.store_manager.models.database.Category dCategory) {
        while (dCategory.getItemTypes().size() > 0) {
            deleteItemTypeInOpenDb(dCategory.getItemTypes().get(0));
        }
        while (dCategory.getCategories().size() > 0) {
            deleteCategoryInOpenDb(dCategory.getCategories().get(0));
        }
        dCategory.deleteFromRealm();
    }

    private void deleteItemTypeInOpenDb(kasper.android.store_manager.models.database.ItemType dItemType) {
        while (dItemType.getItems().size() > 0) {
            dItemType.getItems().get(0).deleteFromRealm();
        }
        dItemType.deleteFromRealm();
    }

    public void deleteItemType(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        kasper.android.store_manager.models.database.ItemType dItemType = realm
                .where(kasper.android.store_manager.models.database.ItemType.class)
                .equalTo("id", id).findFirst();
        if (dItemType != null) {
            deleteItemTypeInOpenDb(dItemType);
        }
        realm.commitTransaction();
        realm.close();
    }

    public void deleteItem(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        kasper.android.store_manager.models.database.Item dItem = realm
                .where(kasper.android.store_manager.models.database.Item.class)
                .equalTo("id", id).findFirst();
        if (dItem != null) {
            dItem.deleteFromRealm();
        }
        realm.commitTransaction();
        realm.close();
    }

    public void deleteOrder(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        kasper.android.store_manager.models.database.Order dOrder =
                realm.where(kasper.android.store_manager.models.database.Order.class)
                .equalTo("id", id).findFirst();
        if (dOrder != null) {
            dOrder.deleteFromRealm();
        }
        realm.commitTransaction();
        realm.close();
    }

    public void deleteFactory(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        kasper.android.store_manager.models.database.Factory dFactory =
                realm.where(kasper.android.store_manager.models.database.Factory.class)
                .equalTo("id", id).findFirst();
        if (dFactory != null) {
            dFactory.deleteFromRealm();
        }
        realm.commitTransaction();
        realm.close();
    }

    public void deleteCustomer(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        kasper.android.store_manager.models.database.Customer customer =
                realm.where(kasper.android.store_manager.models.database.Customer.class)
                        .equalTo("id", id).findFirst();
        if (customer != null) {
            while (customer.getOrders().size() > 0) {
                customer.getOrders().get(0).deleteFromRealm();
            }
            customer.deleteFromRealm();
        }
        realm.commitTransaction();
        realm.close();
    }

    public void deleteTag(int id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        kasper.android.store_manager.models.database.Tag dTag = realm
                .where(kasper.android.store_manager.models.database.Tag.class)
                .equalTo("id", id).findFirst();
        if (dTag != null) {
            dTag.deleteFromRealm();
        }
        realm.commitTransaction();
        realm.close();
    }

    private short mapEventAttachmentTypesToNums(Event.EventAttachmentTypes eventAttachmentType) {
        switch (eventAttachmentType) {
            case ITEM_TYPE:
                return 1;
            case ITEM:
                return 2;
            case CATEGORY:
                return 3;
            case ORDER:
                return 4;
            case FACTORY:
                return 5;
            case CUSTOMER:
                return 6;
            default:
                return 0;
        }
    }

    private String convertIdListToString(List<Integer> ids) {
        StringBuilder attIdsStr = new StringBuilder("");
        for (int attId : ids) {
            attIdsStr = attIdsStr.append(attId).append(",");
        }
        if (attIdsStr.charAt(attIdsStr.length() - 1) == ',') {
            attIdsStr.deleteCharAt(attIdsStr.length() - 1);
        }
        return attIdsStr.toString();
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

    public List<ItemType> getItemTypes() {

        List<ItemType> mItemTypes;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.ItemType> dItemTypes =
                realm.where(kasper.android.store_manager.models.database.ItemType.class)
                .findAll();

        mItemTypes = ItemType.getIntoMemory(dItemTypes);

        realm.close();

        return mItemTypes;
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

    public List<Event> getEvents() {

        List<kasper.android.store_manager.models.memory.Event> mEvents;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.Event> dEvents =
                realm.where(kasper.android.store_manager.models.database.Event.class)
                        .findAllSorted("time", Sort.DESCENDING);

        mEvents = Event.getIntoMemory(dEvents);

        realm.close();

        return mEvents;
    }

    public List<DayReport> getDayReports() {

        List<DayReport> mDayReports = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.DayReport> dDayReports =
                realm.where(kasper.android.store_manager.models.database.DayReport.class)
                .findAllSorted("time", Sort.DESCENDING);

        int counter = 20;

        for (kasper.android.store_manager.models.database.DayReport dDayReport : dDayReports) {
            DayReport mDayReport = DayReport.getIntoMemory(dDayReport);
            mDayReports.add(mDayReport);
            counter--;
            if (counter <= 0) {
                break;
            }
        }

        realm.close();

        return mDayReports;
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

    public List<ItemType> getItemTypesByParentId(int parentCategoryId) {

        List<ItemType> mItems;

        Realm realm = Realm.getDefaultInstance();

        RealmList<kasper.android.store_manager.models.database.ItemType> dItemTypes =
                realm.where(kasper.android.store_manager.models.database.Category.class)
                .equalTo("id", parentCategoryId).findFirst().getItemTypes();

        mItems = ItemType.getIntoMemory(dItemTypes);

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

    public Category getCategoryById(int categoryId) {

        Realm realm = Realm.getDefaultInstance();

        kasper.android.store_manager.models.database.Category dCategory =
                realm.where(kasper.android.store_manager.models.database.Category.class)
                        .equalTo("id", categoryId).findFirst();

        Category category = Category.getIntoMemory(dCategory);

        realm.close();

        return category;
    }

    // ***

    public List<Item> getItemsNearDeadline() {

        List<Item> result;

        Realm realm = Realm.getDefaultInstance();

        long currentMillis = System.currentTimeMillis();

        long nearDeadlineMillis = currentMillis + 86400000;

        RealmResults<kasper.android.store_manager.models.database.Item> dItems =
                realm.where(kasper.android.store_manager.models.database.Item.class)
                        .lessThanOrEqualTo("deadLineTime", nearDeadlineMillis)
                        .greaterThan("deadLineTime", currentMillis).findAll();

        result = Item.getIntoMemory(dItems);

        realm.close();

        return result;
    }

    public List<Item> getItemsPassedDeadline() {

        List<Item> result;

        Realm realm = Realm.getDefaultInstance();

        long currentMillis = System.currentTimeMillis();

        RealmResults<kasper.android.store_manager.models.database.Item> dItems =
                realm.where(kasper.android.store_manager.models.database.Item.class)
                        .notEqualTo("deadLineTime",0)
                        .lessThanOrEqualTo("deadLineTime", currentMillis).findAll();

        result = Item.getIntoMemory(dItems);

        realm.close();

        return result;
    }

    public List<Item> getItemsRecentlyReged() {

        List<Item> result;

        Realm realm = Realm.getDefaultInstance();

        long currentMillis = System.currentTimeMillis();

        long nearDeadlineMillis = currentMillis - 86400000;

        RealmResults<kasper.android.store_manager.models.database.Item> dItems =
                realm.where(kasper.android.store_manager.models.database.Item.class)
                        .greaterThan("registerTime", nearDeadlineMillis).findAll();

        result = Item.getIntoMemory(dItems);

        realm.close();

        return result;
    }

    public List<ItemType> getItemTypesNearEnd() {

        List<ItemType> result = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();

        RealmResults<kasper.android.store_manager.models.database.ItemType> dItemTypes =
                realm.where(kasper.android.store_manager.models.database.ItemType.class)
                .findAll();

        for (kasper.android.store_manager.models.database.ItemType dItemType : dItemTypes) {
            int count = 0;
            for (kasper.android.store_manager.models.database.Item dItem : dItemType.getItems()) {
                count += dItem.getCount();
            }
            if (count < 10) {
                ItemType itemType = ItemType.getIntoMemory(dItemType);
                result.add(itemType);
            }
        }

        realm.close();

        return result;
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
