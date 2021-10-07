package de.szut.repository;

import de.szut.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupllierRepository extends JpaRepository<Supplier, Long> {
}
