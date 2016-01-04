package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Labor;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/31.
 *人员
 */
public class LaborDao {
    private Context context;
    private Dao<Labor, Integer> LaborDaoOpe;
    private DatabaseHelper helper;

    public LaborDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            LaborDaoOpe = helper.getDao(Labor.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新位置信息
     * @param list
     */
    public void create(final List<Labor> list) {
            try {
                deleteall();
                LaborDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Labor labor : list) {
                            LaborDaoOpe.createOrUpdate(labor);
                        }
                        return null;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    /**
     *
     * @return
     */
    public List<Labor> queryForAll(){
        try {
            return LaborDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public void deleteall(){
        try {
            LaborDaoOpe.delete(LaborDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param laborcode
     * @return
     */
    public Labor queryByLabor(String laborcode){
        try {
            return LaborDaoOpe.queryBuilder().where().eq("laborcode",laborcode).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param labor
     * @return
     */
    public boolean isexit(Labor labor){
        try {
            List<Labor>workOrderList = LaborDaoOpe.queryBuilder().where().eq("location",labor.laborcode).query();
            if(workOrderList.size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
