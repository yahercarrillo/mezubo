'use strict';

const fs = require('fs');
const path = require('path');

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

exports.up = async function(db) {
  var filePath = path.join(__dirname, 'relations', 'foreign_keys.sql');
  let sql = fs.readFileSync(filePath, 'utf8');
  return db.runSql(sql);
};

exports.down = function(db) {
  console.log('Delete constrains')
  return db.runSql(`
    DO $$ DECLARE
      r RECORD;
    BEGIN
        FOR r IN (SELECT * FROM information_schema.table_constraints WHERE table_schema = 'public' AND constraint_type = 'FOREIGN KEY') LOOP
            EXECUTE 'ALTER TABLE ' || quote_ident(r.table_name) || ' DROP CONSTRAINT ' || r.constraint_name;
        END LOOP;
    END $$;
  `);
};

exports._meta = {
  "version": 1
};