package com.stackroute.patientapp.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import com.stackroute.patientapp.exception.PatientExistsException;
import com.stackroute.patientapp.model.Patient;
import com.stackroute.patientapp.util.ConnectionUtility;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * This class implements the PatientDao interface
 * **TODO**
 * PatientDao interface methods should be implemented in this class
 * Log appropriate error/info messages wherever necessary
 */
@Slf4j
public class PatientDaoImpl implements PatientDao {

    public static final String PATIENT_CODE = "patientCode";
    private final MongoCollection<Patient> mongoCollection;

    /**
     * **TODO**
     * Initialize mongoCollection variable using ConnectionUtility class
     */
    public PatientDaoImpl() {

        this.mongoCollection = ConnectionUtility.getCollection();
    }

    /**
     * **TODO**
     * This method should get the patient with the passed patientCode
     * If the patient with given patientCode does not exist in database, it should return an empty optional
     */
    @Override
    public Optional<Patient> getPatient(int patientCode) {

        Patient findPatient = mongoCollection.find(Filters.eq("patientCode",patientCode)).first();
        if(findPatient == null) {

            return Optional.empty();
        }
        else {
            return Optional.of(findPatient);
        }
    }

    /**
     * **TODO**
     * This method should add new patient to the Cosmos DB database
     * If the patient with given patientCode already exists in database, then throw PatientExistsException
     * If the input is null, throw IllegalArgumentException
     */
    @Override
    public Patient addPatient(Patient newPatient) throws PatientExistsException {

        mongoCollection.insertOne(newPatient);

        return null;
    }

    /**
     * **TODO**
     * This method should update the name of an existing patient
     * Return false if patient does not exist
     * If the input name is null or empty, throw IllegalArgumentException
     */
    @Override
    public boolean updatePatient(int patientCode, String name) {

        if(name == null || name.isEmpty()) {
        log.error("patient name is empty or null");
        throw new IllegalArgumentException();
        }

        Patient patient = mongoCollection.find(Filters.eq("patientCode", patientCode)).first();
        if(patient == null) {
            return false;
        }

        UpdateResult updatePatientDetails = mongoCollection.updateOne(Filters.eq("patientCode", patientCode), Updates.set("name", name));

           return updatePatientDetails.wasAcknowledged();
    }

    /**
     * **TODO**
     * This method should delete the patient from the database for the given patientCode
     */

    @Override
    public boolean deletePatient(int patientCode) {

        return mongoCollection.deleteOne(Filters.eq("patientCode", patientCode)).wasAcknowledged();
    }

    /**
     * **TODO**
     * This method should return a List of All Patients from the database
     */
    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        mongoCollection.find().forEach((Consumer<Patient>) patient -> patients.add(patient));

        return patients;
    }

}