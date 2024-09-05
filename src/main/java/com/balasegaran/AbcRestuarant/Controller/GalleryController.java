// src/main/java/com/balasegaran/AbcRestuarant/Controller/GalleryController.java
package com.balasegaran.AbcRestuarant.Controller;

import com.balasegaran.AbcRestuarant.Model.Gallery;
import com.balasegaran.AbcRestuarant.Service.GalleryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/galleries")
public class GalleryController {

  @Autowired
  private GalleryService galleryService;

  @Value("${file.upload-dir}")
  private String uploadDir;

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
    Optional<Gallery> galleryOpt = galleryService.getGalleryById(id);
    if (!galleryOpt.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Gallery gallery = galleryOpt.get();

    String imageUrl = gallery.getImageUrl();
    if (imageUrl != null && imageUrl.startsWith("/uploads/")) {
      String fileName = imageUrl.replace("/uploads/", "");
      Path filePath = Paths.get(uploadDir, fileName);
      try {
        Files.deleteIfExists(filePath);
      } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
    }

    galleryService.deleteGallery(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/upload")
  public ResponseEntity<Gallery> uploadGallery(
      @RequestParam("restaurantId") String restaurantId,
      @RequestParam("description") String description,
      @RequestParam("file") MultipartFile file) {

    if (file.isEmpty()) {
      return ResponseEntity.badRequest().body(null);
    }

    String contentType = file.getContentType();

    if (contentType == null || !contentType.startsWith("image/")) {
      return ResponseEntity.badRequest().body(null);
    }

    try {
      Path uploadPath = Paths.get(uploadDir);
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      String originalFilename = file.getOriginalFilename();
      String fileName = System.currentTimeMillis() + "_" + originalFilename;

      Path filePath = uploadPath.resolve(fileName);
      Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

      String imageUrl = "/uploads/" + fileName;

      Gallery gallery = new Gallery();
      gallery.setRestaurantId(restaurantId);
      gallery.setImageUrl(imageUrl);
      gallery.setDescription(description);

      Gallery savedGallery = galleryService.createGallery(gallery);

      return ResponseEntity.status(HttpStatus.CREATED).body(savedGallery);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

}
