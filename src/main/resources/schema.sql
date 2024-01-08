CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "user"
(
    id            UUID PRIMARY KEY        DEFAULT uuid_generate_v4(),
    login         VARCHAR UNIQUE NOT NULL,
    email         VARCHAR UNIQUE NOT NULL,
    last_name     VARCHAR        NOT NULL,
    first_name    VARCHAR        NOT NULL,
    password_hash VARCHAR        NOT NULL,
    picture_url   VARCHAR        NOT NULL,
    balance       INT            NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "product"
(
    id             UUID PRIMARY KEY   DEFAULT uuid_generate_v4(),
    name           VARCHAR   NOT NULL,
    description    VARCHAR   NOT NULL,
    picture_urls   VARCHAR[] NOT NULL DEFAULT '{}',
    price          INT       NOT NULL DEFAULT 0,
    rating_percent INT       NOT NULL,
    rating_number  INT       NOT NULL,
    category       SMALLINT  NOT NULL,
    sizes          INT[]     NOT NULL DEFAULT '{}'
);

CREATE TABLE IF NOT EXISTS "article"
(
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR     NOT NULL,
    text        TEXT        NOT NULL,
    picture_url VARCHAR     NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS "article_category"
(
    article_id  UUID     NOT NULL REFERENCES "article",
    category_id SMALLINT NOT NULL,
    PRIMARY KEY (article_id, category_id)
);

CREATE TABLE IF NOT EXISTS "wishlist"
(
    product_id UUID NOT NULL REFERENCES "product",
    user_id    UUID NOT NULL REFERENCES "user",
    PRIMARY KEY (product_id, user_id)
);

CREATE TABLE IF NOT EXISTS "bucket"
(
    product_id UUID NOT NULL REFERENCES "product",
    user_id    UUID NOT NULL REFERENCES "user",
    PRIMARY KEY (product_id, user_id)
);

CREATE TABLE IF NOT EXISTS "order"
(
    id         UUID PRIMARY KEY     DEFAULT uuid_generate_v4(),
    status     SMALLINT    NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL,
    user_id    UUID        NOT NULL REFERENCES "user"
);

CREATE TABLE IF NOT EXISTS "order_product"
(
    order_id   UUID NOT NULL REFERENCES "order",
    product_id UUID NOT NULL REFERENCES "product",
    PRIMARY KEY (order_id, product_id)
);

CREATE TABLE IF NOT EXISTS "transaction"
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id    UUID        NOT NULL REFERENCES "user",
    amount     INT         NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    type       SMALLINT    NOT NULL,
    target     VARCHAR     NOT NULL
);