package com.epam.task1.entity;


public class User extends Entity {

    public User() {
    }

    public User(final String login, final String password) {
        this.login = login;
        this.password = password;
    }

    public User(final Long id) {
        super(id);
    }

    public User(Long id, String login) {
        super(id);
        this.login = login;
    }

    public User(final Long id, final String login, final String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null)
            return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nUser{ " + super.toString()
                + " login='" + login + '\''
                + ", password='" + password + '\''
                + "} ";
    }
}
