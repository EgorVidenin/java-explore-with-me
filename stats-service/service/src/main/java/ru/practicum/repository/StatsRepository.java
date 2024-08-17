package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.StatsDto;
import ru.practicum.model.Hit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<Hit, Long> {

    @Query("SELECT new ru.practicum.StatsDto(s.app, s.uri, COUNT(s.uri)) " +
            "FROM Hit s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "AND s.uri IN :uris " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.uri) DESC")
    List<StatsDto> getWithUri(@Param("start") LocalDateTime start,
                              @Param("end") LocalDateTime end,
                              @Param("uris") List<String> uris);

    @Query("SELECT new ru.practicum.StatsDto(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Hit s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "AND s.uri IN :uris " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip) DESC")
    List<StatsDto> getWithUriUnique(@Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end,
                                    @Param("uris") List<String> uris);

    @Query("SELECT new ru.practicum.StatsDto(s.app, s.uri, COUNT(s.uri)) " +
            "FROM Hit s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(s.uri) DESC")
    List<StatsDto> getWithoutUri(@Param("start") LocalDateTime start,
                                 @Param("end") LocalDateTime end);

    @Query("SELECT new ru.practicum.StatsDto(s.app, s.uri, COUNT(DISTINCT s.ip)) " +
            "FROM Hit s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "GROUP BY s.app, s.uri " +
            "ORDER BY COUNT(DISTINCT s.ip) DESC")
    List<StatsDto> getWithoutUriUnique(@Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);
}