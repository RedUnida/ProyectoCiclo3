package com.example.ProyectoCiclo3.Entities;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "Profile")

public class Profile {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "image")
    private String image;

    @Column (name = "phone")
    private String phone;

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    @Column (name = "email")
    private String emails;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column (name = "password")
    private String password;

    @Column (name = "createdat")
    private Date createdat;

    @Column (name = "updateat")
    private Date updatedat;
}
