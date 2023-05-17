package solvd.laba.ermakovich.hu.web.mapper;

import org.mapstruct.Mapper;
import solvd.laba.ermakovich.hu.domain.DoctorSearchCriteria;
import solvd.laba.ermakovich.hu.web.dto.DoctorSearchCriteriaDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface DoctorSearchCriteriaMapper {

    DoctorSearchCriteria toEntity(DoctorSearchCriteriaDto dto);

}
