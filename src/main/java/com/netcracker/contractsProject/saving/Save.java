package com.netcracker.contractsProject.saving;

import com.netcracker.contractsProject.repositories.IRepository;
import com.netcracker.contractsProject.repositories.Repository;
import com.netcracker.contractsProject.сontracts.BaseContract;

public interface Save {
    void save(IRepository repository);
}
