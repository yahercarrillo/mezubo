CREATE TABLE TBL_MZB_ROULETTE_BETS(
   id          uuid           NOT NULL DEFAULT uuid_generate_v4(),
   id_roulette uuid     NOT NULL,
   numbergame  int    NOT NULL,
   colorgame   VARCHAR(60)      NOT NULL,
   money   FLOAT      NOT NULL,
   usergame    uuid ,
   UNIQUE(numbergame),
   PRIMARY KEY (id)
);
