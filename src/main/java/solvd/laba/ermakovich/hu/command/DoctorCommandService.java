package solvd.laba.ermakovich.hu.command;

import reactor.core.publisher.Mono;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorCommandService {

    Mono<Void> handle(CreateDoctorCommand command);

}
