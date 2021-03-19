package com.stormnet.data;

import java.util.Objects;

public class Account {

    private String login;

    private String password;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(login, account.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "com.stormnet.data.Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
