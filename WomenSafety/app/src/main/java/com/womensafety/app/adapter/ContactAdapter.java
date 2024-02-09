package com.womensafety.app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.womensafety.app.ContactsActivity;
import com.womensafety.app.R;
import com.womensafety.app.Utility;
import com.womensafety.app.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Contact> contacts;
    private Context mContext;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.contacts = contacts;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.contact_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    private void deleteContact(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete the contact?");
        builder.setTitle("Delete?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            this.contacts.remove(position);
            Utility.saveContactsToSharedPref(mContext, contacts);
            this.notifyItemRemoved(position);
            Toast.makeText(mContext, "Emergency contact deleted!", Toast.LENGTH_LONG).show();
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Contact contact = contacts.get(position);

        holder.contactName.setText(contact.name);
        holder.contactNumber.setText(contact.phoneNumber);

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteContact(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }



}
class ViewHolder extends RecyclerView.ViewHolder {
    public TextView contactName;
    public TextView contactNumber;
    public ImageView deleteIcon;
    private ContactAdapter mContactAdapter;

    public ViewHolder linkAdapter(ContactAdapter contactAdapter){
        this.mContactAdapter = contactAdapter;
        return this;
    }
    public ViewHolder(View itemView) {
        super(itemView);
        this.contactName = (TextView) itemView.findViewById(R.id.contactName);
        this.contactNumber = (TextView) itemView.findViewById(R.id.contactNumber);
        this.deleteIcon = (ImageView) itemView.findViewById(R.id.deleteButton);
        /*this.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Deleting" + contact.name, Toast.LENGTH_LONG).show();
            }
        });*/
    }
}
