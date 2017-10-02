package pl.sparkidea.reactive.demo.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import pl.sparkidea.reactive.demo.client.dto.EventDto;
import pl.sparkidea.reactive.demo.client.dto.InfoDto;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;

/**
 * @author Maciej Lesniak
 */
@RestController
public class DemoInfosController {

    private WebClient webClient;

    @Autowired
    public DemoInfosController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Set<InfoDto>> getEventsStream() {
        return webClient
                .get()
                .uri("/infos")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(EventDto.class)
                .buffer(Duration.ofSeconds(3))
                .flatMap(eventDtos -> {
                    Set<InfoDto> setOfInfos = eventDtos.stream().map(eventDto -> new InfoDto(eventDto.getUuid())).collect(Collectors.toSet());
                    return Flux.just(setOfInfos);
                });
    }

}
