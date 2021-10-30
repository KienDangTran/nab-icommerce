DROP TABLE IF EXISTS PUBLIC.order_event;

CREATE TABLE IF NOT EXISTS PUBLIC.order_event (
    id              LONG AUTO_INCREMENT PRIMARY KEY,
    content         VARCHAR2(4096) NOT NULL,
    sent            BOOLEAN        NOT NULL,
    event_timestamp DATETIME       NOT NULL,
    event_type      VARCHAR2(128)  NOT NULL
);