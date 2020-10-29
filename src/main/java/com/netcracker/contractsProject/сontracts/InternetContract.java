package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;

import java.text.ParseException;


public class InternetContract extends BaseContract {
    private double maxSpeed;

    public InternetContract(int id, String startDate, String expirationDate, Client client, double maxSpeed) throws ParseException {
        super(id, startDate, expirationDate, client);
        this.maxSpeed = maxSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "InternetContract{" +
                super.toString() +
                "maxSpeed=" + maxSpeed +
                '}';
    }
}
