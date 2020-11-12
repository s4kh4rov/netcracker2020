package com.netcracker.contractsProject.parser;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.enums.MobileTariff;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.InternetContract;
import com.netcracker.contractsProject.сontracts.TVContract;

import java.util.function.Function;

public class Functions {
    public static Function<Object[], InternetContract> toInternetContract() {
        Function<Object[], InternetContract> f = objects -> {
            InternetContract internetContract = new InternetContract();
            internetContract.setClient((Client) objects[0]);
            internetContract.setId(Integer.parseInt(objects[1].toString()));
            internetContract.setStartDate(objects[2].toString());
            internetContract.setExpirationDate(objects[3].toString());
            internetContract.setMaxSpeed(Double.parseDouble(objects[4].toString()));
            return internetContract;
        };
        return f;
    }

    public static Function<Object[], TVContract> toTvContract() {
        Function<Object[], TVContract> f = objects -> {
            TVContract tvContract = new TVContract();
            tvContract.setClient((Client) objects[0]);
            tvContract.setId(Integer.parseInt(objects[1].toString()));
            tvContract.setStartDate(objects[2].toString());
            tvContract.setExpirationDate(objects[3].toString());
            tvContract.setChPackage(ChannelPackage.valueOf(objects[4].toString().toUpperCase()));
            return tvContract;
        };
        return f;
    }

    public static Function<Object[], CellularContract> toCellularContract() {
        Function<Object[], CellularContract> f = objects -> {
            CellularContract cellularContract = new CellularContract();
            cellularContract.setClient((Client) objects[0]);
            cellularContract.setId(Integer.parseInt(objects[1].toString()));
            cellularContract.setStartDate(objects[2].toString());
            cellularContract.setExpirationDate(objects[3].toString());
            cellularContract.setTariff(MobileTariff.valueOf(objects[4].toString().toUpperCase()));
            return cellularContract;
        };
        return f;
    }
}
