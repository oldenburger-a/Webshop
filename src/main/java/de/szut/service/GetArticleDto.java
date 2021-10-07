package de.szut.service;

import lombok.Data;

@Data
public class GetArticleDto {
    private Long aid;
    private String designation;
    private Double price;
}
