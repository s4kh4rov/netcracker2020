package com.netcracker.contractsProject.saving;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.enums.Gender;
import com.netcracker.contractsProject.enums.MobileTariff;
import com.netcracker.contractsProject.repositories.IRepository;
import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.InternetContract;
import com.netcracker.contractsProject.сontracts.TVContract;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryXMLTest {

    @Test
    void save() throws JAXBException {
        Client client = new Client(1, "Alexey", "Bondariev", "Alexeevich", "2000-03-21", Gender.MALE, 2345, 546789);
        InternetContract internetContract = new InternetContract(1, "2021-09-12", "2021-09-23", client, 45.8);
        TVContract tvContract = new TVContract(2, "2021-09-23", "2021-10-23", client, ChannelPackage.BASE);
        CellularContract cellularContract = new CellularContract(3, "2021-09-23", "2021-10-23", client, MobileTariff.ULTRA);
        IRepository repository = new Repository();
        repository.add(internetContract);
        repository.add(tvContract);
        repository.add(cellularContract);

        RepositoryXML repositoryXML = new RepositoryXML();
        repositoryXML.save(repository);
        IRepository restoreRepository = repositoryXML.restore();
        assertEquals(repository.size(), restoreRepository.size());
    }
}