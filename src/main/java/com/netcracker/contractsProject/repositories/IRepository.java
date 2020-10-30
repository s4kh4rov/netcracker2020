package com.netcracker.contractsProject.repositories;

import java.util.Optional;

/**
 * The interface represents a generic repository
 */
public interface IRepository<T> {
    public void add(T contract);

    public Optional<T> get(int id);

    public void delete(int index);
}
