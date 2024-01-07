package com.example.flutter.model.basic;

public record ContentModel<T>(T content) {

    public static <T> ContentModel<T> okContentModel(T content) {
        return new ContentModel<>(content);
    }

    public static ContentModel<String> deletedContentModel() {
        return deletedContentModel("Successfully deleted");
    }

    public static ContentModel<String> deletedContentModel(String message) {
        return new ContentModel<>(message);
    }
}
