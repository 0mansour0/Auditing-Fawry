package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamModel;
import com.example.auditing.repositories.base.BaseRepository;

import java.util.List;

public interface ParamRepository extends BaseRepository<ParamModel,Long> {
    public List<ParamModel> getParamOfType(String type);
}
