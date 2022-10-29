package com.musala.drones;

import com.musala.drones.entity.Medication;
import com.musala.drones.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DronesApplication implements CommandLineRunner {

    private final MedicationRepository medicationRepo;

    public DronesApplication(MedicationRepository medicationRepo) {
        this.medicationRepo = medicationRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(DronesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addMedicationList();
    }

    private void addMedicationList() {

        List<Medication> medications = new ArrayList();
        medications.add(new Medication("medicine-1", 30d, "ABCDE1111"));
        medications.add(new Medication("medicine-2", 20d, "ABCDE2222"));
        medications.add(new Medication("medicine-3", 50d, "ABCDE3333"));
        medications.add(new Medication("medicine-4", 100d, "ABCDE4444"));
        medications.add(new Medication("medicine-5", 40d, "ABCDE5555"));
        medications.add(new Medication("medicine-6", 60d, "ABCDE6666"));
        medications.add(new Medication("medicine-7", 70d, "ABCDE7777"));
        medications.add(new Medication("medicine-8", 150d, "ABCDE8888"));
        medications.add(new Medication("medicine-9", 90d, "ABCDE9999"));
        medications.add(new Medication("medicine-10", 120d, "ABCDE0000"));

        medicationRepo.saveAll(medications);
    }
}
