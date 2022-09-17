package com.example.auditing.repositories.action;

import com.example.auditing.models.action.ActionTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActionTypeRepository extends JpaRepository<ActionTypeModel,Long> {
    ActionTypeModel findActionTypeByCode(String code);
    List<String> getAllActionTypes();
}
