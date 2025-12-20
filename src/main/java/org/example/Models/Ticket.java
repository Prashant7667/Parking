package org.example.Models;

import java.time.LocalDateTime;

public class Ticket {
    int id;
    int VechileId;
    int slotId;
    LocalDateTime entryTime=LocalDateTime.now();
    Boolean isActive;
}
