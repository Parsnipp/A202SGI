package com.example.callum.waitersfriend;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuActivity extends ListActivity {

    Double cost = new Double(0.00);
    ArrayList<HashMap<String, String>> order = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ListView lv = getListView();
        ArrayList<HashMap<String, String>> items = new ArrayList();
        HashMap<String, String> pizza = new HashMap<String, String>();
        HashMap<String, String> burger = new HashMap<String, String>();
        HashMap<String, String> chips = new HashMap<String, String>();
        HashMap<String, String> salad = new HashMap<String, String>();
        HashMap<String, String> drink = new HashMap<String, String>();
        final HashMap<String, Double> prices = new HashMap<String, Double>();
        pizza.put("name", "Pizza");
        pizza.put("price", "£6.00");
        burger.put("name", "Burger");
        burger.put("price", "£4.50");
        chips.put("name", "Chips");
        chips.put("price", "£2.00");
        salad.put("name", "Salad");
        salad.put("price", "£1.50");
        drink.put("name", "Drink");
        drink.put("price", "£1.80");
        prices.put("Pizza", 6.00);
        prices.put("Burger", 4.50);
        prices.put("Chips", 2.00);
        prices.put("Salad", 1.50);
        prices.put("Drink", 1.80);
        items.add(pizza);
        items.add(burger);
        items.add(chips);
        items.add(salad);
        items.add(drink);

        if (items.size() != 0) {
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), items, android.R.layout.simple_list_item_2,
                    new String[]{"name", "price"}, new int[]{android.R.id.text1, android.R.id.text2});
            setListAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap map = (HashMap) parent.getItemAtPosition(position);
                String pr1 = (String) map.get("name");
                Double pr2 = prices.get(pr1);
                order.add(map);
                cost = cost + pr2;
                Log.d("LOG", String.valueOf(cost));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_bill) {
            Intent intent = new Intent(getApplicationContext(), BillActivity.class);
            intent.putExtra("Cost", cost);
            intent.putExtra("Order", order);
            startActivity(intent);
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
