package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Hospitality;
import com.balasegaran.AbcRestuarant.Repository.HospitalityRepository;
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
        return hospitalityRepository.save(hospitality);
    }

    public Hospitality updateHospitality(ObjectId id, Hospitality hospitalityDetails) {
        hospitalityDetails.setId(id);
        return hospitalityRepository.save(hospitalityDetails);
    }

    public void deleteHospitality(ObjectId id) {
        hospitalityRepository.deleteById(id);
    }
}
