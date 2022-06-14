package com.ash.shrimpapi.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShrimpService {
  @Autowired
  private ShrimpRepository shrimpRepository;

  public Iterable<Shrimp> list() {
    return shrimpRepository.findAll();
  }

  public Optional<Shrimp> findById(Long id) {
    return shrimpRepository.findById(id);
  }

  public Shrimp create(Shrimp shrimp) {
    return shrimpRepository.save(shrimp);
  }

  public Optional<Shrimp> update(Shrimp shrimp) {
    Optional<Shrimp> foundShrimp = shrimpRepository.findById(shrimp.getId());

    if (foundShrimp.isPresent()) {
      Shrimp updatedShrimp = foundShrimp.get();
      updatedShrimp.setName(shrimp.getName());
      updatedShrimp.setImage(shrimp.getImage());

      shrimpRepository.save(updatedShrimp);
      return Optional.of(updatedShrimp);
    } else {
      return Optional.empty();
    }
  }

  public void deleteById(Long id) {
    shrimpRepository.deleteById(id);
  }
}
