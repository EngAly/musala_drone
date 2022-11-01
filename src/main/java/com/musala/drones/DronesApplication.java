package com.musala.drones;

import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.MedicationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DronesApplication implements CommandLineRunner {

    private final MedicationService medicationService;

    public DronesApplication(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DronesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        medicationService.saveMedicationListDummyData();
    }


}
