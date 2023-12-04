package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppContext {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final EntityManager ENTITY_MANAGER;


    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
        ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
    }


    public static void init() {
        System.out.println("initialize DateBase");
    }
}
