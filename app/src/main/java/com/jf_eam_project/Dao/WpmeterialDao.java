package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Wpmaterial;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 *
 */
public class WpmeterialDao {
    private Context context;
    private Dao<Wpmaterial, Integer> WpmaterialDaoOpe;
    private DatabaseHelper helper;

    public WpmeterialDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WpmaterialDaoOpe = helper.getDao(Wpmaterial.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储任务
     * @param list
     */
    public void create(final List<Wpmaterial> list) {
        try {
            deleteall();
            WpmaterialDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Wpmaterial woactivity : list) {
                        WpmaterialDaoOpe.createOrUpdate(woactivity);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Wpmaterial wpmaterial) {
        try
        {
            WpmaterialDaoOpe.createOrUpdate(wpmaterial);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Wpmaterial> queryForAll(){
        try {
            return WpmaterialDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id
     */
    public List<Wpmaterial> queryByWonum(int id) {
        try {
            return WpmaterialDaoOpe.queryBuilder().where().eq("belongid", id ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteall(){
        try {
            WpmaterialDaoOpe.delete(WpmaterialDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteByWonum(int wonum){
        try {
            WpmaterialDaoOpe.delete(WpmaterialDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Wpmaterial SearchByNum(int id){
        try {
            return WpmaterialDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public boolean isexit(Wpmaterial wplabor){
//        try {
//            List<Wpmaterial>workOrderList = WpmaterialDaoOpe.queryBuilder().where().eq("wonum",wplabor.wonum)
//                    .and().eq("laborcode",wplabor.laborcode).query();
//            if(workOrderList.size()>0){
//                return true;
//            }else {
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
