package com.icis.pojo;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/12 19:47
 */
public class ElseUser {
    private String selectname;
    private String selectaddress;
    private String selectgender;

    public ElseUser() {
    }

    public ElseUser(String selectname, String selectaddress, String selectgender) {
        this.selectname = selectname;
        this.selectaddress = selectaddress;
        this.selectgender = selectgender;
    }

    public String getSelectname() {
        return selectname;
    }

    public void setSelectname(String selectname) {
        this.selectname = selectname;
    }

    public String getSelectaddress() {
        return selectaddress;
    }

    public void setSelectaddress(String selectaddress) {
        this.selectaddress = selectaddress;
    }

    public String getSelectgender() {
        return selectgender;
    }

    public void setSelectgender(String selectgender) {
        this.selectgender = selectgender;
    }

    @Override
    public String toString() {
        return "ElseUser{" +
                "selectname='" + selectname + '\'' +
                ", selectaddress='" + selectaddress + '\'' +
                ", selectgender='" + selectgender + '\'' +
                '}';
    }
}
