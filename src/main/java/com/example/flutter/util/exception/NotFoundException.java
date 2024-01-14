package com.example.flutter.util.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class NotFoundException extends RuntimeException {

    protected final Code code;

    protected NotFoundException(Code code, String msg) {
        this(code, null, msg);
    }

    protected NotFoundException(Code code, Throwable e, String msg) {
        super(msg, e);
        this.code = code;
    }

    @Getter
    public enum Code {

        USER_NOT_FOUND("User not found"),
        PRODUCT_NOT_FOUND("Product not found"),
        PRODUCT_IN_BUCKET_NOT_FOUND("Product in bucket not found"),
        ARTICLE_NOT_FOUND("Article not found"),
        SIZE_IN_PRODUCT_NOT_FOUND("Product does not have specified size"),
        ;

        /**
         * Pattern {0} - code description, {1} - message
         */
        private static final String EXCEPTION_MESSAGE_PATTERN = "{0}: {1}";

        private final String description;

        Code(String description) {
            this.description = "%s (%s)".formatted(name(), description);
        }

        public NotFoundException get() {
            return new NotFoundException(this, description);
        }

        public NotFoundException get(String currentLogin, String additionalInfo) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, "currentLogin = %s, %s".formatted(currentLogin, additionalInfo));
            return new NotFoundException(this, errorMessage);
        }

        public NotFoundException get(String additionalInfo) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, additionalInfo);
            return new NotFoundException(this, errorMessage);
        }

        public NotFoundException get(Throwable e) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, e.getMessage());
            return new NotFoundException(this, e, errorMessage);
        }

        public NotFoundException get(Throwable e, String msg) {
            String errorMessage = MessageFormat.format(EXCEPTION_MESSAGE_PATTERN, this.description, msg);
            return new NotFoundException(this, e, errorMessage);
        }
    }
}
