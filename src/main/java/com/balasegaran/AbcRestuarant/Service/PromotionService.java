package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Promotion;
import com.balasegaran.AbcRestuarant.Repository.PromotionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class PromotionService {

  @Autowired
  private PromotionRepository promotionRepository;

  public List<Promotion> getAllPromotions() {
    return promotionRepository.findAll();
  }

  public Optional<Promotion> getPromotionById(ObjectId id) {
    return promotionRepository.findById(id);
  }

  public Promotion createPromotion(Promotion promotion) {
    return promotionRepository.save(promotion);
  }

  public Promotion updatePromotion(ObjectId id, Promotion promotionDetails) {
    promotionDetails.setId(id);
    return promotionRepository.save(promotionDetails);
  }

  public void deletePromotion(ObjectId id) {
    promotionRepository.deleteById(id);
  }
}
