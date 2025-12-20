package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Vehicle {
    int id;
    int userId;
    enum VehicleType {
        Bike,scooty, car;
    };
}
