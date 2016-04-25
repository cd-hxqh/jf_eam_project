package com.jf_eam_project.Dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Assignment;
import com.jf_eam_project.model.Createreport;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 */
public class CreatereportDao {
    private static final String TAG="CreatereportDao";
    private Context context;
    private Dao<Createreport, Integer> createreportDaoOpe;
    private DatabaseHelper helper;

    public CreatereportDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            createreportDaoOpe = helper.getDao(Createreport.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储任务
     *
     * @param list
     */
    public void create(final List<Createreport> list) {
        try {
            deleteall();
            createreportDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Createreport createreport : list) {
                        createreportDaoOpe.createOrUpdate(createreport);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建提报单*
     */
    public void create(Createreport createreport) {
        try {
            createreportDaoOpe.create(createreport);
            Log.i(TAG, "11111");
        } catch (SQLException e) {
            Log.i(TAG,"sssssssssssssss");
            e.printStackTrace();
        }
    }

    public List<Createreport> queryForAll() {
        try {
            return createreportDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id
     */
    public List<Createreport> queryByUdinspojxxmid(String id) {
        try {
            return createreportDaoOpe.queryBuilder().where().eq("udinspojxxmid", id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteall() {
        try {
            createreportDaoOpe.delete(createreportDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteByWonum(int wonum) {
        try {
            createreportDaoOpe.delete(createreportDaoOpe.queryBuilder().where().eq("belongid", wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Createreport findByUdinspojxxmid(String id) {
        try {
            return createreportDaoOpe.queryBuilder().where().eq("udinspojxxmid", id).queryForFirst();
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
