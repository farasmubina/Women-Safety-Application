package com.womensafety.app;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.womensafety.app.model.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static final String CONTACTS_KEY = "EMERGENCY_CONTACTS";

    private static final String PANIC_MESSAGE_KEY = "PANIC_MESSAGE";

    private static final String CAUTIOUS_MESSAGE_KEY = "CAUTIOUS_MESSAGE";

    public static void saveContactsToSharedPref(Context context, List<Contact> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPrefUtility.saveToSharedPref(context, CONTACTS_KEY, json);
    }

    public static ArrayList<Contact> readContactsFromSharedPref(Context context) {
        ArrayList<Contact> contacts = null;
        String serializedObject = SharedPrefUtility.readFromSharedPref(context, CONTACTS_KEY);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Contact>>() {
            }.getType();
            contacts = gson.fromJson(serializedObject, type);
        }
        return contacts;
    }

    public static String readPanicMessageFromSharedPref(Context context){
        String message = SharedPrefUtility.readFromSharedPref(context, PANIC_MESSAGE_KEY);
        return message;
    }

    public static String readCautiousMessageFromSharedPref(Context context){
        String message = SharedPrefUtility.readFromSharedPref(context, CAUTIOUS_MESSAGE_KEY);
        return message;
    }

    public static void savePanicMessageToSharedPref(Context context) {
        String message = "Panic Alert. Please help me. My location: https://maps.app.goo.gl/nRA8DUXe5X24Cv4t5";
        SharedPrefUtility.saveToSharedPref(context, PANIC_MESSAGE_KEY, message);
    }

    public static void saveCautiousMessageToSharedPref(Context context) {
        String message = "Cautious Alert. Please help me. My location: https://maps.app.goo.gl/nRA8DUXe5X24Cv4t5";
        SharedPrefUtility.saveToSharedPref(context, CAUTIOUS_MESSAGE_KEY, message);
    }
}
