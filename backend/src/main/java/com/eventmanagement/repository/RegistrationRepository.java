package com.eventmanagement.repository;

import com.eventmanagement.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByUserId(Long userId);

    List<Registration> findByEventId(Long eventId);

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

    @Query("""
    SELECT SUM(r.quantity)
    FROM Registration r
    WHERE r.event.organizer.id = :organizerId
""")
    Integer getTotalTicketsSold(@Param("organizerId") Long organizerId);

    @Query("""
    SELECT SUM(r.totalAmount)
    FROM Registration r
    WHERE r.event.organizer.id = :organizerId
""")
    Double getTotalRevenue(@Param("organizerId") Long organizerId);

    @Query("""
    SELECT r.event.id, r.event.title, SUM(r.quantity), SUM(r.totalAmount)
    FROM Registration r
    WHERE r.event.organizer.id = :organizerId
    GROUP BY r.event.id, r.event.title
""")
    List<Object[]> getEventWiseStats(@Param("organizerId") Long organizerId);
}
