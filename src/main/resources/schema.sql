CREATE TABLE catch_log (
    id BIGINT PRIMARY KEY,
    species VARCHAR(255),
    date_time TIMESTAMP,
    location VARCHAR(255),
    weight DOUBLE,
    bait_used VARCHAR(255),
    weather_description VARCHAR(255),
    weather_temperature DOUBLE,
    weather_wind_speed DOUBLE,
    weather_pressure INT,
    weather_moon_phase DOUBLE
);