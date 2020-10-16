CREATE TABLE teammates
(
    id              SERIAL PRIMARY KEY,
    first_name            VARCHAR(255)        NOT NULL,
    surname         VARCHAR(255)        NOT NULL,
    nickname        VARCHAR(255) UNIQUE NOT NULL,
    hired_at        TIMESTAMP           NOT NULL,
    global_role     VARCHAR(255)        NOT NULL,
    curr_role       VARCHAR(255),  -- Нынешняя роль в команде. Может быть нулевой, если человек не работает в команде на постоянке
    main_photo_link VARCHAR(255)        NOT NULL,
    description     TEXT                NOT NULL,
    social_links    VARCHAR(255)[]      NOT NULL,
    photos          VARCHAR(255)[] -- Массив ссылок на фотки
);

CREATE TABLE news
(
    id              SERIAL PRIMARY KEY,
    header          VARCHAR(255) NOT NULL UNIQUE,
    main_image_link VARCHAR(255) NOT NULL,
    content         TEXT         NOT NULL,
    create_at       TIMESTAMP    NOT NULL,
    delete_at       TIMESTAMP
);

CREATE TABLE projects
(
    id            SERIAL PRIMARY KEY,
    project_name          VARCHAR(255) UNIQUE NOT NULL,
    description   TEXT                NOT NULL,
    logo_img_link VARCHAR(255)        NOT NULL,
    tech_specs    TEXT                NOT NULL,
    genres        VARCHAR(100)[]      NOT NULL,
    status        BOOLEAN             NOT NULL
);
