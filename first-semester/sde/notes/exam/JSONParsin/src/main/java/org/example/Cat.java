package org.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Cat {

    private String name;
    
    private int age;

    private boolean isDead;

    private List<Cat> friends;

    public Cat(String name, int age, boolean isDead, List<Cat> friends) {
        this.name = name;
        this.age = age;
        this.isDead = isDead;
        this.friends = friends;
    }

    public Cat(String name, int age, boolean isDead) {
        this.name = name;
        this.age = age;
        this.isDead = isDead;
        this.friends = new ArrayList<Cat>();
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isDead() {
        return isDead;
    }

    public List<Cat> getFriends() {
        return friends;
    }

    public JSONObject toJSONObject() {
        var root = new JSONObject();
        root.put("name", name);
        root.put("age", age);
        root.put("isDead", isDead);
        var friends = new ArrayList<JSONObject>();
        for (var friend : this.friends) {
            var obj = new JSONObject();
            obj.put("name", friend.getName());
            obj.put("age", friend.getAge());
            obj.put("isDead", friend.isDead());
            friends.add(obj);
        }
        root.put("friends", friends);
        return root;
    }


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isDead=" + isDead +
                ", friends=" + friends +
                '}';
    }
}
