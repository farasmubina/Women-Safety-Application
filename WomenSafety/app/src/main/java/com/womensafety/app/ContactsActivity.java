package com.womensafety.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.womensafety.app.adapter.ContactAdapter;
import com.womensafety.app.model.Contact;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ContactsActivity extends AppCompatActivity {

    Button btn_add_contact;
    ArrayList<Contact> contacts;
    ContactAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Emergency Contacts");


        contacts = Utility.readContactsFromSharedPref(ContactsActivity.this);
        //contacts.add(new Contact("Sukeshanand Sawant", "1234567890", 1));
        if(contacts == null){
            contacts = new ArrayList<>();
        }

        recyclerView = (RecyclerView) findViewById(R.id.contactRV);
        adapter = new ContactAdapter(this, contacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btn_add_contact = findViewById(R.id.btn_add_contact);

        btn_add_contact.setOnClickListener(view -> {
            showAddContactDialog();
        });
    }

    private void showAddContactDialog() {
        final Dialog addContactDialog = new Dialog(ContactsActivity.this);
        addContactDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addContactDialog.setCancelable(true);
        addContactDialog.setContentView(R.layout.add_contact_layout);
        final EditText contactNameId = addContactDialog.findViewById(R.id.contactNameId);
        final EditText phoneNumberId = addContactDialog.findViewById(R.id.phoneNumberId);

        Button submitbuttonId = addContactDialog.findViewById(R.id.submitbuttonId);

        submitbuttonId.setOnClickListener(view -> {
            if (contactNameId.getText().length() != 0 && !contactNameId.getText().toString().trim().equalsIgnoreCase("")
                    && phoneNumberId.getText().length() != 0 && !phoneNumberId.getText().toString().trim().equalsIgnoreCase("")) {
                Contact contact = new Contact(contactNameId.getText().toString(), phoneNumberId.getText().toString(), 0);
                contacts.add(contact);
                Utility.saveContactsToSharedPref(ContactsActivity.this, contacts);
                adapter.notifyItemInserted(contacts.size() - 1);
                addContactDialog.dismiss();
            }

        });
        addContactDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}