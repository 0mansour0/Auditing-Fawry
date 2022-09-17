package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParamTypeRepository extends BaseRepository<ParamTypeModel,Long> {
    ParamTypeModel getParamType(String code);
    List<String> getAllParams();
}
