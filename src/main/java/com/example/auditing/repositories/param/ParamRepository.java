package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamModel;
import com.example.auditing.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParamRepository extends BaseRepository<ParamModel,Long> {
    List<String> getParamOfType(String type);
}
