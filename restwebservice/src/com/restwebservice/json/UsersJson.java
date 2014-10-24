package com.restwebservice.json;

import com.restwebservice.entities.Users;

public class UsersJson {
	private int id;
    private String eMail;
    private String name;
    private String group;

    public UsersJson(Users user) {
        if (user == null) {
            this.id = 0;
            this.eMail = null;
            this.name = null;
            this.group = null;
        } else {
            this.id = user.getIdUsers();
            this.eMail = user.geteMail();
            this.name = user.getName();
            this.group = user.getUsersgroupByUsersGroup().getType();
        }
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String email) {
        this.eMail = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + eMail + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

