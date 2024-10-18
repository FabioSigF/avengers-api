package com.avengers.avengers_api.resource.avenger;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvengerEntityRepository extends JpaRepository<AvengerEntity, Long> {
    Optional<AvengerEntity> findByNick(String nick);
}
