package com.netcracker.contractsProject.repositories;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.enums.MobileTariff;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.TVContract;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void add() throws ParseException {
        Repository<BaseContract> repository = new Repository<>();
        CellularContract contract = new CellularContract(1, "01 02 2020", "03 02 2020", new Client(), MobileTariff.SMART);
        TVContract tvContract = new TVContract(2, "02 03 2019", "02 03 2020", new Client(), ChannelPackage.BASE);
        repository.add(contract);
        repository.add(tvContract);
        assertEquals(repository.size(), 2);
    }

    @Test
    void get() throws ParseException {
        Repository<BaseContract> repository = new Repository<>();
        CellularContract contract = new CellularContract(1, "01 02 2020", "03 02 2020", new Client(), MobileTariff.SMART);
        TVContract tvContract = new TVContract(2, "02 03 2019", "02 03 2020", new Client(), ChannelPackage.BASE);
        repository.add(contract);
        repository.add(tvContract);

        assertFalse(repository.get(0).isPresent());
        assertTrue(repository.get(1).isPresent());
        assertTrue(repository.get(2).isPresent());

        assertEquals(contract, repository.get(1).get());
        assertEquals(tvContract, repository.get(2).get());
    }

    @Test
    void delete() throws ParseException {
        Repository<BaseContract> repository = new Repository<>();
        CellularContract contract = new CellularContract(1, "01 02 2020", "03 02 2020", new Client(), MobileTariff.SMART);
        TVContract tvContract = new TVContract(2, "02 03 2019", "02 03 2020", new Client(), ChannelPackage.BASE);
        repository.add(contract);
        repository.add(tvContract);
        repository.delete(0);

        assertFalse(repository.get(1).isPresent());
        assertEquals(repository.size(), 1);

    }
}