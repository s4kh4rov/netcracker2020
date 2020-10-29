package com.netcracker.contractsProject.сontracts;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.MobileTariff;

import java.text.ParseException;


public class CellularContract extends BaseContract {
    private MobileTariff tariff;

    public CellularContract(int id, String startDate, String expirationDate, Client client, MobileTariff tariff) throws ParseException {
        super(id, startDate, expirationDate, client);
        this.tariff = tariff;
    }

    public MobileTariff getTariff() {
        return tariff;
    }

    public void setTariff(MobileTariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return "CellularContract{" +
                super.toString() +
                "tariff=" + tariff +
                '}';
    }
}
