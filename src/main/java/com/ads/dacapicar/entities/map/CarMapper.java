package com.ads.dacapicar.entities.map;

import com.ads.dacapicar.entities.Car;
import com.ads.dacapicar.entities.dto.request.CarRequestDTO;
import com.ads.dacapicar.entities.dto.response.CarResponseDTO;

public class CarMapper {

    public static Car toEntity(CarRequestDTO carRequestDTO){
        return new Car(null, carRequestDTO.getName(), carRequestDTO.getPrice(), carRequestDTO.getYear(), carRequestDTO.getCity(), carRequestDTO.getPlaca(), carRequestDTO.getCompany());
    }

    public static CarResponseDTO toDto (Car car){
        return new CarResponseDTO(car.getId(), car.getName(), car.getPrice(), car.getYear(), car.getCity(), car.getPlaca(), car.getCompany());
    }
}
