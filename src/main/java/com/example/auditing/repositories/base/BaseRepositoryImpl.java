package com.example.auditing.repositories.base;

import com.example.auditing.exception.DbResultNotFoundException;
import com.example.auditing.models.action.QActionModel;
import com.example.auditing.models.action.QActionTypeModel;
import com.example.auditing.models.dummytables.QApplicationModel;
import com.example.auditing.models.dummytables.QBusinessEntityModel;
import com.example.auditing.models.dummytables.QUserModel;
import com.example.auditing.models.param.QParamModel;
import com.example.auditing.models.param.QParamTypeModel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public abstract class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    protected final QUserModel qUser = QUserModel.userModel;
    protected final QApplicationModel qApplication = QApplicationModel.applicationModel;
    protected final QBusinessEntityModel qBusinessEntity = QBusinessEntityModel.businessEntityModel;
    protected final QActionModel qAction = QActionModel.actionModel;
    protected final QActionTypeModel qActionType = QActionTypeModel.actionTypeModel;
    protected final QParamModel qParam = QParamModel.paramModel;
    protected final QParamTypeModel qParamType = QParamTypeModel.paramTypeModel;

    private final EntityManager em;
    protected final JPAQueryFactory queryFactory;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public T findByIdMandatory(ID id) throws DbResultNotFoundException {
        return findById(id)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Entity [%s] with id [%s] was not found in DB", getDomainClass().getSimpleName(), id);
                    return new DbResultNotFoundException(errorMessage);
                });
    }

    public void clear() {
        em.clear();
    }

    public void detach(T entity) {
        em.detach(entity);
    }
}