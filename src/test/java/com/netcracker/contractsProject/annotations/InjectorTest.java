package com.netcracker.contractsProject.annotations;

import com.netcracker.contractsProject.parser.CsvContractReader;
import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.repositories.sort.ISorter;
import com.netcracker.contractsProject.validators.DateOfBirthValidator;
import com.netcracker.contractsProject.validators.DateValidator;
import com.netcracker.contractsProject.validators.IValidator;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InjectorTest {

    @Test
    void inject() throws IllegalAccessException, IOException, InstantiationException, CsvValidationException{
        CsvContractReader csvContractReader = new CsvContractReader("C:\\Users\\User\\contracts.csv");
        Injector.inject(csvContractReader);
        Repository<BaseContract> repository = (Repository<BaseContract>) csvContractReader.createRepository();
        Injector.inject(repository);
        assertTrue(repository.size() == 6);

    }
}