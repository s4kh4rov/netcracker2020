package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseContract {
    private int id;
    private Date startDate;
    private Date expirationDate;
    private Client client;
    private DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

    public BaseContract(int id, String startDate, String expirationDate, Client client) throws ParseException {
        this.id = id;
        this.startDate = dateFormat.parse(startDate);
        this.expirationDate = dateFormat.parse(expirationDate);
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        this.startDate = dateFormat.parse(startDate);
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) throws ParseException {
        this.expirationDate = dateFormat.parse(expirationDate);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", startDate=" + dateFormat.format(startDate) +
                ", expirationDate=" + dateFormat.format(expirationDate) +
                ", client=" + client;
    }
}
