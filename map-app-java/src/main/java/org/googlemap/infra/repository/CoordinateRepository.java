package org.googlemap.infra.repository;

import org.googlemap.infra.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate,Integer> {}
