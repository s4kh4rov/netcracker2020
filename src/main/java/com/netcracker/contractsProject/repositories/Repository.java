package com.netcracker.contractsProject.repositories;

import com.netcracker.contractsProject.annotations.MyInject;
import com.netcracker.contractsProject.repositories.sort.BubbleSorter;
import com.netcracker.contractsProject.repositories.sort.ISorter;
import com.netcracker.contractsProject.repositories.sort.IncertionSorter;
import com.netcracker.contractsProject.сontracts.BaseContract;

import javax.xml.bind.annotation.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * The class represents a repository for storing any classes that extends the BaseContract
 *
 * @param <T> class type that extends BaseContract
 */
@XmlRootElement(name = "Repository")
@XmlAccessorType(XmlAccessType.FIELD)
public class Repository<T extends BaseContract> implements IRepository<T> {
    @MyInject(clazz = ISorter.class)
    @XmlElements({
            @XmlElement(type = BubbleSorter.class),
            @XmlElement(type = IncertionSorter.class)
    })
    private ISorter<T> sorter;
    @XmlElement(name = "startSize")
    private final int startSize = 10;
    @XmlElementWrapper(name = "contracts")
    @XmlElement(name = "contract")
    private BaseContract[] elements;
    @XmlElement(name = "lastElemIndex")
    private int lastElemIndex;

    public Repository() {
        elements = new BaseContract[startSize];
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
    public Optional<T> getById(int id) {
        for (BaseContract el : elements) {
            if (el != null && el.getId() == id) {
                return Optional.of((T) el);
            }
        }
        return Optional.empty();
    }

    /**
     * receiving a contract from the repository by its serial number in it
     *
     * @param index serial number in the repository
     * @return if the index is greater than the size of the array return empty optional else
     * optional with found contract
     */
    @Override
    public Optional<T> get(int index) {
        return index >= lastElemIndex ? Optional.empty() : Optional.of((T) elements[index]);
    }

    /**
     * removing an element from repository by contract id
     *
     * @param id contract id in the repository
     */
    @Override
    public void delete(int id) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].getId() == id) {
                for (int j = i; j < elements.length - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                lastElemIndex--;
            }

        }

    }

    /**
     * search in the repository by a specified  criterion
     *
     * @param condition repository search condition
     * @return new repository with found contracts
     */
    @Override
    public Repository<T> searchBy(Predicate<T> condition) {
        Repository<T> repository = new Repository<>();
        for (BaseContract elem : elements) {
            if (elem != null && condition.test((T) elem)) {
                repository.add((T) elem);
            }

        }
        return repository;
    }

    @Override
    public void sortBy(Comparator<T> comporator) {
        sorter.sort(elements, comporator);
    }

    /**
     * increases the size of the {@link #elements}
     */
    private void increaseSize() {
        BaseContract[] largerArr = new BaseContract[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            largerArr[i] = elements[i];
        }
        elements = largerArr;
    }

    /**
     * @return repository size
     */
    public int size() {
        return lastElemIndex;
    }

}
