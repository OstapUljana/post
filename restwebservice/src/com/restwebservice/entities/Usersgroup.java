package com.restwebservice.entities;


import javax.persistence.*;
import java.util.Collection;

/**
 * Created by User on 07.10.2014.
 */
@Entity
public class Usersgroup {
    private int idUsersGroup;
    private String type;
    private Collection<Users> usersesByIdUsersGroup;

    @Id
    @Column(name = "idUsersGroup", nullable = false, insertable = true, updatable = true)
    public int getIdUsersGroup() {
        return idUsersGroup;
    }

    public void setIdUsersGroup(int idUsersGroup) {
        this.idUsersGroup = idUsersGroup;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = true, updatable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usersgroup that = (Usersgroup) o;

        if (idUsersGroup != that.idUsersGroup) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsersGroup;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersgroupByUsersGroup")
    public Collection<Users> getUsersesByIdUsersGroup() {
        return usersesByIdUsersGroup;
    }

    public void setUsersesByIdUsersGroup(Collection<Users> usersesByIdUsersGroup) {
        this.usersesByIdUsersGroup = usersesByIdUsersGroup;
    }
}
