CREATE TABLE transactions (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    amount NUMERIC(19,2) NOT NULL,
    receiver_id BIGINT NOT NULL,
    sender_id BIGINT NOT NULL,
    time TIMESTAMP NOT NULL,
    FOREIGN KEY (receiver_id)
        REFERENCES wallet (id),
    FOREIGN KEY (sender_id)
        REFERENCES wallet (id)
)