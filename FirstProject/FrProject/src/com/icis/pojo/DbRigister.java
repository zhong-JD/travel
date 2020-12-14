package com.icis.pojo;


public class DbRigister {

  private int id;
  private String name;
  private String password;
  private String shenfenma;

  public DbRigister(int id, String name, String password, String shenfenma) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.shenfenma = shenfenma;
  }

  public DbRigister() {
  }



  public long getId() {
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


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getShenfenma() {
    return shenfenma;
  }

  public void setShenfenma(String shenfenma) {
    this.shenfenma = shenfenma;
  }

  @Override
  public String toString() {
    return "DbRigister{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", shenfenma='" + shenfenma + '\'' +
            '}';
  }
}
