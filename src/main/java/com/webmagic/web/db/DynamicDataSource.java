package com.webmagic.web.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Xingyf
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final String DATA_SOURCE_A = "dataSourceA";
    public static final String DATA_SOURCE_B = "dataSourceB";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        return getCustomerType();
    }
}
