package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Assets;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/28.
 * 巡检备件
 */
public class UdinspoAssetDao {
    private Context context;
    private Dao<Udinspoasset, Integer> udinspoassetDaoOpe;
    private DatabaseHelper helper;

    public UdinspoAssetDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            udinspoassetDaoOpe = helper.getDao(Udinspoasset.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增
     */
    public void insert(Udinspoasset udinspoasset) {
        try {
            udinspoassetDaoOpe.createOrUpdate(udinspoasset);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void create(final List<Udinspoasset> list) {
        try {
            udinspoassetDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinspoasset udinspoasset : list) {
                        deleteudInspoassetnum(udinspoasset.udinspoassetnum);
                        udinspoassetDaoOpe.createOrUpdate(udinspoasset);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据编号删除信息*
     */
    public void deleteudInspoassetnum(String udinspoassetnum) {
        try {

            List<Udinspoasset> list = udinspoassetDaoOpe.queryBuilder().where().eq("udinspoassetnum", udinspoassetnum).query();

            udinspoassetDaoOpe.delete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return
     */
    public List<Udinspoasset> queryForAll() {
        try {
            return udinspoassetDaoOpe.queryForAll();
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
            udinspoassetDaoOpe.delete(udinspoassetDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param insponum
     * @return
     */
    public List<Udinspoasset> queryByNum(String insponum) {
        try {
            return udinspoassetDaoOpe.queryBuilder().where().like("insponum", insponum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param udinspoasset
     * @return
     */
    public boolean isexit(Udinspoasset udinspoasset) {
        try {
            List<Udinspoasset> workOrderList = udinspoassetDaoOpe.queryBuilder().where().eq("udinspoassetnum", udinspoasset.udinspoassetnum)
                    .and().eq("description", udinspoasset.location).query();
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


    /**根据巡检单编号查询巡检备件信息**/
    public List<Udinspoasset> queryByInsponum(String insponum) {
        try {
            return udinspoassetDaoOpe.queryBuilder().where().eq("insponum", insponum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
