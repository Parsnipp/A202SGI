package com.example.its.mycontactlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class Contact extends Activity {
    Button button;

    SqliteController controller = new SqliteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_contact);

            button = (Button) findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                EditText first_name = (EditText)findViewById(R.id.editText);
                EditText surname = (EditText)findViewById(R.id.editText2);
                EditText number = (EditText)findViewById(R.id.editText4);
                EditText address = (EditText)findViewById(R.id.editText3);
                EditText city = (EditText)findViewById(R.id.editText5);
                EditText post_code = (EditText)findViewById(R.id.editText6);
                EditText email = (EditText)findViewById(R.id.editText7);

                HashMap<String, String> hm = new HashMap<String, String>();

                hm.put("First_Name", first_name.getText().toString());
                hm.put("Surname", surname.getText().toString());
                hm.put("Number", number.getText().toString());
                hm.put("Address", address.getText().toString());
                hm.put("City", city.getText().toString());
                hm.put("Post_Code", post_code.getText().toString());
                hm.put("Email", email.getText().toString());

                controller.insertContact(hm);
                Intent intent = new Intent(getApplicationContext(), ContactList.class);
                startActivity(intent);
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
}
