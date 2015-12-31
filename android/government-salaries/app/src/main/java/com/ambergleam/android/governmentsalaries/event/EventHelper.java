package com.ambergleam.android.governmentsalaries.event;


import de.greenrobot.event.EventBus;
import timber.log.Timber;

public class EventHelper {

    public EventHelper() {
    }

    public static void postEvent(Object event) {
        if (!(event instanceof BaseEvent)) {
            Timber.e("Event not of correct type:" + event.getClass().toString());
        }
        getEventBus().post(event);
    }

    public static void registerSubscriber(Object subscriber) {
        if (!getEventBus().isRegistered(subscriber)) {
            getEventBus().register(subscriber);
        }
    }

    public static void unregisterSubscriber(Object subscriber) {
        if (getEventBus().isRegistered(subscriber)) {
            getEventBus().unregister(subscriber);
        }
    }

    private static EventBus getEventBus() {
        return EventBus.getDefault();
    }

}