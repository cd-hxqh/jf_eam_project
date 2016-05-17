package com.jf_eam_project.Dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Udreport;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/28.
 * 故障缺陷工单
 */
public class UdreportDao {

    private static final String TAG = "UdreportDao";
    private Context context;
    private Dao<Udreport, Integer> udreportDao;
    private DatabaseHelper helper;

    public UdreportDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            udreportDao = helper.getDao(Udreport.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增
     */
    public void insert(Udreport udreport) {
        try {
            udreportDao.createOrUpdate(udreport);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void create(final List<Udreport> list) {
        try {
            deleteList(list);
            udreportDao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udreport udreport : list) {
                        Log.i(TAG, "insponum=" + udreport.reportnum);
                        deleteInsponum(udreport.reportnum);
                        udreportDao.createOrUpdate(udreport);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**更新提报单**/
    public void update(Udreport udreport) {
        try {
            udreportDao.update(udreport);
        } catch (SQLException e) {
            Log.i(TAG,"this is SQLException");
            e.printStackTrace();
        }
    }



    /**
     * @param list
     */
    public void deleteList(final List<Udreport> list) {
        try {
            udreportDao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udreport udreport : list) {
                        udreportDao.delete(udreport);
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
    public List<Udreport> queryForAll() {
        try {
            return udreportDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有的数据
     */
    public void deleteall() {
        try {
            udreportDao.delete(udreportDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据编号删除信息*
     */
    public void deleteInsponum(String reportnum) {
        try {

            List<Udreport> list = udreportDao.queryBuilder().where().eq("reportnum", reportnum).query();

            udreportDao.delete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据巡检编号查询Udreport信息
     *
     * @param reportnum
     * @return
     */
    public List<Udreport> queryByNum(String reportnum) {
        try {
            return udreportDao.queryBuilder().where().like("reportnum", reportnum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据描述查询udinspo信息*
     */
    public List<Udreport> queryByDesc(String desc) {
        List<Udreport> list = null;
        try {
            list = udreportDao.queryBuilder().where().like("description", "%" + desc + "%").query();
            Log.i(TAG, "list size=" + list.size());
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param udreport
     * @return
     */
    public boolean isexit(Udreport udreport) {
        try {
            List<Udreport> workOrderList = udreportDao.queryBuilder().where().eq("reportnum", udreport.reportnum).query();
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
