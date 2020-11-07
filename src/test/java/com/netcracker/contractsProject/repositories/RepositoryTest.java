package com.netcracker.contractsProject.repositories;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.enums.MobileTariff;
import com.netcracker.contractsProject.repositories.search.SearchCriteria;
import com.netcracker.contractsProject.repositories.sort.ComparsionCriteria;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.InternetContract;
import com.netcracker.contractsProject.сontracts.TVContract;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void add() throws ParseException {
        Repository<BaseContract> repository = new Repository<>();
        CellularContract contract = new CellularContract(1, "01.02.2020", "03.02.2020", new Client(), MobileTariff.SMART);
        TVContract tvContract = new TVContract(2, "02.03.2019", "02.03.2020", new Client(), ChannelPackage.BASE);
        repository.add(contract);
        repository.add(tvContract);
        assertEquals(repository.size(), 2);
    }

    @Test
    void getById() throws ParseException {
        Repository<BaseContract> repository = new Repository<>();
        CellularContract contract = new CellularContract(1, "01.02.2020", "03.02.2020", new Client(), MobileTariff.SMART);
        TVContract tvContract = new TVContract(2, "02.03.2019", "02.03.2020", new Client(), ChannelPackage.BASE);
        repository.add(tvContract);
        repository.add(contract);

        assertFalse(repository.getById(0).isPresent());
        assertTrue(repository.getById(1).isPresent());
        assertTrue(repository.getById(2).isPresent());

        assertEquals(contract, repository.getById(1).get());
        assertEquals(tvContract, repository.getById(2).get());
    }

    @Test
    void get() {
        InternetContract ic = new InternetContract(1, "22.08.2000", "14.09.2025", new Client(), 78.9);
        InternetContract ic1 = new InternetContract(2, "22.08.2000", "24.09.2020", new Client(), 78.9);
        InternetContract ic2 = new InternetContract(3, "08.10.2007", "24.09.2020", new Client(), 88.5);
        Repository<InternetContract> repository = new Repository<>();
        repository.add(ic);
        repository.add(ic1);
        repository.add(ic2);

        assertEquals(repository.get(0).get(), ic);
        assertEquals(repository.get(1).get(), ic1);
        assertEquals(repository.get(2).get(), ic2);
        assertEquals(repository.get(3), Optional.empty());

    }

    @Test
    void delete() throws ParseException {
        Repository<BaseContract> repository = new Repository<>();
        CellularContract contract = new CellularContract(1, "01.02.2020", "03.02.2020", new Client(), MobileTariff.SMART);
        TVContract tvContract = new TVContract(2, "02.03.2019", "02.03.2020", new Client(), ChannelPackage.BASE);
        repository.add(contract);
        repository.add(tvContract);
        repository.delete(1);

        assertFalse(repository.getById(1).isPresent());
        assertEquals(repository.size(), 1);

    }

    @Test
    void searchBy() {
        InternetContract ic = new InternetContract(1, "22.08.2000", "14.09.2025", new Client(), 78.9);
        InternetContract ic1 = new InternetContract(2, "22.08.2000", "24.09.2020", new Client(), 78.9);
        InternetContract ic2 = new InternetContract(3, "08.10.2007", "24.09.2020", new Client(), 88.5);
        Repository<InternetContract> repository = new Repository<>();
        repository.add(ic);
        repository.add(ic1);
        repository.add(ic2);
        IRepository rep = repository.searchBy(SearchCriteria.bySpeed(78.9));

        assertEquals(rep.size(), 2);
        assertTrue(rep.getById(1).isPresent());
        assertTrue(rep.getById(2).isPresent());

        IRepository rep2 = repository.searchBy(SearchCriteria.byExpirationDate(LocalDate.parse("24.09.2020", DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        assertEquals(rep2.size(), 2);
        assertTrue(rep2.getById(2).isPresent());
        assertTrue(rep2.getById(3).isPresent());

        IRepository rep3 = repository.searchBy(SearchCriteria.byStartDate(LocalDate.parse("22.08.2000", DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
        assertEquals(rep3.size(), 2);
        assertTrue(rep3.getById(1).isPresent());
        assertTrue(rep3.getById(2).isPresent());
    }

    @Test
    void sortBy() {
        InternetContract ic = new InternetContract(3, "22.08.2003", "14.09.2025", new Client(), 73.9);
        InternetContract ic1 = new InternetContract(1, "22.08.2001", "24.09.2020", new Client(), 71.9);
        InternetContract ic2 = new InternetContract(2, "08.10.2002", "24.09.2020", new Client(), 72.5);
        InternetContract ic3 = new InternetContract(5, "08.10.2005", "24.09.2020", new Client(), 75.5);
        InternetContract ic4 = new InternetContract(4, "08.10.2004", "24.09.2020", new Client(), 74.5);
        Repository<InternetContract> repository = new Repository<>();
        repository.add(ic);
        repository.add(ic1);
        repository.add(ic2);
        repository.add(ic3);
        repository.add(ic4);

        repository.sortBy(ComparsionCriteria.sortById());
        assertEquals(repository.get(0).get(), ic1);
        assertEquals(repository.get(1).get(), ic2);
        assertEquals(repository.get(2).get(), ic);
        assertEquals(repository.get(3).get(), ic4);
        assertEquals(repository.get(4).get(), ic3);

        repository.sortBy(ComparsionCriteria.sortByStartDate());
        assertEquals(repository.get(0).get(), ic1);
        assertEquals(repository.get(1).get(), ic2);
        assertEquals(repository.get(2).get(), ic);
        assertEquals(repository.get(3).get(), ic4);
        assertEquals(repository.get(4).get(), ic3);

        repository.sortBy(ComparsionCriteria.sortBySpeed());
        assertEquals(repository.get(0).get(), ic1);
        assertEquals(repository.get(1).get(), ic2);
        assertEquals(repository.get(2).get(), ic);
        assertEquals(repository.get(3).get(), ic4);
        assertEquals(repository.get(4).get(), ic3);

    }
}