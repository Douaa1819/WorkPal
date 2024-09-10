package com.workpal.models;

public class Membre extends Personne {
    private String phone;

    public Membre(int id, String name, String email, String password, int role_id, String phone) {
        super(id, name, email, password, role_id);
        this.phone = phone;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}


