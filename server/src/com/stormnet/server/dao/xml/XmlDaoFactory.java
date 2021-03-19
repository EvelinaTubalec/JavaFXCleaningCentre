package com.stormnet.server.dao.xml;

import com.stormnet.server.dao.*;

public class XmlDaoFactory extends DaoFactory {

    @Override
    public OrderDao getOrderDao() {
        return new XmlOrderDao();
    }

    @Override
    public UserDao getUserDao() {
        return new XmlUserDao();
    }

    @Override
    public ManagerDao getManagerDao() {
        return new XmlManagerDao();
    }

    @Override
    public ReviewDao getReviewDao() {
        return new XmlReviewDao();
    }
}
