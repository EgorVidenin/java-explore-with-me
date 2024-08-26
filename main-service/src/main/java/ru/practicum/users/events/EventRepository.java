package ru.practicum.users.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.users.events.model.Event;
import ru.practicum.users.events.model.State;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByCategoryId(Long categoryId);

    List<Event> findAllByInitiatorId(Long initiatorId, Pageable pageable);

    Event findByInitiatorIdAndId(Long initiatorId, Long id);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?1 " +
            "order by e.views desc")
    List<Event> getEventsPaidAndAvailable(@Param("now") LocalDateTime now,
                                          @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.eventDate > ?1 " +
            "order by e.views desc")
    List<Event> getEventsPaid(@Param("now") LocalDateTime now,
                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?1 " +
            "order by e.views desc")
    List<Event> getEventsAvailable(@Param("now") LocalDateTime now,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.eventDate > ?1 " +
            "order by e.views desc")
    List<Event> getEventsAll(@Param("now") LocalDateTime now,
                             @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?1 " +
            "order by e.eventDate desc")
    List<Event> getEventsPaidAndAvailableByDate(@Param("now") LocalDateTime now,
                                                @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.eventDate > ?1 " +
            "order by e.eventDate desc")
    List<Event> getEventsPaidByDate(@Param("now") LocalDateTime now,
                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?1 " +
            "order by e.eventDate desc")
    List<Event> getEventsAvailableByDate(@Param("now") LocalDateTime now,
                                         @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.eventDate > ?1 " +
            "order by e.eventDate desc")
    List<Event> getEventsAllByDate(@Param("now") LocalDateTime now,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsWitchCatAndPaidAndAvailable(@Param("categories") List<Long> categories,
                                                     @Param("now") LocalDateTime now,
                                                     @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsPaidAndCat(@Param("categories") List<Long> categories,
                                    @Param("now") LocalDateTime now,
                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsAvailableAndCat(@Param("categories") List<Long> categories,
                                         @Param("now") LocalDateTime now,
                                         @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsAllAndCat(@Param("categories") List<Long> categories,
                                   @Param("now") LocalDateTime now,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsWitchCatAndPaidAndAvailableByDate(@Param("categories") List<Long> categories,
                                                           @Param("now") LocalDateTime now,
                                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsPaidAndCatByDate(@Param("categories") List<Long> categories,
                                          @Param("now") LocalDateTime now,
                                          @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsAvailableAndCatByDate(@Param("categories") List<Long> categories,
                                               @Param("now") LocalDateTime now,
                                               @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsAllAndCatByDate(@Param("") List<Long> categories,
                                         @Param("") LocalDateTime now,
                                         @Param("") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsTextAndAvailableAndPaid(@Param("text") String text,
                                                 @Param("now") LocalDateTime now,
                                                 @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsTextAndAvailable(@Param("text") String text,
                                          @Param("now") LocalDateTime now,
                                          @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsTextAndPaid(@Param("") String text,
                                     @Param("") LocalDateTime now,
                                     @Param("") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.eventDate > ?2 " +
            "order by e.views desc")
    List<Event> getEventsText(@Param("text") String text,
                              @Param("now") LocalDateTime now,
                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndAvailableAndPaidByDate(@Param("text") String text,
                                                       @Param("now") LocalDateTime now,
                                                       @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndAvailableByDate(@Param("text") String text,
                                                @Param("now") LocalDateTime now,
                                                @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndPaidByDate(@Param("text") String text,
                                           @Param("now") LocalDateTime now,
                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.eventDate > ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextByDate(@Param("text") String text,
                                    @Param("now") LocalDateTime now,
                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategoriesAndAvailableAndPaid(@Param("text") String text,
                                                              @Param("categories") List<Long> categories,
                                                              @Param("now") LocalDateTime now,
                                                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategoriesAndAvailable(@Param("text") String text,
                                                       @Param("categories") List<Long> categories,
                                                       @Param("now") LocalDateTime now,
                                                       @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.paid = true " +
            "and e.eventDate > ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategoriesAndPaid(@Param("text") String text,
                                                  @Param("categories") List<Long> categories,
                                                  @Param("times") LocalDateTime times,
                                                  @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.eventDate > ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategories(@Param("text") String text,
                                           @Param("categories") List<Long> categories,
                                           @Param("times") LocalDateTime times,
                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesAndAvailableAndPaidByDate(@Param("text") String text,
                                                                    @Param("categories") List<Long> categories,
                                                                    @Param("now") LocalDateTime now,
                                                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate > ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesAndAvailableByDate(@Param("text") String text,
                                                             @Param("categories") List<Long> categories,
                                                             @Param("now") LocalDateTime now,
                                                             @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.paid = true " +
            "and e.eventDate > ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesAndPaidByDate(@Param("text") String text,
                                                        @Param("categories") List<Long> categories,
                                                        @Param("now") LocalDateTime now,
                                                        @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.eventDate > ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesByDate(@Param("text") String text,
                                                 @Param("categories") List<Long> categories,
                                                 @Param("now") LocalDateTime now,
                                                 @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?1 and ?2 " +
            "order by e.views desc")
    List<Event> getEventsPaidAndAvailable(@Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end,
                                          @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.eventDate between ?1 and ?2 " +
            "order by e.views desc")
    List<Event> getEventsPaid(@Param("start") LocalDateTime start,
                              @Param("end") LocalDateTime end,
                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?1 and ?2 " +
            "order by e.views desc")
    List<Event> getEventsAvailable(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.eventDate between ?1 and ?2 " +
            "order by e.views desc")
    List<Event> getEventsAll(@Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end,
                             @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?1 and ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsPaidAndAvailableByDate(@Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end,
                                                @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.eventDate between ?1 and ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsPaidByDate(@Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end,
                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?1 and ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsAvailableByDate(@Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end,
                                         @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.eventDate between ?1 and ?2 " +
            "order by e.eventDate desc")
    List<Event> getEventsAllByDate(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsWitchCatAndPaidAndAvailable(@Param("categories") List<Long> categories,
                                                     @Param("start") LocalDateTime start,
                                                     @Param("end") LocalDateTime end,
                                                     @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsPaidAndCat(@Param("categories") List<Long> categories,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end,
                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsAvailableAndCat(@Param("categories") List<Long> categories,
                                         @Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end,
                                         @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsAllAndCat(@Param("categories") List<Long> categories,
                                   @Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsWitchCatAndPaidAndAvailableByDate(@Param("categories") List<Long> categories,
                                                           @Param("start") LocalDateTime start,
                                                           @Param("end") LocalDateTime end,
                                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.paid = true " +
            "and e.category.id in ?1 " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsPaidAndCatByDate(@Param("categories") List<Long> categories,
                                          @Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end,
                                          @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsAvailableAndCatByDate(@Param("categories") List<Long> categories,
                                               @Param("start") LocalDateTime start,
                                               @Param("end") LocalDateTime end,
                                               @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsAllAndCatByDate(@Param("categories") List<Long> categories,
                                         @Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end,
                                         @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndAvailableAndPaid(@Param("text") String text,
                                                 @Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end,
                                                 @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndAvailable(@Param("text") String text,
                                          @Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end,
                                          @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsTextAndPaid(@Param("text") String text,
                                     @Param("start") LocalDateTime start,
                                     @Param("end") LocalDateTime end,
                                     @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesAndAvailableAndPaidByDate(@Param("text") String text,
                                                                    @Param("categories") List<Long> categories,
                                                                    @Param("start") LocalDateTime start,
                                                                    @Param("end") LocalDateTime end,
                                                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesAndAvailableByDate(@Param("text") String text,
                                                             @Param("categories") List<Long> categories,
                                                             @Param("start") LocalDateTime start,
                                                             @Param("end") LocalDateTime end,
                                                             @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.paid = true " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesAndPaidByDate(@Param("text") String text,
                                                        @Param("categories") List<Long> categories,
                                                        @Param("start") LocalDateTime start,
                                                        @Param("end") LocalDateTime end,
                                                        @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2 " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndCategoriesByDate(@Param("text") String text,
                                                 @Param("categories") List<Long> categories,
                                                 @Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end,
                                                 @Param("pageable") Pageable pageable);


    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.views desc")
    List<Event> getEventsText(@Param("text") String text,
                              @Param("start") LocalDateTime start,
                              @Param("end") LocalDateTime end,
                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndAvailableAndPaidByDate(@Param("text") String text,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end,
                                                       @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndAvailableByDate(@Param("text") String text,
                                                @Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end,
                                                @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.paid = true " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextAndPaidByDate(@Param("text") String text,
                                           @Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end,
                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.eventDate between ?2 and ?3 " +
            "order by e.eventDate desc")
    List<Event> getEventsTextByDate(@Param("text") String text,
                                    @Param("start") LocalDateTime start,
                                    @Param("end") LocalDateTime end,
                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.paid = true " +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategoriesAndAvailableAndPaid(@Param("text") String text,
                                                              @Param("categories") List<Long> categories,
                                                              @Param("start") LocalDateTime start,
                                                              @Param("end") LocalDateTime end,
                                                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.confirmedRequests < e.participantLimit " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategoriesAndAvailable(@Param("text") String text,
                                                       @Param("categories") List<Long> categories,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end,
                                                       @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.state in ?1 " +
            "and e.category.id in ?2 " +
            "and e.eventDate between ?3 and ?4")
    List<Event> getEventsWithStateAndCategoriesAndTimes(@Param("states") List<State> states,
                                                        @Param("categories") List<Long> categories,
                                                        @Param("rangeStart") LocalDateTime rangeStart,
                                                        @Param("rangeEnd") LocalDateTime rangeEnd,
                                                        @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.state in ?1 " +
            "and e.eventDate between ?2 and ?3")
    List<Event> getEventsWithStateAndTimes(@Param("states") List<State> states,
                                           @Param("rangeStart") LocalDateTime rangeStart,
                                           @Param("rangeEnd") LocalDateTime rangeEnd,
                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.initiator.id in ?1 " +
            "and e.eventDate between ?2 and ?3")
    List<Event> getEventsWithUsersAndTimes(@Param("users") List<Long> users,
                                           @Param("rangeStart") LocalDateTime rangeStart,
                                           @Param("rangeEnd") LocalDateTime rangeEnd,
                                           @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.initiator.id in ?1 " +
            "and e.state in ?2 " +
            "and e.eventDate between ?3 and ?4")
    List<Event> getEventsWithUsersAndStatesAndTimes(@Param("users") List<Long> users,
                                                    @Param("states") List<State> states,
                                                    @Param("rangeStart") LocalDateTime rangeStart,
                                                    @Param("rangeEnd") LocalDateTime rangeEnd,
                                                    @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.paid = true " +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategoriesAndPaid(@Param("text") String text,
                                                  @Param("categories") List<Long> categories,
                                                  @Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end,
                                                  @Param("pageable") Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.initiator.id IN ?1 AND e.category.id IN ?2 AND e.eventDate BETWEEN ?3 AND ?4")
    List<Event> getEventsWithUsersAndCategoriesAndTimes(
            @Param("users") List<Long> users,
            @Param("categories") List<Long> categories,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("pageable") Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.initiator.id IN ?1 AND e.state IN ?2 AND e.createdOn <= ?3")
    List<Event> getEventsWithUsersAndStates(
            @Param("users") List<Long> users,
            @Param("states") List<State> states,
            @Param("now") LocalDateTime now,
            @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where (lower(e.annotation) like ?1 " +
            "or lower(e.description) like ?1) " +
            "and e.category.id in ?2" +
            "and e.eventDate between ?3 and ?4 " +
            "order by e.views desc")
    List<Event> getEventsTextAndCategories(String text,
                                           @Param("categories") List<Long> categories,
                                           @Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end,
                                           @Param("pageable") Pageable pageable);


    @Query("select e from Event e " +
            "where e.initiator.id in ?1 " +
            "and e.state in ?2 " +
            "and e.category.id in ?3 " +
            "and e.eventDate between ?4 and ?5")
    List<Event> getEventsWithUsersAndStatesAndCategoriesAndTimes(@Param("users") List<Long> users,
                                                                 @Param("states") List<State> states,
                                                                 @Param("categories") List<Long> categories,
                                                                 @Param("rangeStart") LocalDateTime rangeStart,
                                                                 @Param("rangeEnd") LocalDateTime rangeEnd,
                                                                 @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.eventDate between ?1 and ?2")
    List<Event> getEventsWithTimes(@Param("rangeStart") LocalDateTime rangeStart,
                                   @Param("rangeEnd") LocalDateTime rangeEnd,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.eventDate between ?2 and ?3")
    List<Event> getEventsWithCategoryAndTimes(@Param("categories") List<Long> categories,
                                              @Param("rangeStart") LocalDateTime rangeStart,
                                              @Param("rangeEnd") LocalDateTime rangeEnd,
                                              @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.category.id in ?1 " +
            "and e.eventDate > ?2")
    List<Event> getEventsWithCategory(@Param("categories") List<Long> categories,
                                      @Param("rangeEnd") LocalDateTime rangeEnd,
                                      @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.state in ?1 " +
            "and e.category.id in ?2 " +
            "and e.eventDate > ?3")
    List<Event> getEventsWithStateAndCategories(@Param("states") List<State> states,
                                                @Param("categories") List<Long> categories,
                                                @Param("rangeEnd") LocalDateTime rangeEnd,
                                                @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.state in ?1 " +
            "and e.eventDate > ?2")
    List<Event> getEventsWithState(@Param("states") List<State> states,
                                   @Param("rangeEnd") LocalDateTime rangeEnd,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.initiator.id in ?1 " +
            "and e.eventDate > ?2")
    List<Event> getEventsWithUsers(@Param("users") List<Long> users,
                                   @Param("rangeEnd") LocalDateTime rangeEnd,
                                   @Param("pageable") Pageable pageable);

    @Query("select e from Event e " +
            "where e.initiator.id in ?1 " +
            "and e.category.id in ?2 " +
            "and e.eventDate > ?3")
    List<Event> getEventsWithUsersAndCategories(@Param("users") List<Long> users,
                                                @Param("categories") List<Long> categories,
                                                @Param("rangeEnd") LocalDateTime rangeEnd,
                                                @Param("pageable") Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.initiator.id IN ?1 AND e.state IN ?2 AND e.category.id IN ?3 AND e.createdOn <= ?4")
    List<Event> getEventsWithUsersAndStatesAndCategories(
            @Param("users") List<Long> users,
            @Param("states") List<State> states,
            @Param("categories") List<Long> categories,
            @Param("now") LocalDateTime now,
            @Param("pageable") Pageable pageable);
}