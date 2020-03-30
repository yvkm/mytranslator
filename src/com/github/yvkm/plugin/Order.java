package com.github.yvkm.plugin;

public interface Order {

    public static final int HIGH_PRIORITY = Integer.MIN_VALUE;
    public static final int DEFAULT_PRIORITY = 0;
    public static final int LOW_PRIORITY = Integer.MAX_VALUE;

    int getOrder();
}
