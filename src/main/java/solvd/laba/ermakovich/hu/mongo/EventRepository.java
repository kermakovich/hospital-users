package solvd.laba.ermakovich.hu.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import solvd.laba.ermakovich.hu.event.Event;

/**
 * @author Ermakovich Kseniya
 */
public interface EventRepository extends ReactiveMongoRepository<Event, String> {

}
