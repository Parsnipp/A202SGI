package com.example.callum.waitersfriend;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class BillActivity extends ListActivity {

    Double cost;
    ArrayList<HashMap<String, String>> order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent iny = getIntent();
        cost = iny.getExtras().getDouble("Cost");
        order = (ArrayList) iny.getExtras().getSerializable("Order");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        final DecimalFormat money = new DecimalFormat("#.00");
        TextView total = (TextView) findViewById(R.id.textView);
        total.setText("£" + String.valueOf(money.format(cost)));
        Button button = (Button) findViewById(R.id.button);
        if (order.size() != 0) {
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), order, android.R.layout.simple_list_item_2,
                    new String[]{"name", "price"}, new int[]{android.R.id.text1, android.R.id.text2});
            setListAdapter(adapter);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText per = (EditText) findViewById(R.id.editText);
                double percent1 = Integer.parseInt(String.valueOf(per.getText()));
                Double percent2 = percent1 / 100;
                Double percent3 = 1 - percent2;
                Double newCost = cost * percent3;
                TextView total = (TextView) findViewById(R.id.textView);
                total.setText("£" + String.valueOf(money.format(newCost)));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bill, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_menu) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.putExtra("Cost", cost);
            intent.putExtra("Order", order);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_new) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
