package com.jf_eam_project.Dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/28.
 * 巡检单
 */
public class UdinspoDao {

    private static final String TAG="UdinspoDao";
    private Context context;
    private Dao<Udinspo, Integer> udinspoDaoOpe;
    private DatabaseHelper helper;

    public UdinspoDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            udinspoDaoOpe = helper.getDao(Udinspo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增
     */
    public void insert(Udinspo udinspo) {
        try {
            udinspoDaoOpe.createOrUpdate(udinspo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void create(final List<Udinspo> list) {
        try {
            deleteall();
            udinspoDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinspo udinspo : list) {
                        udinspoDaoOpe.createOrUpdate(udinspo);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void deleteList(final List<Udinspo> list) {
        try {
            udinspoDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinspo udinspo : list) {
                        udinspoDaoOpe.delete(udinspo);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return
     */
    public List<Udinspo> queryForAll() {
        try {
            return udinspoDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public void deleteall() {
        try {
            udinspoDaoOpe.delete(udinspoDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据巡检编号查询Udinspo信息
     *
     * @param insponum
     * @return
     */
    public List<Udinspo> queryByNum(String insponum) {
        try {
            return udinspoDaoOpe.queryBuilder().where().like("insponum", insponum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据描述查询udinspo信息*
     */
    public List<Udinspo> queryByDesc(String desc) {
        List<Udinspo> list = null;
        try {
            list = udinspoDaoOpe.queryBuilder().where().like("description", "%" +desc+"%").query();
            Log.i(TAG,"list size="+list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param udinspo
     * @return
     */
    public boolean isexit(Udinspo udinspo) {
        try {
            List<Udinspo> workOrderList = udinspoDaoOpe.queryBuilder().where().eq("insponum", udinspo.insponum).query();
            if (workOrderList.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
