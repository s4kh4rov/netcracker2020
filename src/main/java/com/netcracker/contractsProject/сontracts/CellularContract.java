package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.MobileTariff;


/**
 * The class represents a cellular contract
 */
public class CellularContract extends BaseContract {
    /**
     * represents one of the cellular tariffs from the MobileTariff enum
     */
    private MobileTariff tariff;

    public CellularContract() {
    }


    /**
     * @param id             contract id
     * @param startDate      a string representing the start date
     * @param expirationDate a string representing the expiration date
     * @param client         client
     * @param tariff         represents one of the cellular tariffs from the MobileTariff enum
     */
    public CellularContract(int id, String startDate, String expirationDate, Client client, MobileTariff tariff) {
        super(id, startDate, expirationDate, client);
        this.tariff = tariff;
    }

    /**
     * gets cellular tariff
     *
     * @return {@link #tariff}
     */
    public MobileTariff getTariff() {
        return tariff;
    }

    /**
     * sets cellular tariff
     *
     * @param tariff {@link #tariff}
     */
    public void setTariff(MobileTariff tariff) {
        this.tariff = tariff;
    }

    /**
     * @return string with a full description of the cellular contract
     */
    @Override
    public String toString() {
        return "CellularContract{" +
                super.toString() +
                "tariff=" + tariff +
                '}';
    }
}
