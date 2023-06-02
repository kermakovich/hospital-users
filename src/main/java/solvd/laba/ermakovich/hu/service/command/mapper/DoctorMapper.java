package solvd.laba.ermakovich.hu.service.command.mapper;

import org.mapstruct.Mapper;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    ElasticDoctor toElastic(Doctor doctor);

}
