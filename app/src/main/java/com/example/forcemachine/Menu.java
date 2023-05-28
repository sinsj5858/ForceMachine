package com.example.forcemachine;

public class Menu {
    private String menuName;
    private String menuPrice;
    private int menuCount; // 개별 메뉴의 개수

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

    public Menu(String menuName, String menuPrice, int menuCount) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCount = menuCount;
    }

    public void increaseCount() {
        menuCount++;
    }

    public void decreaseCount() {
        if (menuCount > 0) {
            menuCount--;
        }
    }
}