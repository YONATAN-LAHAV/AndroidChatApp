package com.example.whatsapp.localdb;

import android.content.Context;

import androidx.room.Room;

public class localDatabase {
    private static Context _context;
    private static AppDB _db = null;
    private static UsersDao _usersDao;

    public localDatabase(Context context) {
        _context = context;
    }

    public static AppDB getInstance() {
        if (_db == null) {
            _db = Room.databaseBuilder(_context,
                            AppDB.class
                            , "AndroidDB")
                    .allowMainThreadQueries()
                    .build();
            _usersDao = _db.usersDao();
        }
        return _db;
    }
}
