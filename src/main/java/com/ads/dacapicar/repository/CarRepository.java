package com.ads.dacapicar.repository;

import com.ads.dacapicar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
