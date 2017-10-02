package pl.sparkidea.reactive.demo.server.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.sparkidea.reactive.demo.server.dto.EventDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * @author Maciej Lesniak
 */
@RestController
public class DemoController {

    @GetMapping("/info/{id}")
    public Mono<EventDto> getOneInfoById(@PathVariable("id") String id) {
        return Mono.just(new EventDto(id, LocalDateTime.now()));
    }


    @GetMapping(value = "/infos", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<EventDto> getInfosAsStream() {

        Flux<EventDto> dtoFlux = Flux.fromStream(Stream.generate(() -> new EventDto(UUID.randomUUID().toString(), LocalDateTime.now())));
        Flux<Long> duration = Flux.interval(Duration.ofMillis(100));

        return Flux.zip(dtoFlux, duration).map(Tuple2::getT1);
    }

}
