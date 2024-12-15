CREATE TABLE helicopters
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    model   VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parts
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    helicopter_id INT          NOT NULL,
    part_name     VARCHAR(255) NOT NULL,
    part_type     VARCHAR(255),
    FOREIGN KEY (helicopter_id) REFERENCES helicopters (id)
);