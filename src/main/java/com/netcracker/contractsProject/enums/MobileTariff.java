package com.netcracker.contractsProject.enums;

/**
 * The enumeration represents the mobile tariffs
 */
public enum MobileTariff {

    ULTRA(3500, 1000, 50),
    SMART(300, 500, 5),
    X(100, 300, 7);

    /**
     * number of minutes in the tariff
     */
    private int numberOfMinutes;

    /**
     * number of sms in the tariff
     */
    private int numberOfSms;

    /**
     * Amount of internet in gigabytes
     */
    private int amountOfInternet;

    /**
     * Mobile tariff constructor with all fields as parameters
     *
     * @param numberOfMinutes  number of minutes
     * @param numberOfSms      number of sms
     * @param amountOfInternet amount of internet
     */
    MobileTariff(int numberOfMinutes, int numberOfSms, int amountOfInternet) {
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.amountOfInternet = amountOfInternet;
    }

    /**
     * gets the number of minutes
     *
     * @return {@link #numberOfMinutes}
     */
    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    /**
     * gets the number of sms
     *
     * @return {@link #numberOfSms}
     */
    public int getNumberOfSms() {
        return numberOfSms;
    }

    /**
     * gets the amount of internet
     *
     * @return {@link #amountOfInternet}
     */
    public int getAmountOfInternet() {
        return amountOfInternet;
    }

    /**
     * @return string with a full description of the tariff
     */
    @Override
    public String toString() {
        return "Тариф " + name() +
                " Количество минут: " + numberOfMinutes +
                " Количество интернета: " + amountOfInternet + "гб" +
                " Количество смс: " + numberOfSms;
    }
}
