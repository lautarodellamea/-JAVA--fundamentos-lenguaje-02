package com.empresa.project.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BasicExample {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Serialize: Java object -> JSON
        Person person = new Person("Lautaro", 30, true);
        String json = gson.toJson(person);
        System.out.println(json);

        // Deserialize: JSON -> Java object
        Person fromJson = gson.fromJson(json, Person.class);
        System.out.println(fromJson);
    }
}
