package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {

    public Dog() {
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(columnDefinition = "varchar(200) unsigned", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(columnDefinition = "char(2) DEFAULT 'XX'")
    private String resideState;

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
    @Column(columnDefinition = "INT UNSIGNED", nullable = false, unique = true)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
//    private long id;
//
//    private String title;
//
//    @Column(columnDefinition = "TEXT NOT NULL")
//    private String description;
//}
