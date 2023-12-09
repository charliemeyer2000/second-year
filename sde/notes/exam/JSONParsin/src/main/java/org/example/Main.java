package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException {

        // practicing arrayLists and stuff
        Cat friend1 = new Cat("Kitty", 2, false);
        Cat friend2 = new Cat("Tom", 4, true);

        var root = new JSONObject();
        root.put("name", "Honk Shoo");
        // Convert Cat objects to JSONObjects and add them to JSONArray
        JSONArray jsonArray = new JSONArray();
        for (Cat cat : List.of(friend1, friend2)) {
            JSONObject catJson = new JSONObject();
            catJson.put("name", cat.getName());
            catJson.put("age", cat.getAge());
            catJson.put("isDead", cat.isDead());
            jsonArray.put(catJson);
        }

        root.put("list", jsonArray);
        System.out.println(root.toString(2));
    }

    // public void objectToJSON() {

    //     var cat = new Cat("Mimi", 3, false, new ArrayList<Cat>(List.of(friend1, friend2)));
    //     JSONObject obj = cat.toJSONObject();

    //     System.out.println(obj.toString(2));
    // }

    public void JSONtoObjects() throws IOException {
                // read json practice, json->obj, obj->json

        String fileName = "obj.json";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = br.readLine();
        }

        String json = sb.toString();
        System.out.println(json);

        var root = new JSONObject(json);
        var name = root.getString("name");
        var age = root.getInt("age");
        var isDead = root.getBoolean("isDead");
        var friends = root.getJSONArray("friends");
        List<Cat> friendList = new ArrayList<>();
        for (int i = 0; i < friends.length(); i++) {
            var obj = friends.getJSONObject(i);
            var friendName = obj.getString("name");
            var friendAge = obj.getInt("age");
            var friendIsDead = obj.getBoolean("isDead");
            friendList.add(new Cat(friendName, friendAge, friendIsDead));
        }

        var cat = new Cat(name, age, isDead, friendList);
        System.out.println(cat);
    }
}