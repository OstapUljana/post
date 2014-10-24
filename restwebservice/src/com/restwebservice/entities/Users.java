package com.restwebservice.entities;



import javax.persistence.*;
import java.util.Collection;

/**
 * Created by User on 07.10.2014.
 */
@Entity
public class Users {
    private int idUsers;
    private String eMail;
    private String password;
    private String name;
    private Collection<Article> articlesByIdUsers;
    private Usersgroup usersgroupByUsersGroup;

    @Id
    @Column(name = "idUsers", nullable = false, insertable = true, updatable = true)
    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    @Basic
    @Column(name = "eMail", nullable = true, insertable = true, updatable = true, length = 45)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (idUsers != users.idUsers) return false;
        if (eMail != null ? !eMail.equals(users.eMail) : users.eMail != null) return false;
        if (name != null ? !name.equals(users.name) : users.name != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsers;
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /*@OneToMany(mappedBy = "usersByIdUsers")
    public Collection<Article> getArticlesByIdUsers() {
        return articlesByIdUsers;
    }

    public void setArticlesByIdUsers(Collection<Article> articlesByIdUsers) {
        this.articlesByIdUsers = articlesByIdUsers;
    }*/

    @ManyToOne
    @JoinColumn(name = "usersGroup", referencedColumnName = "idUsersGroup")
    public Usersgroup getUsersgroupByUsersGroup() {
        return usersgroupByUsersGroup;
    }

    public void setUsersgroupByUsersGroup(Usersgroup usersgroupByUsersGroup) {
        this.usersgroupByUsersGroup = usersgroupByUsersGroup;
    }
}
