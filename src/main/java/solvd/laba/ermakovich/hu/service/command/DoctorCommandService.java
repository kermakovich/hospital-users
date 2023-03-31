package solvd.laba.ermakovich.hu.service.command;

import reactor.core.publisher.Mono;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorCommandService {

    Mono<String> handle(CreateDoctorCommand command);

}
