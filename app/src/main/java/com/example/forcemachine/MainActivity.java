package com.example.forcemachine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.forcemachine.menu.MenuDb;
import com.example.forcemachine.menu.MenuDbHelper;
import com.example.forcemachine.menu.MenuTableInfo;
import com.example.forcemachine.retrofit.NetworkHelper;
import com.example.forcemachine.table.RestaurantStatusRequest;
import com.example.forcemachine.table.TableDb;
import com.example.forcemachine.table.TableDbHelper;
import com.example.forcemachine.table.TableInfo;
import com.example.forcemachine.table.TableStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String FIRST_RUN_KEY = "FirstRun";
    private static final String restaurantName = "두꺼비식당";
    private static final String TAG = "MainActivity";

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

        // 최초 실행 여부를 저장



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
        postTableData(tableIds);


//        for (String tableId : tableIds) {
//            ArrayList<TableDb> tableExistByTableId = tableDbHelper.getTableExistByTableId(tableId);
//            for (TableDb tableDb : tableExistByTableId) {
//                System.out.println(tableDb.getTableId());
//                System.out.println(tableDb.getTableExist());
//            }
//        }
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
    private void setTableDB(List<String> tableIds){
        for(String tableId : tableIds){
            ContentValues values = new ContentValues();
            values.put(TableInfo.COLUMN_NAME_TABLEID,tableId);
            values.put(TableInfo.COLUMN_NAME_TABLEEXIST,false);

            SQLiteDatabase db = tableDbHelper.getWritableDatabase();
            long newRowId = db.insert(TableInfo.TABLE_NAME, null, values);
        }
    }

    // 빈자리 통신코드
    private void postTableData(List<String> tableIds){
        List<TableStatus> tableStatuses = new ArrayList<>();
        for (String tableId : tableIds) {
            ArrayList<TableDb> tableExistByTableId = tableDbHelper.getTableExistByTableId(tableId);
            for (TableDb tableDb : tableExistByTableId) {
                TableStatus tableStatus = new TableStatus(tableDb.getTableId(),tableDb.getTableExist());
                tableStatuses.add(tableStatus);
            }
        }
        RestaurantStatusRequest restaurantStatusRequest = new RestaurantStatusRequest(restaurantName,tableStatuses);
        for(TableStatus tableStatus :restaurantStatusRequest.getTableStatusList()){
            System.out.println(tableStatus.getTableId());
            System.out.println(tableStatus.isTableExist());
        }

        Call<String> call = NetworkHelper.getInstance().getApiService().postRestaurantStatus(restaurantStatusRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "빈자리 데이터 보내기 성공");
                } else {
                    // 데이터를 받아오는 도중 오류가 발생한 경우 처리할 로직을 작성합니다.
                    Log.e(TAG, "Response failed"); //username 중복
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e(TAG, "Network failure");
                    t.printStackTrace();
                } else {
                    Log.e(TAG, "Unexpected failure");
                }
            }
        });


    }
}



