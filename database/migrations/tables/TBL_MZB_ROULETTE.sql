CREATE TABLE TBL_MZB_ROULETTE(
   id          uuid           NOT NULL DEFAULT uuid_generate_v4(),
   code     VARCHAR(60)     NOT NULL,
   description    VARCHAR(60)    NOT NULL,
   datecreate  TIMESTAMP      NOT NULL,
   dateupdate  TIMESTAMP      , 
   enable      BOOLEAN        NOT NULL,
   UNIQUE(code),
   PRIMARY KEY (id)
);
