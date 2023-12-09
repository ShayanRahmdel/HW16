package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.CreditCard;
import repository.CreditCardRepository;

import javax.persistence.EntityManager;

public class CreditCardRepositryImpl extends BaseEntityRepositoryImpl<CreditCard,Integer> implements CreditCardRepository {
    public CreditCardRepositryImpl(EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }
}
