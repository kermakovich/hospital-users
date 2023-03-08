package solvd.laba.ermakovich.hu.web.mapper;

import org.mapstruct.Mapper;
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

    DoctorDto toDto(Doctor doctor);

    default Mono<DoctorDto> toDto(Mono<Doctor> doctor) {
        return doctor.map(this::toDto);
    }

}
