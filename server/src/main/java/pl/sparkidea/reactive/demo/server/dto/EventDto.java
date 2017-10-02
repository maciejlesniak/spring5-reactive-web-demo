package pl.sparkidea.reactive.demo.server.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author Maciej Lesniak
 */

@Data
public class EventDto {

    private final String uuid;
    private final LocalDateTime when;

}
