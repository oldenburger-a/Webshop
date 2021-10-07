package de.szut.service;

import de.szut.exceptionhandling.ResourceNotFoundException;
import de.szut.model.Supplier;
import de.szut.repository.SupllierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupllierRepository repository;

    public SupplierService(SupllierRepository repository) {
        this.repository = repository;
    }

    public Supplier create(Supplier newSupplier) {
        return repository.save(newSupplier);
    }

    public Supplier update(Supplier supplier) {
        Supplier updatedSupplier = readById(supplier.getSid());
        updatedSupplier.setName(supplier.getName());
        updatedSupplier.getContact().setStreet(supplier.getContact().getStreet());
        updatedSupplier.getContact().setPostcode(supplier.getContact().getPostcode());
        updatedSupplier.getContact().setCity(supplier.getContact().getCity());
        updatedSupplier.getContact().setPhone(supplier.getContact().getPhone());
        updatedSupplier = this.repository.save(updatedSupplier);
        return updatedSupplier;
    }

    public List<Supplier> readAll() {
        return repository.findAll();
    }

    public Supplier readById(long id) {
        Optional<Supplier> oSupplier = repository.findById(id);
        if (oSupplier.isEmpty()) {
            throw new ResourceNotFoundException("Supplier not found on id = " + id);
        }
        return oSupplier.get();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
