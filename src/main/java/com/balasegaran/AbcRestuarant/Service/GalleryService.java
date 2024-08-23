package com.balasegaran.AbcRestuarant.Service;

import com.balasegaran.AbcRestuarant.Model.Gallery;
import com.balasegaran.AbcRestuarant.Repository.GalleryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class GalleryService {

  @Autowired
  private GalleryRepository galleryRepository;

  public List<Gallery> getAllGalleries() {
    return galleryRepository.findAll();
  }

  public Optional<Gallery> getGalleryById(ObjectId id) {
    return galleryRepository.findById(id);
  }

  public Gallery createGallery(Gallery gallery) {
    return galleryRepository.save(gallery);
  }

  public Gallery updateGallery(ObjectId id, Gallery galleryDetails) {
    galleryDetails.setId(id);
    return galleryRepository.save(galleryDetails);
  }

  public void deleteGallery(ObjectId id) {
    galleryRepository.deleteById(id);
  }
}
