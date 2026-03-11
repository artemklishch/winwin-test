CREATE TABLE processing_log
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id     UUID,
    input_text  TEXT,
    output_text TEXT,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW()
);