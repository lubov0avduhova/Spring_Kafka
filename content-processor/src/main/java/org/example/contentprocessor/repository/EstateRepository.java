package org.example.contentprocessor.repository;

import org.example.contentprocessor.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {
    Optional<Estate> findByCadastr(String cadastr);
}
