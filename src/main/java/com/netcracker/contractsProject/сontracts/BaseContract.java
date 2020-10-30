package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class represents a generic contract
 */
public class BaseContract {
    /**
     * contract id
     */
    private int id;

    /**
     * start date
     */
    private Date startDate;

    /**
     * contract expiration date
     */
    private Date expirationDate;

    /**
     * client with whom the contract was concluded
     */
    private Client client;

    /**
     * the date format used in this class
     */
    private DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

    public BaseContract() {
    }

    /**
     * BaseContract constructor with all fields as parameters
     *
     * @param id             contract id
     * @param startDate      a string representing the start date
     * @param expirationDate a string representing the expiration date
     * @param client         client
     * @throws ParseException an exception if the input date format is incorrect
     */
    public BaseContract(int id, String startDate, String expirationDate, Client client) throws ParseException {
        this.id = id;
        this.startDate = dateFormat.parse(startDate);
        this.expirationDate = dateFormat.parse(expirationDate);
        this.client = client;
    }

    /**
     * gets contract id
     *
     * @return {@link #id}
     */
    public int getId() {
        return id;
    }

    /**
     * sets contract id
     *
     * @param id {@link #id}
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * gets start date
     *
     * @return {@link #startDate}
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * sets start date from string
     *
     * @param startDate a string representing the start date
     * @throws ParseException an exception if the input date format is incorrect
     */
    public void setStartDate(String startDate) throws ParseException {
        this.startDate = dateFormat.parse(startDate);
    }

    /**
     * gets expiration date
     *
     * @return {@link #expirationDate}
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * sets expiration date from string
     *
     * @param expirationDate a string representing the expiration date
     * @throws ParseException an exception if the input date format is incorrect
     */
    public void setExpirationDate(String expirationDate) throws ParseException {
        this.expirationDate = dateFormat.parse(expirationDate);
    }

    /**
     * gets client with whom the contract was concluded
     *
     * @return {@link #client}
     */
    public Client getClient() {
        return client;
    }

    /**
     * sets client with whom the contract was concluded
     *
     * @param client {@link #client}
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return string with a full description of the base contract
     */
    @Override
    public String toString() {
        return "id=" + id +
                ", startDate=" + dateFormat.format(startDate) +
                ", expirationDate=" + dateFormat.format(expirationDate) +
                ", client=" + client;
    }
}
