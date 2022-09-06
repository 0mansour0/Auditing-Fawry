package com.example.auditing.repositories.action;

import com.example.auditing.models.action.ActionTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionTypeJpaRepository extends JpaRepository<ActionTypeModel,Long> {
    ActionTypeModel findByActionTypeCode(String code);
}
