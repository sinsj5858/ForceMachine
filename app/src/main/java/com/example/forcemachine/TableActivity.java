package com.example.forcemachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.forcemachine.menu.MenuDbHelper;
import com.example.forcemachine.menu.MenuDb;
import com.example.forcemachine.menu.MenuTableInfo;
import com.example.forcemachine.table.TableDb;
import com.example.forcemachine.table.TableDbHelper;
import com.example.forcemachine.table.TableInfo;

import java.util.ArrayList;
import java.util.List;


public class TableActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button showFoodBtn;
    Button showFoodBtn2;
    Button showFoodBtn3;
    Button okBtn;
    Button paymentBtn;
    List<Menu> menuList; // 메뉴 목록을 저장하는 리스트

    MenuDbHelper menuDbHelper;

    TableDbHelper tableDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        menuDbHelper = new MenuDbHelper(this);
        tableDbHelper = new TableDbHelper(this);
        menuList = new ArrayList<>();
        showFoodBtn = findViewById(R.id.Food1);
        showFoodBtn2 = findViewById(R.id.Food2);
        showFoodBtn3 = findViewById(R.id.Food3);

        okBtn = findViewById(R.id.ok);
        paymentBtn = findViewById(R.id.Payment);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        String tableId = getIntent().getStringExtra("button_data");
        ArrayList<MenuDb> menuData = menuDbHelper.getMenuByTableId(tableId);

        for(MenuDb menu : menuData){
            TextView foodTextView = new TextView(TableActivity.this);
            String foodInfo = menu.getMenuName() + "    " + menu.getMenuPrice() + "원";
            foodTextView.setText(foodInfo);
            foodTextView.setTextSize(16);
            foodTextView.setPadding(16, 16, 16, 16);

            // LinearLayout에 TextView 추가
            linearLayout.addView(foodTextView);
            Menu menu2 = new Menu(menu.getMenuName(),  menu.getMenuPrice(),menu.getMenuCount());

            // 버튼과 개수를 동적으로 생성하여 추가
            LinearLayout menuLayout = new LinearLayout(this);
            menuLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 16, 0, 16);
            menuLayout.setLayoutParams(layoutParams);

            TextView menuCountTextView = new TextView(this);
            menuCountTextView.setText(String.valueOf(menu.getMenuCount()));
            menuCountTextView.setTextSize(16);
            menuCountTextView.setPadding(16, 16, 16, 16);
            menuLayout.addView(menuCountTextView);

            Button plusButton = new Button(this);
            plusButton.setText("+");
            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menu2.increaseCount();
                    menuCountTextView.setText(String.valueOf(menu2.getMenuCount()));
                }
            });

            menuLayout.addView(plusButton);

            Button minusButton = new Button(this);
            minusButton.setText("-");
            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menu2.decreaseCount();
                    menuCountTextView.setText(String.valueOf(menu2.getMenuCount()));
                }
            });

            menuLayout.addView(minusButton);

            linearLayout.addView(menuLayout);
        }

        showFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 메뉴 추가
                addMenu("웅찌", "1000");
            }
        });
        showFoodBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 메뉴 추가
                addMenu("웅이 뱃살", "1000");
            }
        });
        showFoodBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 메뉴 추가
                addMenu("웅이 삼겹살", "1000");
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Menu menu : menuList){
                    addMenuDB(tableId,menu.getMenuName(),menu.getMenuPrice(),menu.getMenuCount());
                }
                ArrayList<MenuDb> menuData = menuDbHelper.getMenuByTableId(tableId);

                if(menuData.isEmpty()){
                    Boolean tableExist = false;
                    tableDbHelper.updateTableExistByTableId(tableId,tableExist);
                    Intent okIntent = new Intent(TableActivity.this, MainActivity.class);
                    startActivity(okIntent);
                }else {
                    Boolean tableExist = true;
                    tableDbHelper.updateTableExistByTableId(tableId,tableExist);
                    ArrayList<TableDb> tableExistByTableId = tableDbHelper.getTableExistByTableId(tableId);
                    Intent okIntent = new Intent(TableActivity.this, MainActivity.class);
                    startActivity(okIntent);
                }

             }
        });
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                menuDbHelper.deleteMenuByTableId(tableId);
                ArrayList<MenuDb> menuData = menuDbHelper.getMenuByTableId(tableId);
                if(menuData.isEmpty()){
                    Boolean tableExist = false;
                    tableDbHelper.updateTableExistByTableId(tableId,tableExist);
                    Intent okIntent = new Intent(TableActivity.this, MainActivity.class);
                    startActivity(okIntent);
                }else {
                    Boolean tableExist = true;
                    tableDbHelper.updateTableExistByTableId(tableId,tableExist);
                    Intent okIntent = new Intent(TableActivity.this, MainActivity.class);
                    startActivity(okIntent);
                }
            }
        });

    }

    public void addMenuDB(String tableId,String menuName,String  menuPrice,int menuCount){
        ContentValues values = new ContentValues();
        values.put(MenuTableInfo.COLUMN_NAME_TABLEID,tableId);
        values.put(MenuTableInfo.COLUMN_NAME_MENUNAME,menuName);
        values.put(MenuTableInfo.COLUMN_NAME_MENUPRICE,menuPrice);
        values.put(MenuTableInfo.COLUMN_NAME_MENUCOUNT,menuCount);

        SQLiteDatabase db = menuDbHelper.getWritableDatabase();
        long newRowId = db.insert(MenuTableInfo.TABLE_NAME, null, values);
        Log.i(TAG,"메뉴추가");
    }
    void addMenu(String foodName, String price) {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        // 음식 이름과 가격 데이터
        // 음식 이름과 가격을 추가할 TextView 생성
        TextView foodTextView = new TextView(TableActivity.this);
        String foodInfo = foodName + "    " + price + "원";
        foodTextView.setText(foodInfo);
        foodTextView.setTextSize(16);
        foodTextView.setPadding(16, 16, 16, 16);

        // LinearLayout에 TextView 추가
        linearLayout.addView(foodTextView);

        // 메뉴 객체 생성
        Menu menu = new Menu(foodName, price,1);
        menuList.add(menu);

        // 버튼과 개수를 동적으로 생성하여 추가
        LinearLayout menuLayout = new LinearLayout(this);
        menuLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 16, 0, 16);
        menuLayout.setLayoutParams(layoutParams);

        TextView menuCountTextView = new TextView(this);
        menuCountTextView.setText(String.valueOf(menu.getMenuCount()));
        menuCountTextView.setTextSize(16);
        menuCountTextView.setPadding(16, 16, 16, 16);
        menuLayout.addView(menuCountTextView);

        Button plusButton = new Button(this);
        plusButton.setText("+");
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.increaseCount();
                menuCountTextView.setText(String.valueOf(menu.getMenuCount()));
            }
        });

        menuLayout.addView(plusButton);

        Button minusButton = new Button(this);
        minusButton.setText("-");
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.decreaseCount();
                menuCountTextView.setText(String.valueOf(menu.getMenuCount()));
            }
        });

        menuLayout.addView(minusButton);

        linearLayout.addView(menuLayout);
    }

}