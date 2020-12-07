'use strict';

const fs = require('fs');
var path = require('path');


var dbm;
var type;
var seed;

/**
  * We receive the dbmigrate dependency from dbmigrate initially.
  * This enables us to not have to rely on NODE_PATH.
  */
exports.setup = function(options, seedLink) {
  dbm = options.dbmigrate;
  type = dbm.dataType;
  seed = seedLink;
};

exports.up = async function (db) {
  let baseFolder = path.join(__dirname, 'tables')
  try {
    await db.runSql('BEGIN');
    await db.runSql('CREATE EXTENSION IF NOT EXISTS "uuid-ossp"');
    //console.log(baseFolder);
    let files = fs.readdirSync(baseFolder);
    //console.log(files);
    for (let file of files) {
      console.log(file);
      let sql = fs.readFileSync(`${baseFolder}/${file}`, 'utf8');
      await db.runSql(sql);
      console.log('Ok........');
    }
    return db.runSql('COMMIT');
  } catch (err) {
    console.error('Fail......');
    throw err;
  }
};

exports.down = function(db) {
  console.log('Iniciando Delete........');
  return db.runSql(`
    DO $$ DECLARE
      r RECORD;
    BEGIN
        FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = current_schema() AND tablename<>'migrations') LOOP
            EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
        END LOOP;
    END $$;
  `);
};

exports._meta = {
  "version": 1
};
