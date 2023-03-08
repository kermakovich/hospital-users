package solvd.laba.ermakovich.hu.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.UserInfo;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor toEntity(DoctorDto doctorDto);

    UserInfo toUserEntity(DoctorDto doctorDto);

    @Mapping(target = "password", ignore = true)
    DoctorDto toDto(Doctor doctor);

}
