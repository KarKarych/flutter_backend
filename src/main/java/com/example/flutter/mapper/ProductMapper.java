package com.example.flutter.mapper;

import com.example.flutter.entity.Product;
import com.example.flutter.entity.enumeration.SizeType;
import com.example.flutter.model.get.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public abstract class ProductMapper {

    @Value("${minio.url}")
    protected String minioUrl;

    @Value("${minio.product-bucket}")
    protected String productBucket;

    @Mapping(target = "sizes", source = "sizes", qualifiedByName = "sizesToModel")
    @Mapping(target = "pictureUrls", source = "pictureUrls", qualifiedByName = "pictureUrlsToModel")
    public abstract ProductModel toModel(Product product);

    @Named("pictureUrlsToModel")
    List<String> generatePictureUrlsToModel(List<String> pictureUrls) {
        return pictureUrls.stream()
                .sorted()
                .map(this::createUrl)
                .toList();
    }

    private String createUrl(String name) {
        return "%s/%s/%s".formatted(minioUrl, productBucket, name);
    }

    @Named("sizesToModel")
    List<SizeType> generateSizesToModel(List<Integer> sizesShort) {
        return sizesShort.stream()
                .sorted()
                .map(SizeType::getValueFromId)
                .toList();
    }
}
