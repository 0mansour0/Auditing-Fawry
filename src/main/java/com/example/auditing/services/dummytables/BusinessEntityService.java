package com.example.auditing.services.dummytables;

import com.example.auditing.models.dummytables.BusinessEntityModel;

public interface BusinessEntityService {
    BusinessEntityModel findBeByName(String name);
}
