package com.eventmanagement.repository;

import com.eventmanagement.dto.EventResponseDTO;
import com.eventmanagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByOrganizerId(Long organizerId);

    List<Event> findByTitleContainingIgnoreCase(String keyword);



    List<Event> findByLocationIgnoreCase(String location);

    List<Event> findByEventDate(LocalDate eventDate);

    @Query("""
    SELECT e FROM Event e
    WHERE (:keyword IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')))
      AND (:location IS NULL OR LOWER(e.location) = LOWER(:location))
      AND (:eventDate IS NULL OR e.eventDate = :eventDate)
""")
    List<Event> searchEvents(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("eventDate") LocalDate eventDate
    );

}
