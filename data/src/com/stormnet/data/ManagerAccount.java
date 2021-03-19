package com.stormnet.data;

import java.util.Objects;

public class ManagerAccount {

    private String login;

    private String password;

    private Long id;

    public ManagerAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerAccount that = (ManagerAccount) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, id);
    }

    @Override
    public String toString() {
        return "ManagerAccount{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
