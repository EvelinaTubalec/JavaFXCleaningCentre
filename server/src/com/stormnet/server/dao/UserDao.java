package com.stormnet.server.dao;

import com.stormnet.data.ClientAccount;
import java.util.List;

public interface UserDao {
    List<ClientAccount> getAllUsers();

    Long saveUser(ClientAccount clientAccount);
}
