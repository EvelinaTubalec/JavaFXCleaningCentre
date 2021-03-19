package com.stormnet.data;

import java.util.Objects;

public class ClientAccount {

    private String telephoneNumber;

    private String password;

    private Long id;

    public ClientAccount(String telephoneNumber, String password) {
        this.telephoneNumber = telephoneNumber;
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAccount that = (ClientAccount) o;
        return Objects.equals(telephoneNumber, that.telephoneNumber) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneNumber, password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
                "telephoneNumber='" + telephoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
