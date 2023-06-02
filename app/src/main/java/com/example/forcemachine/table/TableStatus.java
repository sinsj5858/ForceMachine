package com.example.forcemachine.table;

public class TableStatus {
    private String tableId;
    private boolean tableExist;

    public TableStatus(String tableId, boolean tableExist) {
        this.tableId = tableId;
        this.tableExist = tableExist;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public boolean isTableExist() {
        return tableExist;
    }

    public void setTableExist(boolean tableExist) {
        this.tableExist = tableExist;
    }
}
