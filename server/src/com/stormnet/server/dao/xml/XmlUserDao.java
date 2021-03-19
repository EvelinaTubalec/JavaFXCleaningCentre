package com.stormnet.server.dao.xml;

import com.stormnet.data.ClientAccount;
import com.stormnet.server.dao.DaoException;
import com.stormnet.server.dao.UserDao;
import com.stormnet.server.dao.xml.db.XmlDataBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.List;

class XmlUserDao implements UserDao {

    @Override
    public List<ClientAccount> getAllUsers() {
        List<ClientAccount> allClientAccounts = new ArrayList<>();

        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("users");

            NodeList allClientsTag = document.getElementsByTagName("user");
            int tagCount = allClientsTag.getLength();

            for (int i = 0; i <tagCount ; i++) {
                Element clientTag = (Element) allClientsTag.item(i);

                Element idTag = (Element) clientTag.getElementsByTagName("id").item(0);
                String idStr = idTag.getTextContent();
                long id = Long.parseLong(idStr);

                Element telephoneNumberTag = (Element) clientTag.getElementsByTagName("telephoneNumber").item(0);
                String telephoneNumber = telephoneNumberTag.getTextContent();

                Element passwordTag = (Element) clientTag.getElementsByTagName("password").item(0);
                String password = passwordTag.getTextContent();

                ClientAccount clientAccount = new ClientAccount(telephoneNumber, password);
                clientAccount.setId(id);

                allClientAccounts.add(clientAccount);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return allClientAccounts;
    }

    @Override
    public Long saveUser(ClientAccount clientAccount) {
        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("users");

            Element rootTag = document.getDocumentElement();
            Element clientTag = document.createElement("user");

            Long id = dataBase.getNexIdForTable("users");
            Element idTag = document.createElement("id");
            Text idData = document.createTextNode(id.toString());
            idTag.appendChild(idData);
            clientTag.appendChild(idTag);

            Element telephoneNumberTag = document.createElement("telephoneNumber");
            Text telephoneNumberData = document.createTextNode(clientAccount.getTelephoneNumber());
            telephoneNumberTag.appendChild(telephoneNumberData);
            clientTag.appendChild(telephoneNumberTag);

            Element passwordTag = document.createElement("password");
            Text passwordData = document.createTextNode(clientAccount.getPassword());
            passwordTag.appendChild(passwordData);
            clientTag.appendChild(passwordTag);

            rootTag.appendChild(clientTag);

            dataBase.saveDbTableDocument("users");

            return id;

        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
