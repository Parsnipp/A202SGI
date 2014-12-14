package com.example.callum.contactlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class Contact extends Activity {
    Button button;

    SqliteController controller = new SqliteController(this);

    EditText first_name;
    EditText surname;
    EditText number;
    EditText address;
    EditText city;
    EditText post_code;
    EditText email;
    HashMap<String, String> person;
    Boolean update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        first_name = (EditText)findViewById(R.id.editText);
        surname = (EditText)findViewById(R.id.editText2);
        number = (EditText)findViewById(R.id.editText4);
        address = (EditText)findViewById(R.id.editText3);
        city = (EditText)findViewById(R.id.editText5);
        post_code = (EditText)findViewById(R.id.editText6);
        email = (EditText)findViewById(R.id.editText7);
        update = false;

        if (String.valueOf(getIntent().getExtras().getSerializable("Contact")).length() != 0) {
            person = (HashMap<String, String>) getIntent().getExtras().getSerializable("Contact");
            update = true;

            if (person.get("First_Name") != null) first_name.setText(person.get("First_Name"));
            if (person.get("Surname") != null) surname.setText(person.get("Surname"));
            if (person.get("Number") != null) number.setText(person.get("Number"));
            if (person.get("Address") != null) address.setText(person.get("Address"));
            if (person.get("City") != null) city.setText(person.get("City"));
            if (person.get("Post_Code") != null) post_code.setText(person.get("Post_Code"));
            if (person.get("Email") != null) email.setText(person.get("Email"));
        }

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> hm = new HashMap<String, String>();

                hm.put("First_Name", first_name.getText().toString());
                hm.put("Surname", surname.getText().toString());
                hm.put("Number", number.getText().toString());
                hm.put("Address", address.getText().toString());
                hm.put("City", city.getText().toString());
                hm.put("Post_Code", post_code.getText().toString());
                hm.put("Email", email.getText().toString());

                if (update == false) {
                    controller.insertContact(hm);
                    Intent intent = new Intent(getApplicationContext(), ContactList.class);
                    startActivity(intent);
                } else {
                    controller.updateContact(hm);
                    Intent intent = new Intent(getApplicationContext(), ContactList.class);
                    startActivity(intent);
                }
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