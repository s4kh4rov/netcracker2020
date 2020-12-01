package com.netcracker.contractsProject.parser;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.Gender;
import com.netcracker.contractsProject.repositories.IRepository;
import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.validators.*;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Сlass for creating contracts based on csv file data
 */
public class CsvContractReader<T extends BaseContract> {

    CSVReader reader;
    private static final Logger log = Logger.getLogger(CsvContractReader.class);
    private static List<IValidator> validators = new ArrayList<>();
    static {
        validators.add(new DateOfBirthValidator());
        validators.add(new DateValidator());
        validators.add(new PassportDataValidator());
    }

    /**
     * Constructor with parameter
     *
     * @param pathToCsvFile path to csv file
     * @throws FileNotFoundException
     */
    public CsvContractReader(String pathToCsvFile) throws FileNotFoundException {
        reader = new CSVReader(new FileReader(pathToCsvFile));
    }

    /**
     * creates a repository with contracts based on data from a csv file
     *
     * @return new repository with contracts
     * @throws IOException
     * @throws CsvValidationException
     */
    public IRepository<T> createRepository() throws IOException, CsvValidationException {
        IRepository<T> repository = new Repository<>();
        String[] line;
        Object[] data;
        List<Client> clients = new ArrayList<>();
        reader.skip(1);
        log.info("Начало парсинга");
        while ((line = reader.readNext()) != null) {
            data = new Object[line.length - 8];
            Client client = createClient(Arrays.copyOfRange(line, 1, 9));
            int index = clients.indexOf(client);
            if (index != -1) {
                client = clients.get(index);
            } else {
                clients.add(client);
            }
            data[0] = client;
            System.arraycopy(line, 9, data, 1, data.length - 1);
            T contract = null;
            switch (line[0].toLowerCase().trim()) {
                case "internetcontract":
                    contract = createContract((Function<Object[], T>) Functions.toInternetContract(), data);
                    break;
                case "tvcontract":
                    contract = createContract((Function<Object[], T>) Functions.toTvContract(), data);
                    break;
                case "cellularcontract":
                    contract = createContract((Function<Object[], T>) Functions.toCellularContract(), data);
                    break;
            }
            List<ValidationResult> results = validation(contract);
            Set<CheckStatus> status = results.stream().map(r -> r.getStatus()).collect(Collectors.toSet());
            if (!status.contains(CheckStatus.ERROR)) {
                repository.add(contract);
            }
            log.info("Контракт с id " + contract.getId());
            results.forEach(r -> log.info(r.getFieldName() + " " + r.getStatus() + " " + r.getMessage()));
        }
        reader.close();
        return repository;
    }

    /**
     * method for creating a contract of type T
     *
     * @param function a function that creates a contract of type T from an array of objects
     * @param data     array with data necessary to create a contract
     * @return new instance of class contract
     */
    public T createContract(Function<Object[], T> function, Object[] data) {
        return function.apply(data);
    }

    /**
     * method for creating a client
     *
     * @param args an array of strings containing the data needed to fill the fields in the client class
     * @return a new instance of the client class
     */
    private Client createClient(String[] args) {
        int id = Integer.parseInt(args[0]);
        String name = args[1];
        String surname = args[2];
        String patronymic = args[3];
        String dateOfBirth = args[4];
        Gender gender = Gender.valueOf(args[5].toUpperCase());
        int passportID = Integer.parseInt(args[6]);
        int passportSeries = Integer.parseInt(args[7]);
        return new Client(id, name, surname, patronymic, dateOfBirth, gender, passportSeries, passportID);
    }

    /**
     * checks the contract for compliance with all requirements
     * @param contract contract for verification
     * @return a list of objects that contain the results of validation
     */
    private List<ValidationResult> validation(T contract) {
        return validators.stream().map(v->v.validate(contract)).collect(Collectors.toList());
    }


}
