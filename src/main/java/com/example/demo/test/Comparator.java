package com.example.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Comparator {

  @Data
  static class Person {
    String name;
    int age;
  }

  public static void main(String[] args) {
    Person p1 = new Person();
    p1.setAge(2);

    Person p2 = new Person();
    p2.setAge(2);

    Person p3 = new Person();
    p3.setAge(2);

    Person p4 = new Person();
    p4.setAge(2);

    Map<Person, List<Person>> map = new HashMap<>();
    List<Person> list = new ArrayList<>();
    /*list.add(p1);
    list.add(p2);
    list.add(p3);
    list.add(p4);*/

    map.put(p1, list);

    List<Person> parentRecFileList = map.keySet().stream()
        .sorted(java.util.Comparator.comparing(Person::getAge).thenComparing(Person::getName)).collect(
            Collectors.toList());

    System.out.println(11);
  }
}
