package com.stormnet.data;

import com.stormnet.utils.date.DataUtils;
import java.util.Date;
import java.util.Objects;

public class Order {

    private Long id;

    private String firstName;

    private String lastName;

    private Date dateOfOrder;

    private String address;

    private String email;

    private String telephoneNumber;

    public Order(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Order(String firstName, Date dateOfOrder, String address) {
        this.lastName = firstName;
        this.dateOfOrder = dateOfOrder;
        this.address = address;
    }

    public Order(String lastName, String firstName, Date dateOfOrder, String address, String email, String telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfOrder = dateOfOrder;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDateOfOrderStr() {
        return DataUtils.buildStringFromDate(dateOfOrder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(firstName, order.firstName) &&
                Objects.equals(lastName, order.lastName) &&
                Objects.equals(dateOfOrder, order.dateOfOrder) &&
                Objects.equals(address, order.address) &&
                Objects.equals(email, order.email) &&
                Objects.equals(telephoneNumber, order.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfOrder, address, email, telephoneNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfOrder=" + dateOfOrder +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
