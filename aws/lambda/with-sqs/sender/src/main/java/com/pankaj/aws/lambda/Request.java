package com.pankaj.aws.lambda;

/**
 * Created by pankaj on 11/2/2018.
 */
public class Request {
    private int id;
    private String name;

    public int getId() {
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


    public Request(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Request() {
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
