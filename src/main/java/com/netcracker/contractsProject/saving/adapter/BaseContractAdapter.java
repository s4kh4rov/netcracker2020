package com.netcracker.contractsProject.saving.adapter;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.enums.MobileTariff;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.InternetContract;
import com.netcracker.contractsProject.сontracts.TVContract;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * The adapter class contains the logic for saving and restoring contracts in xml format
 */
public class BaseContractAdapter extends XmlAdapter<BaseContractAdapter.AdaptedBaseContract, BaseContract> {
    /**
     * Convert a value type to a bound type
     *
     * @param v the value to be converted
     * @throws Exception- if there's an error during the conversion
     */
    @Override
    public BaseContract unmarshal(AdaptedBaseContract v) throws Exception {
        if (v == null) {
            return null;
        }
        BaseContract baseContract = new BaseContract();
        baseContract.setId(v.id);
        baseContract.setStartDate(v.startDate.toString());
        baseContract.setExpirationDate(v.expirationDate.toString());
        baseContract.setClient(v.client);
        if (v.maxSpeed != null) {
            InternetContract ic = new InternetContract();
            ic.setId(v.id);
            ic.setStartDate(v.startDate.toString());
            ic.setExpirationDate(v.expirationDate.toString());
            ic.setClient(v.client);
            ic.setMaxSpeed(v.maxSpeed);
            return ic;
        } else if (v.chPackage != null) {
            TVContract tvContract = new TVContract();
            tvContract.setId(v.id);
            tvContract.setStartDate(v.startDate.toString());
            tvContract.setExpirationDate(v.expirationDate.toString());
            tvContract.setClient(v.client);
            tvContract.setChPackage(v.chPackage);
            return tvContract;
        } else {
            CellularContract cellularContract = new CellularContract();
            cellularContract.setId(v.id);
            cellularContract.setStartDate(v.startDate.toString());
            cellularContract.setExpirationDate(v.expirationDate.toString());
            cellularContract.setClient(v.client);
            cellularContract.setTariff(v.tariff);
            return cellularContract;
        }
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v the value to be convereted
     * @throws Exception- if there's an error during the conversion
     */
    @Override
    public AdaptedBaseContract marshal(BaseContract v) throws Exception {
        if (v == null) {
            return null;
        }
        AdaptedBaseContract adaptedBaseContract = new AdaptedBaseContract();
        adaptedBaseContract.id = v.getId();
        adaptedBaseContract.startDate = v.getStartDate();
        adaptedBaseContract.expirationDate = v.getExpirationDate();
        adaptedBaseContract.client = v.getClient();
        if (v instanceof CellularContract) {
            CellularContract contract = (CellularContract) v;
            adaptedBaseContract.tariff = contract.getTariff();
        } else if (v instanceof InternetContract) {
            InternetContract ic = (InternetContract) v;
            adaptedBaseContract.maxSpeed = ic.getMaxSpeed();
        } else {
            TVContract tvc = (TVContract) v;
            adaptedBaseContract.chPackage = tvc.getChPackage();
        }
        return adaptedBaseContract;
    }

    /**
     * Class containing the fields of the base contract and contracts inherited from it
     */
    public static class AdaptedBaseContract {
        @XmlElement(name = "id")
        private int id;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate expirationDate;
        @XmlElement(name = "client")
        private Client client;
        @XmlElement(name = "tariff")
        private MobileTariff tariff;
        @XmlElement(name = "maxSpeed")
        private Double maxSpeed;
        @XmlElement(name = "chPackage")
        private ChannelPackage chPackage;
    }
}
