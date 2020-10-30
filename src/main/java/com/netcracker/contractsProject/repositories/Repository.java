package com.netcracker.contractsProject.repositories;

import com.netcracker.contractsProject.сontracts.BaseContract;

import java.util.Optional;

/**
 * The class represents a repository for storing any classes that extends the BaseContract
 *
 * @param <T> class type that extends BaseContract
 */
public class Repository<T extends BaseContract> implements IRepository<T> {
    private final int startSize = 10;
    private T[] elements;
    private int lastElemIndex;

    public Repository() {
        elements = (T[]) new Object[startSize];
        lastElemIndex = 0;
    }

    /**
     * adding a contract to the repository
     *
     * @param contract any class that extends the BaseContract
     */
    @Override
    public void add(T contract) {
        if (lastElemIndex == elements.length - 1) {
            increaseSize();
        }
        elements[lastElemIndex++] = contract;
    }

    /**
     * get a contract from the repository by contract id
     *
     * @param id contract id stored in the repository
     * @return when the required contract is found,
     * it returns Optional with the value of the found contract,
     * else returns empty Optional
     */
    @Override
    public Optional<T> get(int id) {
        for (T el : elements) {
            if (el.getId() == id) {
                return Optional.of(el);
            }
        }
        return Optional.empty();
    }

    /**
     * removing an element from repository by its ordinal number in it
     *
     * @param index ordinal in the repository
     */
    @Override
    public void delete(int index) {
        for (int i = index; i < elements.length - 1; i++) {
            elements[i] = elements[i + 1];
        }
    }

    /**
     * increases the size of the {@link #elements}
     */
    private void increaseSize() {
        T[] largerArr = (T[]) new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            largerArr[i] = elements[i];
        }
        elements = largerArr;
    }

}
