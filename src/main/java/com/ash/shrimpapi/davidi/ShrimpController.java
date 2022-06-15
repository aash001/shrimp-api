package com.ash.shrimpapi.davidi;

import java.util.Map;
import java.util.HashMap;

import com.ash.shrimpapi.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/shrimp")
public class ShrimpController {
  @Autowired
  private ShrimpService shrimpService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Shrimp>> list() {
    Iterable<Shrimp> shrimp = shrimpService.list();
    return createHashPlural(shrimp);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Shrimp> read(@PathVariable Long id) {
    Shrimp shrimp = shrimpService
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));
    return createHashSingular(shrimp);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Shrimp> create(@Validated @RequestBody Shrimp shrimp) {
    Shrimp createdShrimp = shrimpService.create(shrimp);
    return createHashSingular(createdShrimp);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Shrimp> update(@RequestBody Shrimp shrimp, @PathVariable Long id) {
    Shrimp updatedShrimp = shrimpService
        .update(shrimp)
        .orElseThrow(() -> new ResourceNotFoundException("No resource with that ID"));

    return createHashSingular(updatedShrimp);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    shrimpService.deleteById(id);
  }

  private Map<String, Shrimp> createHashSingular(Shrimp shrimp) {
    Map<String, Shrimp> response = new HashMap<String, Shrimp>();
    response.put("shrimp", shrimp);

    return response;
  }

  private Map<String, Iterable<Shrimp>> createHashPlural(Iterable<Shrimp> shrimps) {
    Map<String, Iterable<Shrimp>> response = new HashMap<String, Iterable<Shrimp>>();
    response.put("shrimp", shrimps);

    return response;
  }
}
