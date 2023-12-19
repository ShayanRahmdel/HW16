package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Grade;
import entity.PayInstallment;
import entity.Student;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class InstallmentServiceImpl extends BaseEntityServiceImpl<PayInstallment,Integer,InstallmentRepository> implements InstallmentService {

    public InstallmentServiceImpl(InstallmentRepository repository) {
        super(repository);
    }



    public  List<LocalDate> tuition9 (Student student){
        int year = 0;
        if (student.getGrade().equals(Grade.Associate)){
             year = student.getYearOfEntry().getYear()+2;
        }else if (student.getGrade().equals(Grade.Bachelor_Continuous)||student.getGrade().equals(Grade.Bachelor_Discontinuous)){
             year = student.getYearOfEntry().getYear()+4;
        }
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = LocalDate.of(year,3,21);
        LocalDate endDate = currentDate.plusMonths(18);
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plus(1, ChronoUnit.MONTHS);
        }
        return dates;
    }

    public  List<Double> calculateTuition9() {
        Double installment = 52000.0;
        List<Double> installmentList = new ArrayList<>();
        for (int i = 1; i <= 19; i++) {
            installmentList.add(installment);
            if (i == 12) {
                installment = installment * 2;
            }
        }
        return installmentList;
    }
}
