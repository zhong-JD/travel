package com.icis.pojo;



import java.sql.Date;


public class User {

  private Integer id;
  private String name;
  private String gender;
  private Integer age;
  private String address;
  private java.sql.Date birthday;
  private String email;
  private String password;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public Integer getAge() {
    return new java.util.Date().getYear() - birthday.getYear();
  }

  public void setAge(Integer age) {
    this.age = age;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
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

  public User() {
  }

  public User(Integer id, String name, String gender, Integer age, String address, Date birthday, String email, String password) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.age = new java.util.Date().getYear() - birthday.getYear();
    this.address = address;
    this.birthday = birthday;
    this.email = email;
    this.password = password;
  }


  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            ", birthday=" + birthday +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
