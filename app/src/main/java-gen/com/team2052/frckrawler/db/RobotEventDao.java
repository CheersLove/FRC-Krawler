package com.team2052.frckrawler.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.team2052.frckrawler.db.RobotEvent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ROBOT_EVENT.
*/
public class RobotEventDao extends AbstractDao<RobotEvent, Long> {

    public static final String TABLENAME = "ROBOT_EVENT";

    /**
     * Properties of entity RobotEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Robot_id = new Property(1, long.class, "robot_id", false, "ROBOT_ID");
        public final static Property Event_id = new Property(2, long.class, "event_id", false, "EVENT_ID");
        public final static Property Data = new Property(3, String.class, "data", false, "DATA");
    };

    private Query<RobotEvent> event_RobotEventListQuery;
    private Query<RobotEvent> robot_RobotEventListQuery;

    public RobotEventDao(DaoConfig config) {
        super(config);
    }
    
    public RobotEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ROBOT_EVENT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "'ROBOT_ID' INTEGER NOT NULL ," + // 1: robot_id
                "'EVENT_ID' INTEGER NOT NULL ," + // 2: event_id
                "'DATA' TEXT);"); // 3: data
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ROBOT_EVENT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RobotEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getRobot_id());
        stmt.bindLong(3, entity.getEvent_id());
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(4, data);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RobotEvent readEntity(Cursor cursor, int offset) {
        RobotEvent entity = new RobotEvent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // robot_id
            cursor.getLong(offset + 2), // event_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // data
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RobotEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRobot_id(cursor.getLong(offset + 1));
        entity.setEvent_id(cursor.getLong(offset + 2));
        entity.setData(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(RobotEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(RobotEvent entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "robotEventList" to-many relationship of Event. */
    public List<RobotEvent> _queryEvent_RobotEventList(long event_id) {
        synchronized (this) {
            if (event_RobotEventListQuery == null) {
                QueryBuilder<RobotEvent> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Event_id.eq(null));
                event_RobotEventListQuery = queryBuilder.build();
            }
        }
        Query<RobotEvent> query = event_RobotEventListQuery.forCurrentThread();
        query.setParameter(0, event_id);
        return query.list();
    }

    /** Internal query to resolve the "robotEventList" to-many relationship of Robot. */
    public List<RobotEvent> _queryRobot_RobotEventList(long robot_id) {
        synchronized (this) {
            if (robot_RobotEventListQuery == null) {
                QueryBuilder<RobotEvent> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Robot_id.eq(null));
                robot_RobotEventListQuery = queryBuilder.build();
            }
        }
        Query<RobotEvent> query = robot_RobotEventListQuery.forCurrentThread();
        query.setParameter(0, robot_id);
        return query.list();
    }

}
