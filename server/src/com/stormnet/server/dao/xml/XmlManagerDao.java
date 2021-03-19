package com.stormnet.server.dao.xml;

import com.stormnet.data.ManagerAccount;
import com.stormnet.server.dao.DaoException;
import com.stormnet.server.dao.ManagerDao;
import com.stormnet.server.dao.xml.db.XmlDataBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

public class XmlManagerDao implements ManagerDao {
    @Override
    public List<ManagerAccount> getAllManagers() {

        List<ManagerAccount> allManagerAccounts = new ArrayList<>();

        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("managers");

            NodeList allClientsTag = document.getElementsByTagName("manager");
            int tagCount = allClientsTag.getLength();

            for (int i = 0; i <tagCount ; i++) {
                Element clientTag = (Element) allClientsTag.item(i);

                Element idTag = (Element) clientTag.getElementsByTagName("id").item(0);
                String idStr = idTag.getTextContent();
                long id = Long.parseLong(idStr);

                Element loginTag = (Element) clientTag.getElementsByTagName("login").item(0);
                String login = loginTag.getTextContent();

                Element passwordTag = (Element) clientTag.getElementsByTagName("password").item(0);
                String password = passwordTag.getTextContent();

                ManagerAccount managerAccount = new ManagerAccount(login, password);
                managerAccount.setId(id);

                allManagerAccounts.add(managerAccount);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return allManagerAccounts;
    }
}
