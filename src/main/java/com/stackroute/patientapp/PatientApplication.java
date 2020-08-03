package com.stackroute.patientapp;

import com.stackroute.patientapp.dao.PatientDao;
import com.stackroute.patientapp.dao.PatientDaoImpl;
import com.stackroute.patientapp.exception.PatientExistsException;
import com.stackroute.patientapp.model.Patient;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


/**
 * This is the main application class used for executing
 * CRUD operations on the Database using methods defined in PatientDao
 * Log the results as informational messages
 */
@Slf4j

public class PatientApplication {
    public static void main(String[] args) throws PatientExistsException {
        PatientDao patientDao = new PatientDaoImpl();

        //** TODO **

        patientDao.addPatient(new Patient(null, 2232, "Rukoo", 'F', 34));

        //**TODO**
        // Get a patient given the patientCode

        Optional<Patient> findpatient = patientDao.getPatient(2232);
        findpatient.ifPresent(System.out::println);

        //**TODO**
        // Update patient name given patientCode

        //**TODO**
        // Delete a Patient given patientCode

        //**TODO**
        // Get all patients from the database
    }
}