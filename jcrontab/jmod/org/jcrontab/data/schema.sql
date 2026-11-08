-- The events table, as Jcrontab defines it in etc/create.sql:
-- https://github.com/iolalla/jcrontab/blob/master/etc/create.sql
--
-- Only this table is kept. It is the one the queries in this package use,
-- and the original file's MySQL '#' comments do not parse as standard SQL.
CREATE TABLE events (
id INTEGER NOT NULL PRIMARY KEY,
second VARCHAR(64) DEFAULT '0',
minute VARCHAR(64) DEFAULT '*',
hour VARCHAR(64) DEFAULT '*',
dayofmonth VARCHAR(64) DEFAULT '*',
month VARCHAR(64) DEFAULT '*',
dayofweek VARCHAR(64) DEFAULT '*',
year VARCHAR(64) DEFAULT '*',
task VARCHAR(255),
extrainfo VARCHAR(255),
businessDays VARCHAR(6) DEFAULT 'true'
);
