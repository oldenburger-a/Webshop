package de.szut.controller;

import de.szut.model.Supplier;
import de.szut.service.AddSupplierDto;
import de.szut.service.GetSupplierDto;
import de.szut.service.MappingService;
import de.szut.service.SupplierService;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("Store/supplier")
public class SupplierController {
    private MappingService mappingService;
    private SupplierService service;

    public SupplierController(MappingService mappingService, SupplierService supplierService) {
        this.mappingService = mappingService;
        this.service = supplierService;
    }

    @PostMapping
    public ResponseEntity<GetSupplierDto> createSupplier(@Valid @RequestBody final AddSupplierDto dto) {
        Supplier newSupplier = this.mappingService.mapAddSupplierDtoToSupplier(dto);
        newSupplier = this.service.create(newSupplier);
        final GetSupplierDto request = this.mappingService.mapSupplierToGetSupplierDto(newSupplier);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetSupplierDto>> findAllSuppliers() {
        List<Supplier> all = this.service.readAll();
        List<GetSupplierDto> dtoList = new LinkedList<>();
        for (Supplier supplier : all) {
            dtoList.add(this.mappingService.mapSupplierToGetSupplierDto(supplier));
        }
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSupplierDto> getSupplierById(@PathVariable final Long id) {
        final var entity = this.service.readById(id);
        final GetSupplierDto dto = this.mappingService.mapSupplierToGetSupplierDto(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
