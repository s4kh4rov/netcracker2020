package com.netcracker.contractsProject.saving;

import com.netcracker.contractsProject.clients.Client;
import com.netcracker.contractsProject.enums.ChannelPackage;
import com.netcracker.contractsProject.enums.Gender;
import com.netcracker.contractsProject.enums.MobileTariff;
import com.netcracker.contractsProject.repositories.IRepository;
import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.сontracts.BaseContract;
import com.netcracker.contractsProject.сontracts.CellularContract;
import com.netcracker.contractsProject.сontracts.InternetContract;
import com.netcracker.contractsProject.сontracts.TVContract;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static java.sql.Types.NULL;

public class RepositoryDB implements Save, Restore {
    private static final String INSERT_CONTRACT = "INSERT INTO CONTRACT (contract_id,start_date,expiration_date,tariff,max_speed,channel_package,client_id) VALUES(?,?,?,?,?,?,?) ON CONFLICT(contract_id) DO UPDATE SET start_date=excluded.start_date,expiration_date=excluded.expiration_date,tariff=excluded.tariff,max_speed=excluded.max_speed,channel_package=excluded.channel_package,client_id=excluded.client_id";
    private static final String INSERT_CLIENT = "INSERT INTO CLIENT (id,client_name,surname,patronymic,birth_date,gender,passport_series,passport_id) VALUES (?,?,?,?,?,?,?,?) ON CONFLICT(id) DO UPDATE SET client_name=excluded.client_name,surname=excluded.surname,patronymic=excluded.patronymic,birth_date=excluded.birth_date,gender=excluded.gender,passport_series=excluded.passport_series,passport_id=excluded.passport_id";
    private static final String SELECT_CONTRACT = "SELECT * FROM CONTRACT JOIN CLIENT ON CONTRACT.client_id = CLIENT.id";
    private String url;
    private String user;
    private String pass;

    public RepositoryDB() {
        try (InputStream dbp = RepositoryDB.class.getClassLoader().getResourceAsStream("DBConnection.properties")) {
            Properties props = new Properties();
            props.load(dbp);
            url = props.getProperty("Database.DataURL");
            user = props.getProperty("Database.Prop.user");
            pass = props.getProperty("Database.Prop.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(IRepository repository) {
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement contractStatement = connection.prepareStatement(INSERT_CONTRACT);
             PreparedStatement clientStatement = connection.prepareStatement(INSERT_CLIENT)) {
            Client client;
            for (int i = 0; i < repository.size(); i++) {
                BaseContract c = (BaseContract) repository.get(i).get();
                client = c.getClient();
                clientStatement.setInt(1, client.getId());
                clientStatement.setString(2, client.getName());
                clientStatement.setString(3, client.getSurname());
                clientStatement.setString(4, client.getPatronymic());
                clientStatement.setDate(5, Date.valueOf(client.getDateOfBirth()));
                clientStatement.setString(6, client.getGender().name());
                clientStatement.setInt(7, client.getPassportSeries());
                clientStatement.setInt(8, client.getPassportID());
                clientStatement.executeUpdate();

                contractStatement.setInt(1, c.getId());
                contractStatement.setDate(2, Date.valueOf(c.getStartDate()));
                contractStatement.setDate(3, Date.valueOf(c.getExpirationDate()));
                contractStatement.setNull(4, NULL);
                contractStatement.setNull(5, NULL);
                contractStatement.setNull(6, NULL);
                contractStatement.setInt(7, client.getId());
                if (c instanceof InternetContract) {
                    contractStatement.setDouble(5, ((InternetContract) c).getMaxSpeed());
                } else if (c instanceof CellularContract) {
                    contractStatement.setString(4, ((CellularContract) c).getTariff().name());
                } else if (c instanceof TVContract) {
                    contractStatement.setString(6, ((TVContract) c).getChPackage().name());
                }
                contractStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public IRepository restore() {
        IRepository repository = new Repository();
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement statement = connection.prepareStatement(SELECT_CONTRACT);
             ResultSet set = statement.executeQuery()) {
            Client client;
            BaseContract contract = new BaseContract();
            while (set.next()) {
                client = new Client(set.getInt("id"),
                        set.getString("client_name"),
                        set.getString("surname"),
                        set.getString("patronymic"),
                        set.getString("birth_date"),
                        Gender.valueOf(set.getString("gender")),
                        set.getInt("passport_series"),
                        set.getInt("passport_id"));
                if (set.getDouble("max_speed") != NULL) {
                    contract = new InternetContract();
                    ((InternetContract) contract).setMaxSpeed(set.getDouble("max_speed"));
                } else if (set.getString("channel_package") != null) {
                    contract = new TVContract();
                    ((TVContract) contract).setChPackage(ChannelPackage.valueOf(set.getString("channel_package")));
                } else if (set.getString("tariff") != null) {
                    contract = new CellularContract();
                    ((CellularContract) contract).setTariff(MobileTariff.valueOf(set.getString("tariff")));
                }
                contract.setId(set.getInt("contract_id"));
                contract.setClient(client);
                contract.setExpirationDate(set.getString("expiration_date"));
                contract.setStartDate(set.getString("start_date"));
                repository.add(contract);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return repository;
    }
}
