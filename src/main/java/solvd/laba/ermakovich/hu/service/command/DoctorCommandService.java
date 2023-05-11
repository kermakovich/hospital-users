package solvd.laba.ermakovich.hu.service.command;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hu.domain.command.CreateDoctorCommand;
import solvd.laba.ermakovich.hu.domain.command.DeleteDoctorCommand;

/**
 * @author Ermakovich Kseniya
 */
public interface DoctorCommandService {

    Mono<Void> handle(CreateDoctorCommand command);

    Mono<Void> handle(DeleteDoctorCommand command);

}
