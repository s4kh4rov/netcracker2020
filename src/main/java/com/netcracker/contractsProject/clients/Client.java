package com.netcracker.contractsProject.clients;

import com.netcracker.contractsProject.enums.Gender;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private Date dateOfBirth;
    private Gender gender;
    private int passportSeries;
    private int passportID;
    private DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

    public Client() {
    }

    public Client(int id, String name, String surname, String patronymic, String dateOfBirth, Gender gender, int passportSeries, int passportID) throws ParseException {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = dateFormat.parse(dateOfBirth);
        this.gender = gender;
        this.passportSeries = passportSeries;
        this.passportID = passportID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        this.dateOfBirth = dateFormat.parse(dateOfBirth);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportID() {
        return passportID;
    }

    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }
}
