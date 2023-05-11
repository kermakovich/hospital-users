package solvd.laba.ermakovich.hu.repository.mongo;

import reactor.core.publisher.Mono;

/**
 * @author Ermakovich Kseniya
 */
public interface SaveCustom<S> {

    Mono<S> save(S event);

}
