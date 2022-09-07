package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.repositories.base.BaseRepository;

public interface BusinessEntityRepository extends BaseRepository<BusinessEntityModel,Long> {
    BusinessEntityModel findByBeName(String name);
}
