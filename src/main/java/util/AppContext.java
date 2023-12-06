package util;

import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppContext {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final EntityManager ENTITY_MANAGER;
    private static final StudentRepository STUDENT_REPOSITORY;

    private static final StudentService STUDENT_SERVICE;


    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
        ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
        STUDENT_REPOSITORY = new StudentRepositoryImpl(ENTITY_MANAGER);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY);
    }


    public static void init() {
        System.out.println("initialize DateBase");
    }

    public static StudentService getStudentService(){
        return STUDENT_SERVICE;
    }
}
