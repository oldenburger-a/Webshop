package de.szut.service;

import lombok.Data;

import java.util.Set;
@Data
public class GetAllArticlesBySupplierIdDto {
    private Long supplierId;
    private String name;
    private Set<GetArticleDto> articleDTOSet;
}
