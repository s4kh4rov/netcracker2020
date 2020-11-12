package com.netcracker.contractsProject.parser;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.Gender;
import com.netcracker.contractsProject.repositories.IRepository;
import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * Сlass for creating contracts based on csv file data
 */
public class CsvContractReader<T extends BaseContract> {

    CSVReader reader;

    /**
     * Constructor with parameter
     * @param pathToCsvFile path to csv file
     * @throws FileNotFoundException
     */
    public CsvContractReader(String pathToCsvFile) throws FileNotFoundException {
        reader = new CSVReader(new FileReader(pathToCsvFile));
    }

    /**
     * creates a repository with contracts based on data from a csv file
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
            switch (line[0].toLowerCase().trim()) {
                case "internetcontract":
                    repository.add(createContract((Function<Object[], T>) Functions.toInternetContract(), data));
                    break;
                case "tvcontract":
                    repository.add(createContract((Function<Object[], T>) Functions.toTvContract(), data));
                    break;
                case "cellularcontract":
                    repository.add(createContract((Function<Object[], T>) Functions.toCellularContract(), data));
                    break;
            }
        }
        reader.close();
        return repository;
    }

    /**
     * method for creating a contract of type T
     * @param function a function that creates a contract of type T from an array of objects
     * @param data array with data necessary to create a contract
     * @return new instance of class contract
     */
    public T createContract(Function<Object[], T> function, Object[] data) {
        return function.apply(data);
    }

    /**
     * method for creating a client
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
        int passportSeries = Integer.parseInt(args[6]);
        int passportID = Integer.parseInt(args[7]);
        return new Client(id, name, surname, patronymic, dateOfBirth, gender, passportSeries, passportID);
    }


}
