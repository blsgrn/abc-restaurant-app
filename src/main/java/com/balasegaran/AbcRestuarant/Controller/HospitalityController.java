package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Hospitality;
import com.balasegaran.AbcRestuarant.Service.HospitalityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitalities")
public class HospitalityController {

  @Autowired
  private HospitalityService hospitalityService;

  @GetMapping
  public List<Hospitality> getAllHospitalities() {
    return hospitalityService.getAllHospitalities();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hospitality> getHospitalityById(@PathVariable ObjectId id) {
    return hospitalityService.getHospitalityById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Hospitality createHospitality(@RequestBody Hospitality hospitality) {
    return hospitalityService.createHospitality(hospitality);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Hospitality> updateHospitality(@PathVariable ObjectId id,
      @RequestBody Hospitality hospitalityDetails) {
    return hospitalityService.getHospitalityById(id)
        .map(hospitality -> {
          Hospitality updatedHospitality = hospitalityService.updateHospitality(id, hospitalityDetails);
          return ResponseEntity.ok(updatedHospitality);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHospitality(@PathVariable ObjectId id) {
    return hospitalityService.getHospitalityById(id)
        .map(hospitality -> {
          hospitalityService.deleteHospitality(id);
          return ResponseEntity.noContent().<Void>build(); // Use the generic type parameter
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
