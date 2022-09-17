package com.example.auditing.repositories.param;

import com.example.auditing.models.param.ParamModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ParamRepositoryImpl extends BaseRepositoryImpl<ParamModel,Long> implements ParamRepository {

    public ParamRepositoryImpl(EntityManager em) {
        super(ParamModel.class, em);
    }

    @Override
    public List<String> getParamOfType(String type) {
        return queryFactory
                .selectFrom(qParam)
                .where(qParam.param_type.paramTypeCode.eq(type))
                .fetch()
                .stream()
                .map(ParamModel::getValue)
                .distinct()
                .collect(Collectors.toList());
    }
}
