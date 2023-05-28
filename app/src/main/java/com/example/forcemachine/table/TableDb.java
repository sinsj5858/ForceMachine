package com.example.forcemachine.table;

public class TableDb {
    private int id;
    private String tableId;
    private Boolean tableExist;

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

    public Boolean getTableExist() {
        return tableExist;
    }

    public void setTableExist(Boolean tableExist) {
        this.tableExist = tableExist;
    }

    public TableDb(int id, String tableId, Boolean tableExist) {
        this.id = id;
        this.tableId = tableId;
        this.tableExist = tableExist;
    }
}
