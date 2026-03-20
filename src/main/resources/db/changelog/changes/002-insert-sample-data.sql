-- liquibase formatted sql

-- changeset mateusz:10
INSERT INTO sports (name) VALUES ('Football');

-- changeset mateusz:11
INSERT INTO competitions (origin_competition_id, origin_competition_name, _sport_id)
VALUES ('afc-champions-league', 'AFC Champions League', 1);

-- changeset mateusz:12
INSERT INTO stages (stage_code, name, ordering) VALUES
                                                    ('ROUND OF 16', 'ROUND OF 16', 4),
                                                    ('FINAL', 'FINAL', 7);

-- changeset mateusz:13
INSERT INTO teams (name, official_name, slug, abbreviation, team_country_code) VALUES
                                                                                   ('Al Shabab',     'Al Shabab FC',          'al-shabab-fc',        'SHA', 'KSA'),
                                                                                   ('Nasaf',         'FC Nasaf',              'fc-nasaf-qarshi',     'NAS', 'UZB'),
                                                                                   ('Al Hilal',      'Al Hilal Saudi FC',     'al-hilal-saudi-fc',   'HIL', 'KSA'),
                                                                                   ('Shabab Al Ahli','SHABAB AL AHLI DUBAI',  'shabab-al-ahli-club', 'SAH', 'UAE'),
                                                                                   ('Al Duhail',     'AL DUHAIL SC',          'al-duhail-sc',        'DUH', 'QAT'),
                                                                                   ('Al Rayyan',     'AL RAYYAN SC',          'al-rayyan-sc',        'RYN', 'QAT'),
                                                                                   ('Al Faisaly',    'Al Faisaly FC',         'al-faisaly-fc',       'FAI', 'KSA'),
                                                                                   ('Foolad',        'FOOLAD KHOUZESTAN FC',  'foolad-khuzestan-fc', 'FLD', 'IRN'),
                                                                                   ('Urawa Reds',    'Urawa Red Diamonds',    'urawa-red-diamonds',  'RED', 'JPN');

-- changeset mateusz:14
-- Event 1: Al Shabab vs Nasaf, played, 2024-01-03
INSERT INTO events (season_year, status, date_venue, time_venue_utc, event_group, _sport_id, _competition_id, _stage_id, _stadium_id, _home_team_id, _away_team_id)
VALUES (2024, 'played', '2024-01-03', '00:00:00', null, 1, 1, 1, null, 1, 2);

-- changeset mateusz:15
-- Event 2: Al Hilal vs Shabab Al Ahli, scheduled, 2024-01-03
INSERT INTO events (season_year, status, date_venue, time_venue_utc, event_group, _sport_id, _competition_id, _stage_id, _stadium_id, _home_team_id, _away_team_id)
VALUES (2024, 'scheduled', '2024-01-03', '16:00:00', null, 1, 1, 1, null, 3, 4);

-- changeset mateusz:16
-- Event 3: Al Duhail vs Al Rayyan, scheduled, 2024-01-04
INSERT INTO events (season_year, status, date_venue, time_venue_utc, event_group, _sport_id, _competition_id, _stage_id, _stadium_id, _home_team_id, _away_team_id)
VALUES (2024, 'scheduled', '2024-01-04', '15:25:00', null, 1, 1, 1, null, 5, 6);

-- changeset mateusz:17
-- Event 4: Al Faisaly vs Foolad, scheduled, 2024-01-04
INSERT INTO events (season_year, status, date_venue, time_venue_utc, event_group, _sport_id, _competition_id, _stage_id, _stadium_id, _home_team_id, _away_team_id)
VALUES (2024, 'scheduled', '2024-01-04', '08:00:00', null, 1, 1, 1, null, 7, 8);

-- changeset mateusz:18
-- Event 5: null vs Urawa Reds, scheduled, FINAL, 2024-01-19
INSERT INTO events (season_year, status, date_venue, time_venue_utc, event_group, _sport_id, _competition_id, _stage_id, _stadium_id, _home_team_id, _away_team_id)
VALUES (2024, 'scheduled', '2024-01-19', '00:00:00', null, 1, 1, 2, null, null, 9);

-- changeset mateusz:19
-- Event 1: played, wynik 1:2, winner Nasaf
INSERT INTO results (home_goals, away_goals, winner, message, score_by_periods, _winner_id, _event_id)
VALUES (1, 2, 'Nasaf', null, null, 2, 1);

-- changeset mateusz:20
-- Event 2: scheduled, wynik 0:0, brak zwycięzcy
INSERT INTO results (home_goals, away_goals, winner, message, score_by_periods, _winner_id, _event_id)
VALUES (0, 0, null, null, null, null, 2);

-- changeset mateusz:21
-- Event 3: scheduled, wynik 0:0, brak zwycięzcy
INSERT INTO results (home_goals, away_goals, winner, message, score_by_periods, _winner_id, _event_id)
VALUES (0, 0, null, null, null, null, 3);

-- changeset mateusz:22
-- Event 4: scheduled, wynik 0:0, brak zwycięzcy
INSERT INTO results (home_goals, away_goals, winner, message, score_by_periods, _winner_id, _event_id)
VALUES (0, 0, null, null, null, null, 4);

-- Event 5: result null w JSON - brak INSERT
