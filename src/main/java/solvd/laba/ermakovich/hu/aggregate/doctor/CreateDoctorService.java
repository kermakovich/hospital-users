package solvd.laba.ermakovich.hu.aggregate.doctor;

import solvd.laba.ermakovich.hu.event.CreateDoctor;

/**
 * @author Ermakovich Kseniya
 */
public interface CreateDoctorService {

    void on(DoctorAggregate aggregate, CreateDoctor event);

}
