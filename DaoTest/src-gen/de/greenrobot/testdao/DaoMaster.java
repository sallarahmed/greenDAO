package de.greenrobot.testdao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;

import de.greenrobot.testdao.SimpleEntityDao;
import de.greenrobot.testdao.SimpleEntityNotNullDao;
import de.greenrobot.testdao.TestEntityDao;
import de.greenrobot.testdao.RelationEntityDao;

// THIS CODE IS GENERATED, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        SimpleEntityDao.createTable(db, ifNotExists);
        SimpleEntityNotNullDao.createTable(db, ifNotExists);
        TestEntityDao.createTable(db, ifNotExists);
        RelationEntityDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        SimpleEntityDao.dropTable(db, ifExists);
        SimpleEntityNotNullDao.dropTable(db, ifExists);
        TestEntityDao.dropTable(db, ifExists);
        RelationEntityDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
        
    }

    private final SimpleEntityDao simpleEntityDao;
    private final SimpleEntityNotNullDao simpleEntityNotNullDao;
    private final TestEntityDao testEntityDao;
    private final RelationEntityDao relationEntityDao;

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);

        simpleEntityDao = new SimpleEntityDao(db);
        simpleEntityNotNullDao = new SimpleEntityNotNullDao(db);
        testEntityDao = new TestEntityDao(db);
        relationEntityDao = new RelationEntityDao(db, this);

        registerDao(SimpleEntity.class, simpleEntityDao);
        registerDao(SimpleEntityNotNull.class, simpleEntityNotNullDao);
        registerDao(TestEntity.class, testEntityDao);
        registerDao(RelationEntity.class, relationEntityDao);
    }
    
    public SimpleEntityDao getSimpleEntityDao() {
        return simpleEntityDao;
    }

    public SimpleEntityNotNullDao getSimpleEntityNotNullDao() {
        return simpleEntityNotNullDao;
    }

    public TestEntityDao getTestEntityDao() {
        return testEntityDao;
    }

    public RelationEntityDao getRelationEntityDao() {
        return relationEntityDao;
    }

}
