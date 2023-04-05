package solvd.laba.ermakovich.hu.web.mapper;

import org.mapstruct.Mapper;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor toEntity(DoctorDto doctorDto);

}
