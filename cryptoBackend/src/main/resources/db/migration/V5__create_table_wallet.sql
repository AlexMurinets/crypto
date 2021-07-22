CREATE TABLE wallet (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    amount NUMERIC(19,2) NOT NULL,
    currency_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (currency_id)
        REFERENCES currency (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);