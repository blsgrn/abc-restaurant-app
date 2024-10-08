package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Hospitality;
import com.balasegaran.AbcRestuarant.Repository.HospitalityRepository;
import com.balasegaran.AbcRestuarant.Validation.HospitalityValidator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalityService {

    @Autowired
    private HospitalityRepository hospitalityRepository;

    public List<Hospitality> getAllHospitalities() {
        return hospitalityRepository.findAll();
    }

    public Optional<Hospitality> getHospitalityById(ObjectId id) {
        return hospitalityRepository.findById(id);
    }

    public Hospitality createHospitality(Hospitality hospitality) {

        HospitalityValidator.validate(hospitality);
        return hospitalityRepository.save(hospitality);
    }

    public Hospitality updateHospitality(ObjectId id, Hospitality hospitalityDetails) {

        HospitalityValidator.validate(hospitalityDetails);

        return hospitalityRepository.findById(id)
                .map(existingHospitality -> {
                    existingHospitality.setName(hospitalityDetails.getName());
                    existingHospitality.setDescription(hospitalityDetails.getDescription());
                    existingHospitality.setPrice(hospitalityDetails.getPrice());
                    existingHospitality.setCategory(hospitalityDetails.getCategory());
                    existingHospitality.setImageUrl(hospitalityDetails.getImageUrl());
                    return hospitalityRepository.save(existingHospitality);
                }).orElseThrow(() -> new IllegalArgumentException("Hospitality not found"));
    }

    public void deleteHospitality(ObjectId id) {
        hospitalityRepository.deleteById(id);
    }

    public List<Hospitality> searchHospitalities(String query) {

        return hospitalityRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(query,
                        query, query);
    }
}
