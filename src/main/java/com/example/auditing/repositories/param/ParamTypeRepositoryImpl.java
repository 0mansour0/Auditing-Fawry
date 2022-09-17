package com.example.auditing.repositories.param;

import com.example.auditing.exception.ResourceNotFoundException;
import com.example.auditing.models.param.ParamTypeModel;
import com.example.auditing.repositories.base.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ParamTypeRepositoryImpl extends BaseRepositoryImpl<ParamTypeModel,Long> implements ParamTypeRepository {

    public ParamTypeRepositoryImpl(EntityManager em) {
        super(ParamTypeModel.class, em);
    }

    @Override
    public ParamTypeModel getParamType(String code) {
        ParamTypeModel paramTypeModel = queryFactory
                                            .select(qParamType)
                                            .from(qParamType)
                                            .where(qParamType.paramTypeCode.eq(code))
                                            .fetchFirst();

        if (paramTypeModel == null) {
            throw new ResourceNotFoundException("Param Type not found");
        }
        return paramTypeModel;
    }

    @Override
    public List<String> getAllParams() {
        return queryFactory
                .selectFrom(qParamType)
                .fetch()
                .stream()
                .map(ParamTypeModel::getParamTypeCode)
                .distinct()
                .collect(Collectors.toList());
    }

}
