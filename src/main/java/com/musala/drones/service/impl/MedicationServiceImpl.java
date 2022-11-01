package com.musala.drones.service.impl;

import com.musala.drones.util.DroneUtils;
import com.musala.drones.entity.Medication;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.MedicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MedicationServiceImpl implements MedicationService {

    private Logger log = LoggerFactory.getLogger(MedicationServiceImpl.class);

    private MedicationRepository medicationRepo;

    public MedicationServiceImpl(MedicationRepository medicationRepo) {
        this.medicationRepo = medicationRepo;
    }

    public List<Medication> getMedicationsByCodes(Set<String> medicationSet) {

        if (DroneUtils.isEmpty(medicationSet)) return null;

        List<Medication> medicationList = medicationRepo.getByCodeIn(medicationSet);

        return (DroneUtils.isEmpty(medicationList) || medicationList.size() != medicationSet.size()) ? null : medicationList;

    }

    public void saveMedicationListDummyData() {
        medicationRepo.saveAll(getMedicationList());
        log.info("Medications dummy data successfully added to database");

    }

    private List<Medication> getMedicationList() {
        return new ArrayList() {
            {
                add(new Medication("medicine-1", 30d, "ABCDE1111"));
                add(new Medication("medicine-2", 20d, "ABCDE2222"));
                add(new Medication("medicine-3", 50d, "ABCDE3333"));
                add(new Medication("medicine-4", 100d, "ABCDE4444"));
                add(new Medication("medicine-5", 40d, "ABCDE5555"));
                add(new Medication("medicine-6", 60d, "ABCDE6666"));
                add(new Medication("medicine-7", 70d, "ABCDE7777"));
                add(new Medication("medicine-8", 150d, "ABCDE8888"));
                add(new Medication("medicine-9", 90d, "ABCDE9999"));
                add(new Medication("medicine-10", 120d, "ABCDE0000"));
            }
        };
    }
}
