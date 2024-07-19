package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Gallery;
import com.balasegaran.AbcRestuarant.Service.GalleryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/galleries")
public class GalleryController {

  @Autowired
  private GalleryService galleryService;

  @GetMapping
  public List<Gallery> getAllGalleries() {
    return galleryService.getAllGalleries();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Gallery> getGalleryById(@PathVariable ObjectId id) {
    Optional<Gallery> gallery = galleryService.getGalleryById(id);
    return gallery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public Gallery createGallery(@RequestBody Gallery gallery) {
    return galleryService.createGallery(gallery);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Gallery> updateGallery(@PathVariable ObjectId id, @RequestBody Gallery galleryDetails) {
    Optional<Gallery> updatedGallery = Optional.ofNullable(galleryService.updateGallery(id, galleryDetails));
    return updatedGallery.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGallery(@PathVariable ObjectId id) {
    galleryService.deleteGallery(id);
    return ResponseEntity.ok().build();
  }
}
