package util;

import entity.CreditCard;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppContext {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final EntityManager ENTITY_MANAGER;
    private static final StudentRepository STUDENT_REPOSITORY;
    private static final StudentSpouseRepository STUDENT_SPOUSE_REPOSITORY;
    private static final CreditCardRepository CREDIT_CARD_REPOSITORY;
    private static final LoanRepository LOAN_REPOSITORY;
    private static final LoanCategoryRepository LOAN_CATEGORY_REPOSITORY;
    private static final InstallmentRepository INSTALLMENT_REPOSITORY;


    private static final StudentService STUDENT_SERVICE;
    private static final StudentSpouseService STUDENT_SPOUSE_SERVICE;
    private static final CreditCardService CREDIT_CARD_SERVICE;
    private static final LoanService LOAN_SERVICE;
    private static final LoanCategoryService LOAN_CATEGORY_SERVICE;
    private static final InstallmentService INSTALLMENT_SERVICE;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
        ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
        STUDENT_REPOSITORY = new StudentRepositoryImpl(ENTITY_MANAGER);
        STUDENT_SPOUSE_REPOSITORY = new StudentSpouseRepositoryImpl(ENTITY_MANAGER);
        CREDIT_CARD_REPOSITORY = new CreditCardRepositryImpl(ENTITY_MANAGER);
        LOAN_REPOSITORY = new LoanRepositoryImpl(ENTITY_MANAGER);
        LOAN_CATEGORY_REPOSITORY = new LoanCategoryRepositoryImpl(ENTITY_MANAGER);
        INSTALLMENT_REPOSITORY = new InstallmentRepositoryImpl(ENTITY_MANAGER);

        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY);
        STUDENT_SPOUSE_SERVICE = new StudentSpouseServiceImpl(STUDENT_SPOUSE_REPOSITORY);
        CREDIT_CARD_SERVICE = new CreditCardServiceImpl(CREDIT_CARD_REPOSITORY);
        LOAN_SERVICE = new LoanServiceImpl(LOAN_REPOSITORY);
        LOAN_CATEGORY_SERVICE = new LoanCategoryServiceImpl(LOAN_CATEGORY_REPOSITORY);
        INSTALLMENT_SERVICE = new InstallmentServiceImpl(INSTALLMENT_REPOSITORY);
    }


    public static void init() {
        System.out.println("initialize DateBase");
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static CreditCardService getCreditCardService(){
        return CREDIT_CARD_SERVICE;
    }
    public static LoanService getLoanService(){
        return LOAN_SERVICE;
    }

    public static InstallmentService getInstallmentService(){
        return INSTALLMENT_SERVICE;
    }
    public static StudentSpouseService getStudentSpouseService(){
        return STUDENT_SPOUSE_SERVICE;
    }
}
