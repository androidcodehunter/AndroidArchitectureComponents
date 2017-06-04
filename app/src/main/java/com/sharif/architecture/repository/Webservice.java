package com.sharif.architecture.repository;

import com.sharif.architecture.db.User;

/**
 * Created by User on 6/4/2017.
 */

interface Webservice {
    User getUser(String userId);
}
