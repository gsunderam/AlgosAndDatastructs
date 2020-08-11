package com.gs.home.datastructures.graph.amazon;

import java.util.*;

/**
 * Need to tweak this class per your test case. Add freinds for your person etc...
 */
public class Utility {
    public static Map<Integer, Person> createPersonNodes() {
        Map<Integer, Person> people = new HashMap<>();

        List<Person> friends1 = new ArrayList<>();
        List<Person> friends2 = new ArrayList<>();
        List<Person> friends3 = new ArrayList<>();
        List<Person> friends7 = new ArrayList<>();
        List<Person> friends8 = new ArrayList<>();
        List<Person> friends9 = new ArrayList<>();
        Person p1 = new Person(1, friends1);
        Person p2 = new Person(2, friends2);
        Person p3 = new Person(3, friends3);
        Person p4 = new Person(4);
        Person p5 = new Person(5);
        Person p6 = new Person(6);
        Person p7 = new Person(7, friends7);
        Person p8 = new Person(8, friends8);
        Person p9 = new Person(9, friends9);

        friends1.addAll(Arrays.asList(p2, p3));
        friends2.addAll(Arrays.asList(p4, p5));
        friends3.addAll(Arrays.asList(p6, p7));
        friends7.addAll(Arrays.asList(p3, p8, p9));
        friends8.add(p7);friends9.add(p7);

        people.put(1, p1);
        people.put(2, p2);
        people.put(3, p3);
        people.put(4, p4);
        people.put(5, p5);
        people.put(6, p6);
        people.put(7, p7);
        people.put(8, p8);
        people.put(9, p9);

        return people;
    }
}
