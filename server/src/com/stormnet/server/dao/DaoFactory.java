package com.stormnet.server.dao;

import com.stormnet.server.dao.xml.XmlDaoFactory;

public abstract class DaoFactory {

    public abstract OrderDao getOrderDao();

    public abstract UserDao getUserDao();

    public abstract ManagerDao getManagerDao();

    public abstract ReviewDao getReviewDao();

    public static DaoFactory getCurrentDaoFactory(){
       return new XmlDaoFactory();
    }
}
