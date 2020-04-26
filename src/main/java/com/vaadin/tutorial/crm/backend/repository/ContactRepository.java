package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c FROM Contact c " +
            "WHERE LOWER(c.firstName) LIKE LOWER(concat('%', :searchTerm, '%')) " +
            "OR LOWER(c.lastName) LIKE LOWER(concat('%', :searchTerm, '%'))") //
    List<Contact> search(@Param("searchTerm") String searchTerm);

}