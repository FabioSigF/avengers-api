package com.avengers.avengers_api.resource.avenger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvengerEntityRepository extends JpaRepository<AvengerEntity, Long> {
}
