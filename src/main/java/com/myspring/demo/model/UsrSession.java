package com.myspring.demo.model;

public class UsrSession {
    private int id;
    private String name;
    private int type;

    public UsrSession(int id,String name,int type){
        this.id=id;
        this.name=name;
        this.type=type;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }
}
