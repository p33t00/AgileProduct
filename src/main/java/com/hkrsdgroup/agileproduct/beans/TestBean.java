package com.hkrsdgroup.agileproduct.beans;

public class TestBean {
    private int id = 0;
    private String test_field = null;

    public TestBean() {
    }

    public TestBean(int id, String test_field) {
        this.id = id;
        this.test_field = test_field;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest_field() {
        return test_field;
    }

    public void setTest_field(String test_field) {
        this.test_field = test_field;
    }
}
