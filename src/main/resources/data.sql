CREATE TABLE DIAGNOSIS (
                           ID integer primary key,
                           NAME varchar(50)
);

CREATE TABLE WARDS (
                       ID integer primary key,
                       NAME varchar(50),
                       MAX_COUNT integer
);

CREATE TABLE PEOPLE (
                        ID integer primary key,
                        FIRST_NAME varchar(20),
                        LAST_NAME varchar(20),
                        PATHER_NAME varchar(20),
                        DIAGNOSIS_ID integer references DIAGNOSIS(ID),
                        WARD_ID integer references WARDS(ID)
);