package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.clients.Client;

import java.text.ParseException;

/**
 * The class represents a TV contract
 */
public class TVContract extends BaseContract {
    /**
     * represents one of the channel package from the ChannelPackage enum
     */
    private ChannelPackage chPackage;

    public TVContract() {
    }

    /**
     * @param id             contract id
     * @param startDate      a string representing the start date
     * @param expirationDate a string representing the expiration date
     * @param client         client
     * @param chPackage      one of the channel package from the ChannelPackage enum
     * @throws ParseException an exception if the input date format is incorrect
     */
    public TVContract(int id, String startDate, String expirationDate, Client client, ChannelPackage chPackage) throws ParseException {
        super(id, startDate, expirationDate, client);
        this.chPackage = chPackage;
    }

    /**
     * gets channel package
     *
     * @return {@link #chPackage}
     */
    public ChannelPackage getChPackage() {
        return chPackage;
    }

    /**
     * sets channel package
     *
     * @param chPackage {@link #chPackage}
     */
    public void setChPackage(ChannelPackage chPackage) {
        this.chPackage = chPackage;
    }

    /**
     * @return string with a full description of the TV contract
     */
    @Override
    public String toString() {
        return "TVContract{" +
                super.toString() +
                "chPackage=" + chPackage +
                '}';
    }
}
