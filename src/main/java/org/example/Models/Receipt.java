package org.example.Models;

import java.time.LocalDateTime;

public class Receipt {
    int id;
    int ticketId;
    LocalDateTime exitTime=LocalDateTime.now();
    double totalFee;
    Boolean payementStatus;
}
