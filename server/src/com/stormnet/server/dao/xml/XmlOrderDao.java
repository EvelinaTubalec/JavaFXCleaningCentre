package com.stormnet.server.dao.xml;

import com.stormnet.data.Order;
import com.stormnet.server.dao.DaoException;
import com.stormnet.server.dao.OrderDao;
import com.stormnet.server.dao.xml.db.XmlDataBase;
import com.stormnet.utils.date.DataUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class XmlOrderDao implements OrderDao {

    @Override
    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("orders");

            NodeList allClientsTag = document.getElementsByTagName("order");
            int tagCount = allClientsTag.getLength();

            for (int i = 0; i < tagCount; i++) {
                Element clientTag = (Element) allClientsTag.item(i);

                Element idTag = (Element) clientTag.getElementsByTagName("id").item(0);
                String idStr = idTag.getTextContent();
                long id = Long.parseLong(idStr);

                Element lastNameTag = (Element) clientTag.getElementsByTagName("lastName").item(0);
                String lastName = lastNameTag.getTextContent();

                Element firstNameTag = (Element) clientTag.getElementsByTagName("firstName").item(0);
                String firstName = firstNameTag.getTextContent();

                Element dateOfOrderTag = (Element) clientTag.getElementsByTagName("dateOfOrder").item(0);
                String dateOfOrderTagStr = dateOfOrderTag.getTextContent();
                Date dateOfOrder = DataUtils.buildDateFromString(dateOfOrderTagStr);

                Element addressTag = (Element) clientTag.getElementsByTagName("address").item(0);
                String address = addressTag.getTextContent();

                Element emailTag = (Element) clientTag.getElementsByTagName("email").item(0);
                String email = emailTag.getTextContent();

                Element telephoneNumberTag = (Element) clientTag.getElementsByTagName("telephoneNumber").item(0);
                String telephoneNumber = telephoneNumberTag.getTextContent();

                Order order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);
                order.setId(id);

                allOrders.add(order);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return allOrders;
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = null;
        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("orders");

            NodeList allClientsTag = document.getElementsByTagName("order");
            int tagCount = allClientsTag.getLength();

            for (int i = 0; i < tagCount; i++) {
                Element clientTag = (Element) allClientsTag.item(i);

                Element idTag = (Element) clientTag.getElementsByTagName("id").item(0);
                String idStr = idTag.getTextContent();
                Long dbId = Long.parseLong(idStr);

                if (dbId.equals(id)) {
                    Element lastNameTag = (Element) clientTag.getElementsByTagName("lastName").item(0);
                    String lastName = lastNameTag.getTextContent();

                    Element firstNameTag = (Element) clientTag.getElementsByTagName("firstName").item(0);
                    String firstName = firstNameTag.getTextContent();

                    Element dateOfOrderTag = (Element) clientTag.getElementsByTagName("dateOfOrder").item(0);
                    String dateOfOrderTagStr = dateOfOrderTag.getTextContent();
                    DataUtils dataUtils = new DataUtils();
                    Date dateOfOrder = dataUtils.buildDateFromString(dateOfOrderTagStr);

                    Element addressTag = (Element) clientTag.getElementsByTagName("address").item(0);
                    String address = addressTag.getTextContent();

                    Element emailTag = (Element) clientTag.getElementsByTagName("email").item(0);
                    String email = emailTag.getTextContent();

                    Element telephoneNumberTag = (Element) clientTag.getElementsByTagName("telephoneNumber").item(0);
                    String telephoneNumber = telephoneNumberTag.getTextContent();

                    order = new Order(lastName, firstName, dateOfOrder, address, email, telephoneNumber);
                    order.setId(id);
                    return order;
                }
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Long saveOrder(Order order) {
        try{
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("orders");

            Element rootTag = document.getDocumentElement();
            Element clientTag = document.createElement("order");

            Long id = dataBase.getNexIdForTable("orders");
            Element idTag = document.createElement("id");
            Text idData = document.createTextNode(id.toString());
            idTag.appendChild(idData);
            clientTag.appendChild(idTag);

            Element lastNameTag = document.createElement("lastName");
            Text lastNameData = document.createTextNode(order.getLastName());
            lastNameTag.appendChild(lastNameData);
            clientTag.appendChild(lastNameTag);

            Element firstNameTag = document.createElement("firstName");
            Text firstNameData = document.createTextNode(order.getFirstName());
            firstNameTag.appendChild(firstNameData);
            clientTag.appendChild(firstNameTag);

            Element dateOfOrderTag = document.createElement("dateOfOrder");
            Text dateOfOrderData = document.createTextNode(order.getDateOfOrderStr());
            dateOfOrderTag.appendChild(dateOfOrderData);
            clientTag.appendChild(dateOfOrderTag);

            Element addressTag = document.createElement("address");
            Text addressData = document.createTextNode(order.getAddress());
            addressTag.appendChild(addressData);
            clientTag.appendChild(addressTag);

            Element emailTag = document.createElement("email");
            Text emailData = document.createTextNode(order.getEmail());
            emailTag.appendChild(emailData);
            clientTag.appendChild(emailTag);

            Element telephoneNumberTag = document.createElement("telephoneNumber");
            Text telephoneNumberData = document.createTextNode(order.getTelephoneNumber());
            telephoneNumberTag.appendChild(telephoneNumberData);
            clientTag.appendChild(telephoneNumberTag);

            rootTag.appendChild(clientTag);

            dataBase.saveDbTableDocument("orders");

            return id;

        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void editOrder(Order order) {
        try {
            XmlDataBase dataBase = XmlDataBase.getDatabase();
            Document document = dataBase.getTableDocument("orders");

            NodeList allClientsTag = document.getElementsByTagName("order");
            int tagCount = allClientsTag.getLength();

            Long id = order.getId();
            for (int i = 0; i < tagCount; i++) {
                Element clientTag = (Element) allClientsTag.item(i);

                Element idTag = (Element) clientTag.getElementsByTagName("id").item(0);
                String idStr = idTag.getTextContent();
                Long dbId = Long.parseLong(idStr);

                if (dbId.equals(id)) {
                    Element lastNameTag = (Element) clientTag.getElementsByTagName("lastName").item(0);
                    lastNameTag.setTextContent(order.getLastName());

                    Element firstNameTag = (Element) clientTag.getElementsByTagName("firstName").item(0);
                    firstNameTag.setTextContent(order.getFirstName());

                    Element dateOfOrderTag = (Element) clientTag.getElementsByTagName("dateOfOrder").item(0);
                    String dateOfOrderStr = DataUtils.buildStringFromDate(order.getDateOfOrder());
                    dateOfOrderTag.setTextContent(dateOfOrderStr);

                    Element addressTag = (Element) clientTag.getElementsByTagName("address").item(0);
                    addressTag.setTextContent(order.getAddress());

                    Element emailTag = (Element) clientTag.getElementsByTagName("email").item(0);
                    emailTag.setTextContent(order.getEmail());

                    Element telephoneNumberTag = (Element) clientTag.getElementsByTagName("telephoneNumber").item(0);
                    telephoneNumberTag.setTextContent(order.getTelephoneNumber());

                    dataBase.saveDbTableDocument("orders");

                    return;
                }
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteClient(Long id) throws TransformerException {
        XmlDataBase dataBase = XmlDataBase.getDatabase();
        Document document = dataBase.getTableDocument("orders");
        Element allOrdersParentTag = (Element)document.getElementsByTagName("all-orders").item(0);

        Element orderTag= getOrderTagById(id);
        if(orderTag != null){
            allOrdersParentTag.removeChild(orderTag);
            dataBase.saveDbTableDocument("orders");
        }
    }

    private Element getOrderTagById(Long orderId) {
        XmlDataBase dataBase = XmlDataBase.getDatabase();
        Document document = dataBase.getTableDocument("orders");

        NodeList allOrdersTags = document.getElementsByTagName("order");
        int tagCount = allOrdersTags.getLength();

        for (int i = 0; i <tagCount ; i++) {
            Element orderTag = (Element) allOrdersTags.item(i);

            Element idTag = (Element) orderTag.getElementsByTagName("id").item(0);
            String idStr = idTag.getTextContent();
            Long dbId = Long.parseLong(idStr);

            if (dbId.equals(orderId)) {
                return orderTag;
            }
        }
        return null;
    }
}
