package com.netcracker.contractsProject.repositories;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.Comparator;

/**
 * The interface represents a generic repository
 */
public interface IRepository<T> {
    public void add(T contract);

    public Optional<T> getById(int id);

    public Optional<T> get(int index);

    public void delete(int id);

    public int size();

    public IRepository<T> searchBy(Predicate<T> condition);

    public void sortBy(Comparator<T> comporator);
}
