package com.netcracker.contractsProject.saving.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * The adapter class contains the logic for saving and restoring date in xml format
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    /**
     * Convert a value type to a bound type
     *
     * @param v the value to be converted
     * @throws Exception- if there's an error during the conversion
     */
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v the value to be convereted
     * @throws Exception- if there's an error during the conversion
     */
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
