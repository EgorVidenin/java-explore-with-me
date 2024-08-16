package ru.practicum;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class StatsDto {
    private String app;
    private String uri;
    private long hits;
}
