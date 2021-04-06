package com.daxia.utils.android.collection;

import java.util.Collection;
import java.util.List;

/**
 * @author Dongping Wang
 * date 2021/4/2
 */
public final class CollectionUtils {

    private CollectionUtils() {

    }

    public static <E> int getListSize(List<E> list) {
        return getCollectionSize(list);
    }

    public static <E> int getCollectionSize(Collection<E> c) {
        return c == null ? 0 : c.size();
    }
}
