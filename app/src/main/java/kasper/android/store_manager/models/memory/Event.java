package kasper.android.store_manager.models.memory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by keyhan1376 on 12/24/2017.
 */

public class Event {

    public enum EventAttachmentTypes { ITEM_TYPE, ITEM, CATEGORY, ORDER, FACTORY, CUSTOMER }

    private int id;
    private String message;
    private long time;
    private EventAttachmentTypes attachmentType;
    private List<Integer> attachmentIds;

    public static Event getIntoMemory(kasper.android.store_manager.models.database.Event dEvent) {
        Event mEvent = new Event();
        mEvent.setId(dEvent.getId());
        mEvent.setMessage(dEvent.getMessage());
        mEvent.setTime(dEvent.getTime());
        mEvent.setAttachmentType(mapNumsToEventAttachmentTypes(dEvent.getAttachmentType()));
        mEvent.setAttachmentIds(convertStringToIds(dEvent.getAttachmentIds()));
        return mEvent;
    }

    public static List<Event> getIntoMemory(RealmList<kasper.android.store_manager.models.database.Event> dEvents) {
        List<Event> mEvents = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Event dEvent : dEvents) {
            Event mEvent = getIntoMemory(dEvent);
            mEvents.add(mEvent);
        }
        return mEvents;
    }

    public static List<Event> getIntoMemory(RealmResults<kasper.android.store_manager.models.database.Event> dEvents) {
        List<Event> mEvents = new ArrayList<>();
        for (kasper.android.store_manager.models.database.Event dEvent : dEvents) {
            Event mEvent = getIntoMemory(dEvent);
            mEvents.add(mEvent);
        }
        return mEvents;
    }

    private static Event.EventAttachmentTypes mapNumsToEventAttachmentTypes(short number) {
        switch (number) {
            case 1:
                return Event.EventAttachmentTypes.ITEM_TYPE;
            case 2:
                return Event.EventAttachmentTypes.ITEM;
            case 3:
                return Event.EventAttachmentTypes.CATEGORY;
            case 4:
                return Event.EventAttachmentTypes.ORDER;
            case 5:
                return Event.EventAttachmentTypes.FACTORY;
            case 6:
                return Event.EventAttachmentTypes.CUSTOMER;
            default:
                return Event.EventAttachmentTypes.CUSTOMER;
        }
    }

    private static List<Integer> convertStringToIds(String idsStr) {
        String[] idsParts = idsStr.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idsParts) {
            ids.add(Integer.parseInt(idStr));
        }
        return ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public EventAttachmentTypes getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(EventAttachmentTypes attachmentType) {
        this.attachmentType = attachmentType;
    }

    public List<Integer> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<Integer> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
