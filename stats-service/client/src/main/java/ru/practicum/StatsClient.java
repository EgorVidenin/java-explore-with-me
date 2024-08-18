package ru.practicum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Component
public class StatsClient {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final WebClient webClient;

    public String url;

    public StatsClient(@Value("${stats.url:http://localhost:9090}") String url) {
        this.url = url;
        webClient = WebClient.create(url);
    }

    public HitDto addHit(HitDto hitDto) {
        return webClient
                .post()
                .uri("/hit")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(hitDto))
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server Error"));
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client Error"));
                    } else {
                        return clientResponse.bodyToMono(HitDto.class);
                    }
                })
                .block();
    }

    public List<StatsDto> getStats(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime, String uris, boolean unique) {
        String start = startLocalDateTime.format(DATE_TIME_FORMATTER);
        String end = endLocalDateTime.format(DATE_TIME_FORMATTER);
        String uri;
        if (Objects.nonNull(uris)) {
            uri = "/stats?start={start}&end={end}&uris={urisString}&unique={unique}";
        } else {
            uri = "/stats?start={start}&end={end}&unique={unique}";
        }
        return webClient
                .get()
                .uri(uri, start, end, uris, String.valueOf(unique))
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        return Flux.error(new RuntimeException("Server Error"));
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return Flux.error(new RuntimeException("Client Error"));
                    } else {
                        return clientResponse.bodyToFlux(StatsDto.class);
                    }
                })
                .collectList()
                .block();
    }
}
