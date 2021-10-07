package de.szut.service;

import de.szut.model.Article;
import de.szut.model.Contact;
import de.szut.model.Supplier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MappingService {

    public Supplier mapAddSupplierDtoToSupplier(AddSupplierDto dto) {
        Supplier newSupplier = new Supplier();
        newSupplier.setName(dto.getName());
        Contact newContact = new Contact();
        newContact.setStreet(dto.getStreet());
        newContact.setPostcode(dto.getPostcode());
        newContact.setCity(dto.getCity());
        newContact.setPhone(dto.getPhone());
        newSupplier.setContact(newContact);
        newContact.setSupplier(newSupplier);
        return newSupplier;
    }
    public GetSupplierDto mapSupplierToGetSupplierDto(Supplier supplier){

        GetSupplierDto getSupplierDto = new GetSupplierDto();
        getSupplierDto.setName(supplier.getName());
        Contact contact = supplier.getContact();
        getSupplierDto.setPhone(contact.getPhone());
        getSupplierDto.setCity(contact.getCity());
        getSupplierDto.setPostcode(contact.getPostcode());
        getSupplierDto.setStreet(contact.getStreet());
        getSupplierDto.setSid(supplier.getSid());
        return getSupplierDto;
    }
    public GetAllArticlesBySupplierIdDto mapSupplierToGetAllArticlesBySupplierIdDto(Supplier supplier){
        GetAllArticlesBySupplierIdDto getAllArticlesBySupplierIdDto = new GetAllArticlesBySupplierIdDto();
        getAllArticlesBySupplierIdDto.setSupplierId(supplier.getSid());
        getAllArticlesBySupplierIdDto.setName(supplier.getName());

        Set<GetArticleDto> set = new HashSet<>();

        for (Article article: supplier.getArticles()) {
            GetArticleDto articleDto = new GetArticleDto();
            articleDto.setAid(article.getAid());
            articleDto.setDesignation(article.getDesignation());
            articleDto.setPrice(article.getPrice());
            set.add(articleDto);
        }
        getAllArticlesBySupplierIdDto.setArticleDTOSet(set);
        return getAllArticlesBySupplierIdDto;
    }
}
