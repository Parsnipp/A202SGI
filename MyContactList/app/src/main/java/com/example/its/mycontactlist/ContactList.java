package com.example.its.mycontactlist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        final ListView lv = getListView();
        final SqliteController controller = new SqliteController(this);
        final ArrayList<HashMap<String, String>> contactList = controller.getAllContacts();

        if (contactList.size() != 0) {
            final SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), contactList,
                    android.R.layout.simple_list_item_2, new String[]{"full_name", "Number"},
                    new int[]{android.R.id.text1, android.R.id.text2});

            setListAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                }
            });

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HashMap map = (HashMap) adapterView.getItemAtPosition(i);
                    String number = (String) map.get("Number");

                    controller.deleteContact(number);
                    contactList.clear();
                    ArrayList<HashMap<String, String>> contactList = controller.getAllContacts();
                    adapter.notifyDataSetChanged();
                    lv.invalidateViews();
                    lv.refreshDrawableState();
                    return true;
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_contact){
            Intent intent = new Intent(getApplicationContext(), Contact.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
