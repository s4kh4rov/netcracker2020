package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;

import java.text.ParseException;

/**
 * The class represents a internet contract
 */
public class InternetContract extends BaseContract {
    /**
     * represents the maximum internet speed in Mbps
     */
    private double maxSpeed;

    public InternetContract() {
    }

    /**
     * @param id             contract id
     * @param startDate      a string representing the start date
     * @param expirationDate a string representing the expiration date
     * @param client         client
     * @param maxSpeed       maximum internet speed in Mbps
     * @throws ParseException an exception if the input date format is incorrect
     */
    public InternetContract(int id, String startDate, String expirationDate, Client client, double maxSpeed) throws ParseException {
        super(id, startDate, expirationDate, client);
        this.maxSpeed = maxSpeed;
    }

    /**
     * gets max speed
     *
     * @return {@link #maxSpeed}
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * sets max speed
     *
     * @param maxSpeed {@link #maxSpeed}
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return string with a full description of the internet contract
     */
    @Override
    public String toString() {
        return "InternetContract{" +
                super.toString() +
                "maxSpeed=" + maxSpeed +
                '}';
    }
}
