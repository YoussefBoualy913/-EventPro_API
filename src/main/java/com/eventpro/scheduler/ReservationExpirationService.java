package com.eventpro.scheduler;


import com.eventpro.entity.Reservation;
import com.eventpro.entity.enums.StatutReservation;
import com.eventpro.repository.ReservationRepository;
import com.eventpro.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationExpirationService {

    private final ReservationRepository reservationRepository;

    private final ReservationService reservationService;

    @Scheduled(fixedRate = 60000)
    public void annulerReservationsExpirees() {

        List<Reservation> reservationsExpirees =
                reservationRepository.findReservationsExpirees(
                        StatutReservation.PENDING_PAYMENT,
                        LocalDateTime.now()
                );

        for (Reservation reservation : reservationsExpirees) {
            reservationService.annulerReservationExpiree(reservation.getId());
        }
    }
}