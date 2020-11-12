package com.netcracker.contractsProject.parser;

import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CsvContractReaderTest {

    @Test
    void createRepository() throws IOException, CsvValidationException {
        CsvContractReader cr = new CsvContractReader("C:\\Users\\User\\contracts.csv");
        Repository<BaseContract> repository = (Repository<BaseContract>) cr.createRepository();
        assertTrue(repository.size() == 6);
        assertEquals(repository.get(0).get().getClient(), repository.get(1).get().getClient());
        assertEquals(repository.get(1).get().getClient(), repository.get(2).get().getClient());

    }
}
