package com.example.forcemachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.forcemachine.menu.MenuDb;
import com.example.forcemachine.menu.MenuDbHelper;
import com.example.forcemachine.menu.MenuTableInfo;
import com.example.forcemachine.table.TableDb;
import com.example.forcemachine.table.TableDbHelper;
import com.example.forcemachine.table.TableInfo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String FIRST_RUN_KEY = "FirstRun";

    private Button table1;
    private Button table2;
    private Button table3;
    private Button table4;
    private Button table5;
    private Button table6;
    private Button table7;
    private Button table8;
    private Button table9;
    private TableDbHelper tableDbHelper;
    private MenuDbHelper menuDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tableDbHelper = new TableDbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        table1 = findViewById(R.id.table1);
        table2 = findViewById(R.id.table2);
        table3 = findViewById(R.id.table3);
        table4 = findViewById(R.id.table4);
        table5 = findViewById(R.id.table5);
        table6 = findViewById(R.id.table6);
        table7 = findViewById(R.id.table7);
        table8 = findViewById(R.id.table8);
        table9 = findViewById(R.id.table9);
        List<Button> tables = new ArrayList<>();
        tables.add(table1);
        tables.add(table2);
        tables.add(table3);
        tables.add(table4);
        tables.add(table5);
        tables.add(table6);
        tables.add(table7);
        tables.add(table8);
        tables.add(table9);

        String tableId1 = Integer.toString(table1.getId());
        String tableId2 = Integer.toString(table2.getId());
        String tableId3 = Integer.toString(table3.getId());
        String tableId4 = Integer.toString(table4.getId());
        String tableId5 = Integer.toString(table5.getId());
        String tableId6 = Integer.toString(table6.getId());
        String tableId7 = Integer.toString(table7.getId());
        String tableId8 = Integer.toString(table8.getId());
        String tableId9 = Integer.toString(table9.getId());
        List<String> tableIds = new ArrayList<>();
        tableIds.add(tableId1);
        tableIds.add(tableId2);
        tableIds.add(tableId3);
        tableIds.add(tableId4);
        tableIds.add(tableId5);
        tableIds.add(tableId6);
        tableIds.add(tableId7);
        tableIds.add(tableId8);
        tableIds.add(tableId9);
//        setTableDB(tableIds);


        // SharedPreferences에서 FirstRun 값을 가져옴
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean(FIRST_RUN_KEY, true);

        if (isFirstRun) {
            // 앱이 처음 실행될 때만 실행할 함수 호출
//            resetTableDB(tableIds);

            // FirstRun 값을 false로 설정하여 다음에 앱이 실행될 때는 함수를 실행하지 않음
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(FIRST_RUN_KEY, false);
            editor.apply();
        }

        // 최초 실행 여부를 저장


        // 자리에 있는 경우 색깔바꾸기
        String tableExist = getIntent().getStringExtra("tableExist");
        String tableId = getIntent().getStringExtra("tableId");
        System.out.println(tableExist);
        System.out.println(tableId);


        if (tableId != null) {
            if (tableExist.equals("true")) {
                int buttonId = getResources().getIdentifier(tableId, "id", getPackageName());
                Button button = findViewById(buttonId);
                button.setBackgroundColor(Color.RED);
            } else if (tableExist.equals("false")) {
                int buttonId = getResources().getIdentifier(tableId, "id", getPackageName());
                Button button = findViewById(buttonId);
                button.setBackgroundColor(Color.parseColor("#6200EE"));
            }
        }

//        table1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table1.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table2.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table3.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table4.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table5.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table6.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table7.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table8.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
//
//        table9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tableId = Integer.toString(table9.getId());
//                Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
//                table1Intent.putExtra("button_data", tableId);
//                startActivity(table1Intent);
//            }
//        });
        for(Button table : tables) {
            table.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tableId = Integer.toString(table.getId());
                    Intent table1Intent = new Intent(MainActivity.this, TableActivity.class);
                    table1Intent.putExtra("button_data", tableId);
                    startActivity(table1Intent);
                }
            });
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        table1 = findViewById(R.id.table1);
        table2 = findViewById(R.id.table2);
        table3 = findViewById(R.id.table3);
        table4 = findViewById(R.id.table4);
        table5 = findViewById(R.id.table5);
        table6 = findViewById(R.id.table6);
        table7 = findViewById(R.id.table7);
        table8 = findViewById(R.id.table8);
        table9 = findViewById(R.id.table9);

        String tableId1 = Integer.toString(table1.getId());
        String tableId2 = Integer.toString(table2.getId());
        String tableId3 = Integer.toString(table3.getId());
        String tableId4 = Integer.toString(table4.getId());
        String tableId5 = Integer.toString(table5.getId());
        String tableId6 = Integer.toString(table6.getId());
        String tableId7 = Integer.toString(table7.getId());
        String tableId8 = Integer.toString(table8.getId());
        String tableId9 = Integer.toString(table9.getId());
        List<String> tableIds = new ArrayList<>();
        tableIds.add(tableId1);
        tableIds.add(tableId2);
        tableIds.add(tableId3);
        tableIds.add(tableId4);
        tableIds.add(tableId5);
        tableIds.add(tableId6);
        tableIds.add(tableId7);
        tableIds.add(tableId8);
        tableIds.add(tableId9);

        // 이전 화면으로 돌아왔을 때 실행되어야 하는 함수 호출
        changeButtonRGBTableDB(tableIds);



        for (String tableId : tableIds) {
            ArrayList<TableDb> tableExistByTableId = tableDbHelper.getTableExistByTableId(tableId);
            for (TableDb tableDb : tableExistByTableId) {
                System.out.println(tableDb.getTableId());
                System.out.println(tableDb.getTableExist());
            }
        }
    }

    private void resetTableDB(List<String> tableIds) {
        for (String tableId : tableIds) {
            tableDbHelper.updateTableExistByTableId(tableId, false);
        }
    }

    private void changeButtonRGBTableDB(List<String> tableIds) {
        for (String tableId : tableIds) {
            ArrayList<TableDb> tableExistByTableId = tableDbHelper.getTableExistByTableId(tableId);
            for (TableDb tableDb : tableExistByTableId) {
                if (tableDb.getTableExist() == false) {
                    int buttonId = getResources().getIdentifier(tableId, "id", getPackageName());
                    Button button = findViewById(buttonId);
                    int newColor = Color.parseColor("#6200EE");
                    button.setBackgroundColor(newColor);

                } else if (tableDb.getTableExist() == true) {
                    int buttonId = getResources().getIdentifier(tableId, "id", getPackageName());
                    Button button = findViewById(buttonId);
                    int newColor = Color.RED;
                    button.setBackgroundColor(newColor);


                }
            }
        }
    }
//    private void setTableDB(List<String> tableIds){
//        for(String tableId : tableIds){
//            ContentValues values = new ContentValues();
//            values.put(TableInfo.COLUMN_NAME_TABLEID,tableId);
//            values.put(TableInfo.COLUMN_NAME_TABLEEXIST,false);
//
//            SQLiteDatabase db = tableDbHelper.getWritableDatabase();
//            long newRowId = db.insert(TableInfo.TABLE_NAME, null, values);
//        }
//    }
}



