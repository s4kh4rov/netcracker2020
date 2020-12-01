package com.netcracker.contractsProject.validators;

/**
 * Class describing the result of the check
 */
public class ValidationResult {
    private CheckStatus status;
    private String message;
    private String fieldName;

    /**
     * default constructor
     */
    public ValidationResult() {
    }

    /**
     * Constructor with parameters
     *
     * @param status    validation status
     * @param fieldName name of the field to be validated
     * @param message   detailed description of the validation status
     */
    public ValidationResult(CheckStatus status, String message, String fieldName) {
        this.status = status;
        this.message = message;
        this.fieldName = fieldName;
    }

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
