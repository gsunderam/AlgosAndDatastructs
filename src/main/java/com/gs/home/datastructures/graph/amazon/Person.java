package com.gs.home.datastructures.graph.amazon;

import com.gs.home.log.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ID of this person and his freinds as Person objects
 */
public class Person {
    int id;
    public List<Person> friends = Collections.emptyList();

    public Person(){}

    public Person(int id) {
        this.id = id;
    }

    public Person(int id, List<Person> friends) {
        this.id = id;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public List<Person> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        List<Integer> list = friends.stream().map(f -> f.getId()).collect(Collectors.toList());
        return "Person{" + id +  ", Friends=" + list + "}";
    }
}
