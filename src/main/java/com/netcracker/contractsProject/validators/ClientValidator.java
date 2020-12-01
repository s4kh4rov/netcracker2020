package com.netcracker.contractsProject.validators;

import com.netcracker.contractsProject.clients.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Client contract validator class
 */
public class ClientValidator {
    /**
     * Checks the client according to all criteria
     * @param c client for verification
     * @return list with validation results
     */
    public static List<ValidationResult> checkClient(Client c) {
        List<ValidationResult> results = new ArrayList<>();
        results.add(checkDateOfBirth(c));
        results.add(checkPassportData(c));
        return results;
    }
    /**
     * Сhecks dateOfBith against criteria
     * @param client client for verification
     * @return  object with the result of validation
     */
    public static ValidationResult checkDateOfBirth(Client client) {
        ValidationResult result = new ValidationResult();
        if (client.getDateOfBirth().isBefore(LocalDate.now().minusYears(100))) {
            result.setStatus(CheckStatus.WARNING);
            result.setMessage("Возраст клиента превышает 99 лет");
            result.setFieldName("dateOfBirth");
        } else {
            result.setStatus(CheckStatus.OK);
            result.setMessage("корректные данные");
            result.setFieldName("dateOfBirth");
        }
        return result;
    }
    /**
     * Сhecks passport data against criteria
     * @param client client whose passport details will be verified
     * @return  object with the result of validation
     */
    public static ValidationResult checkPassportData(Client client) {
        ValidationResult result = new ValidationResult();
        if (Integer.toString(client.getPassportID()).length() != 4 ||
                Integer.toString(client.getPassportSeries()).length() != 6 ||
                client.getPassportID() < 0 || client.getPassportSeries() < 0) {
            result.setStatus(CheckStatus.ERROR);
            result.setMessage("Некорректные паспортные данные");
            result.setFieldName("passportData");
        } else {
            result.setStatus(CheckStatus.OK);
            result.setMessage("корректные данные");
            result.setFieldName("passportData");
        }
        return result;
    }
}
