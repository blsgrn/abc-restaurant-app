package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Promotion;
import com.balasegaran.AbcRestuarant.Service.PromotionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

  @Autowired
  private PromotionService promotionService;

  @GetMapping
  public List<Promotion> getAllPromotions() {
    return promotionService.getAllPromotions();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Promotion> getPromotionById(@PathVariable ObjectId id) {
    Optional<Promotion> promotion = promotionService.getPromotionById(id);
    return promotion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Promotion createPromotion(@RequestBody Promotion promotion) {
    return promotionService.createPromotion(promotion);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Promotion> updatePromotion(@PathVariable ObjectId id, @RequestBody Promotion promotionDetails) {
    Optional<Promotion> updatedPromotion = Optional.ofNullable(promotionService.updatePromotion(id, promotionDetails));
    return updatedPromotion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePromotion(@PathVariable ObjectId id) {
    promotionService.deletePromotion(id);
    return ResponseEntity.ok().build();
  }
}
