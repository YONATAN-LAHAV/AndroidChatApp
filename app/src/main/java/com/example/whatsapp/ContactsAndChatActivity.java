package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp.adapters.ApiContactListAdapter;
import com.example.whatsapp.adapters.ApiMessageListAdapter;
import com.example.whatsapp.entities.ApiContact;
import com.example.whatsapp.entities.LoginPostRequest;
import com.example.whatsapp.entities.MessagePostRequest;
import com.example.whatsapp.interfaces.ListItemClickListener;
import com.example.whatsapp.localdb.Users;
import com.example.whatsapp.localdb.localDatabase;
import com.example.whatsapp.viewmodels.ApiContactViewModel;
import com.example.whatsapp.viewmodels.ApiMessageViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactsAndChatActivity extends AppCompatActivity implements ListItemClickListener {

    private ApiContactViewModel _apiContactViewModel;
    private ApiMessageViewModel _apiMessageViewModel;
    private LoginPostRequest _connectedUser;
    private ApiContact _contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_and_chat);

        // Get extras and create LoginPostRequest object to pass the connected user.
        Bundle extras = getIntent().getExtras();

        // Init connected user.
        _connectedUser = new LoginPostRequest(extras.getString("username")
                , extras.getString("password"));
        // Set button to add new contact screen.
        FloatingActionButton btnToAddContact = findViewById(R.id.btnAddContactScreen);
        btnToAddContact.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            intent.putExtra("username", extras.getString("username"));
            intent.putExtra("password", extras.getString("password"));
            intent.putExtra("nickname", extras.getString("nickname"));
            startActivity(intent);
        });
        // Init _viewModel _apiContactViewModel.
        ViewModelProvider.Factory factoryContacts = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ApiContactViewModel(_connectedUser);
            }
        };
        _apiContactViewModel = new ViewModelProvider(this, factoryContacts).get(ApiContactViewModel.class);

        // RecycleView Contacts logic.
        RecyclerView lstApiContacts = findViewById(R.id.lstApiContacts);
        final ApiContactListAdapter apiContactListAdapter = new ApiContactListAdapter(this, this);
        lstApiContacts.setLayoutManager(new LinearLayoutManager(this));

        lstApiContacts.setClickable(true);
        lstApiContacts.setAdapter(apiContactListAdapter);

        _apiContactViewModel.get().observe(this, apiContacts -> {
            apiContactListAdapter.setContacts(apiContacts);
            //      lstApiContacts.setAdapter(apiContactListAdapter);
        });

        // Chat side.
        // Init contact field.
        if (extras.getString("ContactUsername") != null) {
            ConstraintLayout constraintLayout = findViewById(R.id.chat_side);
            constraintLayout.setVisibility(ConstraintLayout.VISIBLE);
            _contact = new ApiContact(extras.getString("ContactUsername")
                    , extras.getString("ContactName")
                    , extras.getString("ContactServer")
                    , ""
                    , "");

            // Show user image
            ImageView userImage = findViewById(R.id.ivContactAvatar);
            Users contact = localDatabase.getInstance().usersDao().getUser(_connectedUser.getId());
            if (contact == null)
                userImage.setImageDrawable(getDrawable(R.drawable.ic_avatar));
            else {
                String encodedImage = contact.getPicture();
                byte[] imageByteArray = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
                userImage.setImageBitmap(image);
            }


            // Init top bar (contact card).
            TextView tvContactName = findViewById(R.id.tvContactName);
            tvContactName.setText(_contact.getName());

            // Init _viewModel field.
            ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
                @NonNull
                @Override
                public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                    return (T) new ApiMessageViewModel(_connectedUser, _contact);
                }
            };
            _apiMessageViewModel = new ViewModelProvider(this, factory).get(ApiMessageViewModel.class);

            // RecycleView logic.
            RecyclerView lstApiMessages = findViewById(R.id.lstApiMessages);
            final ApiMessageListAdapter apiMessageListAdapter = new ApiMessageListAdapter(this, getIntent().getExtras().get("ContactName").toString());
            lstApiMessages.setLayoutManager(new LinearLayoutManager(this));
            lstApiMessages.setAdapter(apiMessageListAdapter);

            // Set observer on the data in the viewModel. when viewModel data will change,
            // the method will activate.
            _apiMessageViewModel.get().observe(this, apiMessages -> {
                apiMessageListAdapter.setMessages(apiMessages);
            });

            Button btnSend = findViewById(R.id.btnSend);
            btnSend.setOnClickListener(view -> {
                EditText etNewMessage = findViewById(R.id.etNewMessage);
                MessagePostRequest messagePostRequest =
                        new MessagePostRequest(etNewMessage.getText().toString());
                _apiMessageViewModel.add(messagePostRequest, this);
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                }
                lstApiMessages.scrollToPosition(_apiMessageViewModel.get().getValue().size() - 1);
            });
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, ContactsActivity.class);
            intent.putExtra("username", _connectedUser.getId());
            intent.putExtra("password", _connectedUser.getPassword());
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
        }
    }


    @Override
    public void onListItemClick(ApiContact apiContact) {
        Intent intent = getIntent();
        intent.putExtra("username", _connectedUser.getId());
        intent.putExtra("password", _connectedUser.getPassword());
        intent.putExtra("ContactUsername", apiContact.getId());
        intent.putExtra("ContactName", apiContact.getName());
        intent.putExtra("ContactServer", apiContact.getServer());
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }
}