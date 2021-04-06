package com.daxia.utils.android.pro.eventbus;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Dongping Wang
 * date 2021/4/2
 */
public final class EventBusUtils {

    private static boolean isEventBusExist;

    static {
        try {
            isEventBusExist = Class.forName("org.greenrobot.eventbus.EventBus") != null;
        } catch (Exception e) {
            isEventBusExist = false;
        }
        Log.d("EventBusUtils", " isEventBusExist = " + isEventBusExist);
    }


    private EventBusUtils() {

    }

    public static void register(Object subscriber) {
        if (!isEventBusExist) {
            return;
        }
        if (subscriber != null && !isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        if (!isEventBusExist) {
            return;
        }
        if (isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static boolean isRegistered(Object subscriber) {
        if (!isEventBusExist) {
            return false;
        }
        return EventBus.getDefault().isRegistered(subscriber);
    }

    public static void post(Object event) {
        if (!isEventBusExist) {
            return;
        }
        EventBus.getDefault().post(event);
    }

    public static void postSticky(Object event) {
        if (!isEventBusExist) {
            return;
        }
        EventBus.getDefault().postSticky(event);
    }

    public static void cancelEventDelivery(Object event) {
        if (!isEventBusExist) {
            return;
        }
        if (event != null) {
            EventBus.getDefault().cancelEventDelivery(event);
        }
    }

    public static <T> T getStickyEvent(Class<T> eventType) {
        if (!isEventBusExist) {
            return null;
        }
        return EventBus.getDefault().getStickyEvent(eventType);
    }

    public static boolean hasSubscriberForEvent(Class<?> eventClass) {
        if (!isEventBusExist) {
            return false;
        }
        return EventBus.getDefault().hasSubscriberForEvent(eventClass);
    }

    public static <T> T removeStickyEvent(Class<T> eventType) {
        if (!isEventBusExist) {
            return null;
        }
        return EventBus.getDefault().removeStickyEvent(eventType);
    }

    public static boolean removeStickyEvent(Object event) {
        if (!isEventBusExist) {
            return false;
        }
        return EventBus.getDefault().removeStickyEvent(event);
    }
}
