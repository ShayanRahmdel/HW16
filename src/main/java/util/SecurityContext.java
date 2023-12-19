package util;

import entity.Student;
import lombok.Getter;

import java.time.LocalDate;

public class SecurityContext {
    @Getter
    private static Student student;

    public static void fillContext(Student baseStudent){
        student = baseStudent;

    }


    public static Integer getStudentId() {
        return student.getId();
    }

    @Getter
    private static LocalDate todayDate;

    public static void fillContext(LocalDate today){
        todayDate = today;
    }

}
