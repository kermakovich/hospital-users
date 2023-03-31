package solvd.laba.ermakovich.hu.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import solvd.laba.ermakovich.hu.service.event.Event;

/**
 * @author Ermakovich Kseniya
 */
public interface EventRepository extends ReactiveMongoRepository<Event, String> {

}
