package com.netcracker.contractsProject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation stores the names of the packages where you will need to search for classes
 * whose instances will be injected into the fields marked with the annotation
 * @see com.netcracker.contractsProject.annotations.MyInject
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Configuration {
    String[] packages();
}
