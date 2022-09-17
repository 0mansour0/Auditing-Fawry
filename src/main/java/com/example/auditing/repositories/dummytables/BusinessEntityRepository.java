package com.example.auditing.repositories.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;
import com.example.auditing.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessEntityRepository extends BaseRepository<BusinessEntityModel,Long> {
    BusinessEntityModel findBeByName(String name);
}
