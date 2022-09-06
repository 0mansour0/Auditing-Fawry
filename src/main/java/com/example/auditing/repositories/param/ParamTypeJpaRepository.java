package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.base.BaseRepository;

public interface ParamTypeJpaRepository extends BaseRepository<ParamTypeModel,Long> {
    ParamTypeModel getParamType(String code);
}
