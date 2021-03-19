package com.stormnet.server.dao;

import com.stormnet.data.ManagerAccount;

import java.util.List;

public interface ManagerDao{
    List<ManagerAccount> getAllManagers();
}
