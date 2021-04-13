package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.saving.adapter.BaseContractAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * The class represents a generic contract
 */
@XmlJavaTypeAdapter(BaseContractAdapter.class)
public class BaseContract {
    /**
     * contract id
     */
    private int id;

    /**
     * start date
     */
    private LocalDate startDate;

    /**
     * contract expiration date
     */
    private LocalDate expirationDate;

    /**
     * client with whom the contract was concluded
     */
    private Client client;

    /**
     * the date format used in this class
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public BaseContract() {
    }

    /**
     * BaseContract constructor with all fields as parameters
     *
     * @param id             contract id
     * @param startDate      a string representing the start date
     * @param expirationDate a string representing the expiration date
     * @param client         client
     */
    public BaseContract(int id, String startDate, String expirationDate, Client client) {
        this.id = id;
        this.startDate = LocalDate.parse(startDate, formatter);
        this.expirationDate = LocalDate.parse(expirationDate, formatter);
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
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * sets start date from string
     *
     * @param startDate a string representing the start date
     */
    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, formatter);
    }

    /**
     * gets expiration date
     *
     * @return {@link #expirationDate}
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * sets expiration date from string
     *
     * @param expirationDate a string representing the expiration date
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = LocalDate.parse(expirationDate, formatter);
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
                ", startDate=" + startDate.format(formatter) +
                ", expirationDate=" + expirationDate.format(formatter) +
                ", client=" + client;
    }
}
