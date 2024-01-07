package com.example.flutter.mapper;

import com.example.flutter.entity.Product;
import com.example.flutter.entity.enumeration.SizeType;
import com.example.flutter.model.get.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Mapping(target = "sizes", source = "sizes", qualifiedByName = "sizesToModel")
    ProductModel toModel(Product product);

    @Named("sizesToModel")
    default List<SizeType> generateSizesToModel(List<Integer> sizesShort) {
        return sizesShort.stream()
                .map(SizeType::getValueFromId)
                .toList();
    }
}
