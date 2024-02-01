package com.test.jie.lombok;

public class UserSearchRequest {
    private String name;
    private String context;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "UserSearchRequest{" +
                "name='" + name + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
