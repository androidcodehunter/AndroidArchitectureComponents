package com.sharif.architecture.repository;

import android.arch.lifecycle.LiveData;

import com.sharif.architecture.db.User;
import com.sharif.architecture.db.UserDao;

import java.util.concurrent.Executor;

/**
 * Created by User on 6/4/2017.
 */

public class UserRepository {

    private final Webservice webservice;
    private final UserDao userDao;
    private final Executor executor;

    public UserRepository(Webservice webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userId) {
        refreshUser(userId);
        // return a LiveData directly from the database.
        return userDao.load(userId);
    }

    private void refreshUser(final String userId) {
        executor.execute(() -> {
            // running in a background thread
            // check if user was fetched recently
            boolean userExists = userDao.hasUser(FRESH_TIMEOUT);
            if (!userExists) {
                // refresh the data
                User user = webservice.getUser(userId);
                // TODO check for error etc.
                // Update the database.The LiveData will automatically refresh so
                // we don't need to do anything else here besides updating the database
                userDao.save(user);
            }
        });
    }
}
