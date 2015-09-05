-- Table: mytasklist

-- DROP TABLE mytasklist;

CREATE TABLE mytasklist
(
  activitate character(30),
  stare boolean,
  id bigserial NOT NULL,
  "userId" integer,
  CONSTRAINT mytasklist_pkey PRIMARY KEY (id)
)