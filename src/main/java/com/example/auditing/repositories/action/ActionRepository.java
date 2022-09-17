package com.example.auditing.repositories.action;

import com.example.auditing.models.action.ActionModel;
import com.example.auditing.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ActionRepository extends BaseRepository<ActionModel,Long> {
    List<ActionModel> findActionsBySearch(Map<String,String> searchCriteria);
}
