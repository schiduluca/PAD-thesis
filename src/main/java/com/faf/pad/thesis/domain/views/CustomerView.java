package com.faf.pad.thesis.domain.views;

import org.springframework.hateoas.ResourceSupport;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerView extends ResourceSupport {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private Integer age;

    public CustomerView() {
    }

    public CustomerView(Long id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
