package com.springapp.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ivanybma on 3/27/16.
 */
@Entity
@Table(name="Profile")
@NamedQuery(name="findAll", query="SELECT c FROM Profile c")
public class Profile implements Serializable {

    private Long system_id;
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String organization;
    private String aboutMyself;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="System_Id")
    public Long getSystem_id() {
        return system_id;
    }

    public void setSystem_id(Long system_id) {
        this.system_id = system_id;
    }
    @Column(name="Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name="FirstName")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name="LastName")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name="Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="Organization")
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Column(name="AboutMyself")
    public String getAboutMyself() {
        return aboutMyself;
    }

    public void setAboutMyself(String aboutMyself) {
        this.aboutMyself = aboutMyself;
    }

    public String toString(){
        return "System_Id: "+this.system_id + "id: " + this.id + " FirstName: " + this.firstname +
                " LastName: " + this.lastname + " Email: " + this.email + " Address: "+this.address +
                " Orgainzation: "+this.organization + " AboutMyself: "+this.aboutMyself;
    }
}
