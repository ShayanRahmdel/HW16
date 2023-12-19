package service;

import base.service.BaseService;
import entity.PayInstallment;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentService extends BaseService<PayInstallment,Integer> {



    public List<LocalDate> tuition9 (Student student);

    public List<Double> calculateTuition9();
}


