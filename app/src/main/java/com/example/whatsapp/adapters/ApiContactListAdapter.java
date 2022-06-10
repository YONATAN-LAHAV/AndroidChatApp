package com.example.whatsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.R;
import com.example.whatsapp.entities.ApiContact;

import java.util.List;

public class ApiContactListAdapter extends RecyclerView.Adapter<ApiContactListAdapter.ContactsViewHolder> {

    private final LayoutInflater _layoutInflater;
    private List<ApiContact> _contacts;

    public void setContacts(List<ApiContact> _contacts) {
        this._contacts = _contacts;
    }

    public ApiContactListAdapter(Context context) {
        _layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = _layoutInflater.inflate(R.layout.contact_list_item, parent, false);
        return new ContactsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        if (_contacts != null) {
            final ApiContact contact = _contacts.get(position);
            holder.tvName.setText(contact.getName());
            holder.tvLast.setText(contact.getLast());
            holder.tvLastDate.setText(contact.getLastdate());
        }
    }

    @Override
    public int getItemCount() {
        if (_contacts != null)
            return _contacts.size();
        return 0;
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvLast;
        private final TextView tvLastDate;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLast = itemView.findViewById(R.id.tvLast);
            tvLastDate = itemView.findViewById(R.id.tvLastDate);
        }
    }
}
