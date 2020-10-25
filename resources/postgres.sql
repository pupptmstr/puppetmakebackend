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

INSERT INTO projects(project_name, description, logo_img_link, tech_specs, genres, status)
VALUES ('Sora`s story', 'Моя первая игра', 'localhost:8080/static/project-sora-logo.png', 'Минимальные системные требования 30 кадров в секунду:

    Процессор: Intel Core 2 Duo E8400
    Видеокарта: Intel HD 3000
    Оперативная память: 4 ГБ
    Операционная система: Windows 7/8/10 64-разрядная', ['Immersive sim', 'Shooter'], false)