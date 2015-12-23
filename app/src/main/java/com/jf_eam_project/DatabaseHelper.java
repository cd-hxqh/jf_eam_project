package com.jf_eam_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;
import com.jf_eam_project.utils.DataUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by think on 2015/12/23.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {

        super(context, DataUtils.getFilePath(context), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkOrder.class);
            TableUtils.createTable(connectionSource, Wplabor.class);
            TableUtils.createTable(connectionSource, Labtrans.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, WorkOrder.class, true);
            TableUtils.dropTable(connectionSource, Wplabor.class, true);
            TableUtils.dropTable(connectionSource, Labtrans.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * ������ȡ��Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            try {
                dao = super.getDao(clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * �ͷ���Դ
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
