package org.example;

import org.json.JSONObject;

public class Grandma {

    private String name;

    private int age;

    public Grandma(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("age", this.age);
        return obj;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Grandma [name=" + name + ", age=" + age + "]";
    }
    
}
