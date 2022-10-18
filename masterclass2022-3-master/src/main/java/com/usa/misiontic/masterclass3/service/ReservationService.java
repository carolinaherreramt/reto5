package com.usa.misiontic.masterclass3.service;


import com.usa.misiontic.masterclass3.entities.Admin;
import com.usa.misiontic.masterclass3.entities.DTOs.CompletedAndCancelled;
import com.usa.misiontic.masterclass3.entities.DTOs.TotalAndClient;
import com.usa.misiontic.masterclass3.entities.Reservation;
import com.usa.misiontic.masterclass3.repository.AdminRepository;
import com.usa.misiontic.masterclass3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null) {
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation>reservationEncontrado=getReservation(reservation.getIdReservation());
            if(reservationEncontrado.isEmpty()){
                return reservationRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }
    public Reservation update(Reservation reservation){
        if (reservation.getIdReservation()!=null){
            Optional<Reservation> reservationEncontrado=getReservation(reservation.getIdReservation());
            if(!reservationEncontrado.isEmpty()){
                if(reservation.getStartDate()!=null){
                    reservationEncontrado.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!=null){
                    reservationEncontrado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus()!=null){
                    reservationEncontrado.get().setStatus(reservation.getStatus());
                }
                return reservationRepository.save(reservationEncontrado.get());

            }
        }
        return reservation;
    }
    public boolean deleteReservation(int id){
        Boolean respuesta= getReservation(id).map(reservation-> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return respuesta;
    }

    public List<Reservation> getReservationsBetweenDatesReport(String fechaA, String fechaB) {
        SimpleDateFormat parser= new SimpleDateFormat ("yyyy- MM-dd");

        Date a= new Date ();
        Date b= new Date();
        try {
            a = parser.parse(fechaA);
            b = parser.parse(fechaB);
        }catch   (ParseException exception) {
            exception.printStackTrace();
        }
        if (a.before(b)) {
            return reservationRepository.getReservationsBetweenDates(a, b);
        }else{
            return new ArrayList<>();
        }
    }
    public CompletedAndCancelled getReservationStatusReport(){
        List <Reservation> completed=reservationRepository.getReservationsByStatus("completed");
        List <Reservation> cancelled= reservationRepository.getReservationsByStatus("cancelled");

        int cantidadCompletadas= completed.size();
        int cantidadCanceladas= completed.size();

        return new CompletedAndCancelled((long) cantidadCompletadas, (long)cantidadCanceladas);
    }
    public List <TotalAndClient> getTopClientReport(){
        return reservationRepository.getTopClients();

    }

}
