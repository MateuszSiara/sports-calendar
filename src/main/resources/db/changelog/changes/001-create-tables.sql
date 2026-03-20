-- liquibase formatted sql

-- changeset mateusz:1
CREATE TABLE sports (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL
);

-- changeset mateusz:2
CREATE TABLE competitions (
                              id SERIAL PRIMARY KEY,
                              origin_competition_id VARCHAR(100) NOT NULL,
                              origin_competition_name VARCHAR(100) NOT NULL,
                              _sport_id INTEGER NOT NULL,
                              CONSTRAINT fk_competition_sport FOREIGN KEY (_sport_id) REFERENCES sports(id)
);

-- changeset mateusz:3
CREATE TABLE stadiums (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          city VARCHAR(100),
                          capacity INTEGER
);

-- changeset mateusz:4
CREATE TABLE stages (
                        id SERIAL PRIMARY KEY,
                        stage_code VARCHAR(100) NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        ordering INTEGER NOT NULL
);

-- changeset mateusz:5
CREATE TABLE teams (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       official_name VARCHAR(100) NOT NULL,
                       slug VARCHAR(100) NOT NULL,
                       abbreviation VARCHAR(10) NOT NULL,
                       team_country_code VARCHAR(3) NOT NULL
);

-- changeset mateusz:6
CREATE TABLE events (
                        id SERIAL PRIMARY KEY,
                        season_year INTEGER NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        date_venue DATE NOT NULL,
                        time_venue_utc TIME NOT NULL,
                        event_group VARCHAR(100),
                        _sport_id INTEGER NOT NULL,
                        _competition_id INTEGER NOT NULL,
                        _stage_id INTEGER NOT NULL,
                        _stadium_id INTEGER,
                        _home_team_id INTEGER,
                        _away_team_id INTEGER,
                        CONSTRAINT fk_event_sport FOREIGN KEY (_sport_id) REFERENCES sports(id),
                        CONSTRAINT fk_event_competition FOREIGN KEY (_competition_id) REFERENCES competitions(id),
                        CONSTRAINT fk_event_stage FOREIGN KEY (_stage_id) REFERENCES stages(id),
                        CONSTRAINT fk_event_stadium FOREIGN KEY (_stadium_id) REFERENCES stadiums(id),
                        CONSTRAINT fk_event_home_team FOREIGN KEY (_home_team_id) REFERENCES teams(id),
                        CONSTRAINT fk_event_away_team FOREIGN KEY (_away_team_id) REFERENCES teams(id)
);

-- changeset mateusz:7
CREATE TABLE results (
                         id SERIAL PRIMARY KEY,
                         home_goals INTEGER NOT NULL,
                         away_goals INTEGER NOT NULL,
                         winner VARCHAR(50),
                         message VARCHAR(255),
                         score_by_periods JSON,
                         _winner_id INTEGER,
                         _event_id INTEGER NOT NULL UNIQUE,
                         CONSTRAINT fk_result_winner FOREIGN KEY (_winner_id) REFERENCES teams(id),
                         CONSTRAINT fk_result_event FOREIGN KEY (_event_id) REFERENCES events(id)
);

-- changeset mateusz:8
CREATE TABLE goals (
                       id SERIAL PRIMARY KEY,
                       minute INTEGER,
                       _event_id INTEGER NOT NULL,
                       _team_id INTEGER,
                       CONSTRAINT fk_goal_event FOREIGN KEY (_event_id) REFERENCES events(id),
                       CONSTRAINT fk_goal_team FOREIGN KEY (_team_id) REFERENCES teams(id)
);

-- changeset mateusz:9
CREATE TABLE cards (
                       id SERIAL PRIMARY KEY,
                       card_type VARCHAR(50) NOT NULL,
                       minute INTEGER,
                       _event_id INTEGER NOT NULL,
                       _team_id INTEGER,
                       CONSTRAINT fk_card_event FOREIGN KEY (_event_id) REFERENCES events(id),
                       CONSTRAINT fk_card_team FOREIGN KEY (_team_id) REFERENCES teams(id)
);