package com.ash.shrimpapi.davidi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShrimpRepository extends CrudRepository<Shrimp, Long> {

}
