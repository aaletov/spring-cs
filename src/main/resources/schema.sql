CREATE TABLE "people" (
                          "id" SERIAL PRIMARY KEY,
                          "first_name" varchar(20),
                          "last_name" varchar(20),
                          "pather_name" varchar(20),
                          "ward_id" int,
                          "diagnosis_id" int
);

CREATE TABLE "ward" (
                        "id" SERIAL PRIMARY KEY,
                        "name" varchar(50)
);

CREATE TABLE "diagnosis" (
                             "id" SERIAL PRIMARY KEY,
                             "name" varchar(20),
                             "max_count" int
);

ALTER TABLE "people" ADD FOREIGN KEY ("ward_id") REFERENCES "ward" ("id");

ALTER TABLE "people" ADD FOREIGN KEY ("diagnosis_id") REFERENCES "diagnosis" ("id");
