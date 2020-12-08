CREATE TABLE TBL_MZB_USERS(
   id          uuid           NOT NULL DEFAULT uuid_generate_v4(),
   nickname     VARCHAR(60)     NOT NULL,
   description    VARCHAR(60)    NOT NULL,
   credit       FLOAT    NOT NULL,
   datecreate  TIMESTAMP      NOT NULL,
   dateupdate  TIMESTAMP      , 
   enable      BOOLEAN        NOT NULL,
   UNIQUE(nickname),
   PRIMARY KEY (id)
);
