package com.jeftech.kbfc.Models;

/**
 * Created by ajith on 12/1/16.
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String customerCode;
    private String city;
    private String state;
    private String address;
    private String phone;
    private String type;
    private String areaId;
    private String divisionCode;

    public Customer() {

    }

    public Customer(int customerId, String customerName, String customerCode, String city, String state, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
