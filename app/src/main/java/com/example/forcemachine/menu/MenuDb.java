package com.example.forcemachine.menu;

public class MenuDb {
    private int id;
    private String tableId;
    private String menuName;
    private String menuPrice;
    private int menuCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    public MenuDb(int id, String tableId, String menuName, String menuPrice, int menuCount) {
        this.id = id;
        this.tableId = tableId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCount = menuCount;
    }
}
