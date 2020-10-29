package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.clients.Client;

import java.text.ParseException;


public class TVContract extends BaseContract {
    private ChannelPackage chPackage;

    public TVContract(int id, String startDate, String expirationDate, Client client, ChannelPackage chPackage) throws ParseException {
        super(id, startDate, expirationDate, client);
        this.chPackage = chPackage;
    }

    public ChannelPackage getChPackage() {
        return chPackage;
    }

    public void setChPackage(ChannelPackage chPackage) {
        this.chPackage = chPackage;
    }

    @Override
    public String toString() {
        return "TVContract{" +
                super.toString() +
                "chPackage=" + chPackage +
                '}';
    }
}
