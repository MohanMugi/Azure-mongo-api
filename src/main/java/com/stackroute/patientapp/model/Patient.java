package com.stackroute.patientapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient {
    private ObjectId id;
    private int patientCode;
    private String name;
    private Character gender;
    private int age;
}
