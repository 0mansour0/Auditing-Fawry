package com.example.auditing.repositories.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.repositories.base.BaseRepository;

import java.util.List;
import java.util.Map;

public interface ActionJpaRepository extends BaseRepository<ActionModel,Long> {
    ActionModel getActionById(Long id);
    List<ActionModel> getActionsBySearch(Map<String,String> searchCriteria);
}
