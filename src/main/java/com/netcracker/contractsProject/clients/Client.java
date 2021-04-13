package com.netcracker.contractsProject.clients;

import com.netcracker.contractsProject.enums.Gender;
import com.netcracker.contractsProject.saving.adapter.LocalDateAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private String patronymic;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dateOfBirth;
    @XmlElement
    private Gender gender;
    @XmlElement
    private int passportSeries;
    @XmlElement
    private int passportID;
    @XmlTransient
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public Client() {
    }

    public Client(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
    }

    public Client(int id, String name, String surname, String patronymic, String dateOfBirth, Gender gender, int passportSeries, int passportID) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
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

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(passportID)
                .append(passportSeries)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Client c = (Client) obj;
        return new EqualsBuilder()
                .append(passportID, c.passportID)
                .append(passportSeries, c.passportSeries)
                .isEquals();
    }
}
