package com.musala.drones.util;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class DroneUtils {

    public static boolean isEmpty(Collection list) {
        return CollectionUtils.isEmpty(list);
    }
}
