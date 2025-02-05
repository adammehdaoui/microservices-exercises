package com.stamina.student.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
@Getter
@Setter
public class Student {

    @Id
    private String id;

    private String name;

    private String genre;

    private Long schoolId;

}
