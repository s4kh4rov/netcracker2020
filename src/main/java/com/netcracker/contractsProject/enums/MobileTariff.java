package com.netcracker.contractsProject.enums;

public enum MobileTariff {
    ULTRA(3500, 1000, 50),
    SMART(300, 500, 5),
    X(100, 300, 7);

    private int numberOfMinutes;
    private int numberOfSms;
    private int amountOfInternet;

    MobileTariff(int numberOfMinutes, int numberOfSms, int amountOfInternet) {
        this.numberOfMinutes = numberOfMinutes;
        this.numberOfSms = numberOfSms;
        this.amountOfInternet = amountOfInternet;
    }

    public int getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public int getNumberOfSms() {
        return numberOfSms;
    }

    public int getAmountOfInternet() {
        return amountOfInternet;
    }

    public String getInfoAboutTariff(MobileTariff tariff) {
        return "Тариф: " + tariff.name() +
                "Количество минут:" + tariff.numberOfMinutes +
                "Количество интернета: " + tariff.amountOfInternet + " гб " +
                "Количество смс: " + tariff.numberOfSms;

    }
}
