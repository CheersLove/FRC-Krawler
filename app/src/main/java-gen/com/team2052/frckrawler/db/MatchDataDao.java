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

import com.team2052.frckrawler.db.MatchData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MATCH_DATA.
*/
public class MatchDataDao extends AbstractDao<MatchData, Long> {

    public static final String TABLENAME = "MATCH_DATA";

    /**
     * Properties of entity MatchData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Event_id = new Property(1, long.class, "event_id", false, "EVENT_ID");
        public final static Property Robot_id = new Property(2, long.class, "robot_id", false, "ROBOT_ID");
        public final static Property User_id = new Property(3, Long.class, "user_id", false, "USER_ID");
        public final static Property Game_type = new Property(4, int.class, "game_type", false, "GAME_TYPE");
        public final static Property Metric_id = new Property(5, long.class, "metric_id", false, "METRIC_ID");
        public final static Property Match_number = new Property(6, long.class, "match_number", false, "MATCH_NUMBER");
        public final static Property Data = new Property(7, String.class, "data", false, "DATA");
    };

    private Query<MatchData> event_MatchDataListQuery;
    private Query<MatchData> robot_MatchDataListQuery;
    private Query<MatchData> metric_MatchDataListQuery;
    private Query<MatchData> user_MatchDataListQuery;

    public MatchDataDao(DaoConfig config) {
        super(config);
    }
    
    public MatchDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'MATCH_DATA' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'EVENT_ID' INTEGER NOT NULL ," + // 1: event_id
                "'ROBOT_ID' INTEGER NOT NULL ," + // 2: robot_id
                "'USER_ID' INTEGER," + // 3: user_id
                "'GAME_TYPE' INTEGER NOT NULL ," + // 4: game_type
                "'METRIC_ID' INTEGER NOT NULL ," + // 5: metric_id
                "'MATCH_NUMBER' INTEGER NOT NULL ," + // 6: match_number
                "'DATA' TEXT);"); // 7: data
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MATCH_DATA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MatchData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getEvent_id());
        stmt.bindLong(3, entity.getRobot_id());
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(4, user_id);
        }
        stmt.bindLong(5, entity.getGame_type());
        stmt.bindLong(6, entity.getMetric_id());
        stmt.bindLong(7, entity.getMatch_number());
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(8, data);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MatchData readEntity(Cursor cursor, int offset) {
        MatchData entity = new MatchData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // event_id
            cursor.getLong(offset + 2), // robot_id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // user_id
            cursor.getInt(offset + 4), // game_type
            cursor.getLong(offset + 5), // metric_id
            cursor.getLong(offset + 6), // match_number
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // data
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MatchData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEvent_id(cursor.getLong(offset + 1));
        entity.setRobot_id(cursor.getLong(offset + 2));
        entity.setUser_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setGame_type(cursor.getInt(offset + 4));
        entity.setMetric_id(cursor.getLong(offset + 5));
        entity.setMatch_number(cursor.getLong(offset + 6));
        entity.setData(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(MatchData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(MatchData entity) {
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
    
    /** Internal query to resolve the "matchDataList" to-many relationship of Event. */
    public List<MatchData> _queryEvent_MatchDataList(long event_id) {
        synchronized (this) {
            if (event_MatchDataListQuery == null) {
                QueryBuilder<MatchData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Event_id.eq(null));
                event_MatchDataListQuery = queryBuilder.build();
            }
        }
        Query<MatchData> query = event_MatchDataListQuery.forCurrentThread();
        query.setParameter(0, event_id);
        return query.list();
    }

    /** Internal query to resolve the "matchDataList" to-many relationship of Robot. */
    public List<MatchData> _queryRobot_MatchDataList(long robot_id) {
        synchronized (this) {
            if (robot_MatchDataListQuery == null) {
                QueryBuilder<MatchData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Robot_id.eq(null));
                robot_MatchDataListQuery = queryBuilder.build();
            }
        }
        Query<MatchData> query = robot_MatchDataListQuery.forCurrentThread();
        query.setParameter(0, robot_id);
        return query.list();
    }

    /** Internal query to resolve the "matchDataList" to-many relationship of Metric. */
    public List<MatchData> _queryMetric_MatchDataList(long metric_id) {
        synchronized (this) {
            if (metric_MatchDataListQuery == null) {
                QueryBuilder<MatchData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Metric_id.eq(null));
                metric_MatchDataListQuery = queryBuilder.build();
            }
        }
        Query<MatchData> query = metric_MatchDataListQuery.forCurrentThread();
        query.setParameter(0, metric_id);
        return query.list();
    }

    /** Internal query to resolve the "matchDataList" to-many relationship of User. */
    public List<MatchData> _queryUser_MatchDataList(Long user_id) {
        synchronized (this) {
            if (user_MatchDataListQuery == null) {
                QueryBuilder<MatchData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.User_id.eq(null));
                user_MatchDataListQuery = queryBuilder.build();
            }
        }
        Query<MatchData> query = user_MatchDataListQuery.forCurrentThread();
        query.setParameter(0, user_id);
        return query.list();
    }

}
