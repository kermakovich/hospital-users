package solvd.laba.ermakovich.hu.web.mapper;

import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import solvd.laba.ermakovich.hu.domain.Doctor;
import solvd.laba.ermakovich.hu.domain.ElasticDoctor;
import solvd.laba.ermakovich.hu.domain.aggregate.doctor.DoctorAggregate;
import solvd.laba.ermakovich.hu.web.dto.DoctorAggregateDto;
import solvd.laba.ermakovich.hu.web.dto.DoctorDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor toEntity(DoctorDto doctorDto);

    ElasticDoctor toElastic(Doctor doctor);

    default Flux<DoctorAggregateDto> fluxToDto(Flux<DoctorAggregate> flux) {
        return flux.map(this::toDto);
    }

    DoctorAggregateDto toDto(DoctorAggregate doctorAggregate);

}
