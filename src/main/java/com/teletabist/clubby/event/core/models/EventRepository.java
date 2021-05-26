package com.teletabist.clubby.event.core.models;

import com.teletabist.clubby.user.models.UserRole;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

//@Repository <-- might be needed
public interface EventRepository extends JpaRepository<Event, Integer>{
    Iterable<Event> findAllByClub_id(Integer club_id, Sort sort);
    Event findByClub_idAndId(Integer club_id, Integer id);
    Collection<Event> findAllByUserRole(UserRole userRole);
    Integer deleteAllByUserRole(UserRole userRole);
}
