package com.workpal.models;

public class Personne {
    private int id;
    private String name;
    private String email;
    private String password;
    private int role_id;

    public Personne(int id, String name, String email, String password, int role_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int roleId) {
        this.role_id = roleId;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", role= membre"+ '}';
    }
}
