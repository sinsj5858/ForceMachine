package com.example.forcemachine.table;

import java.util.List;

public class RestaurantStatusRequest {
    private String restaurantName;
    private List<TableStatus> tableStatusList;

    public RestaurantStatusRequest(String restaurantName, List<TableStatus> tableStatusList) {
        this.restaurantName = restaurantName;
        this.tableStatusList = tableStatusList;
    }
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<TableStatus> getTableStatusList() {
        return tableStatusList;
    }

    public void setTableStatusList(List<TableStatus> tableStatusList) {
        this.tableStatusList = tableStatusList;
    }
}
