package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Failurelist;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 *问题代码
 */
public class FailurelistDao {
    private Context context;
    private Dao<Failurelist, Integer> FailurelistDaoOpe;
    private DatabaseHelper helper;

    public FailurelistDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            FailurelistDaoOpe = helper.getDao(Failurelist.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新故障类信息
     * @param list
     */
    public void create(final List<Failurelist> list) {
            try {
                deleteall();
                FailurelistDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Failurelist failurelist : list) {
                            FailurelistDaoOpe.createOrUpdate(failurelist);
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
    public List<Failurelist> queryForAll(){
        try {
            return FailurelistDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List<Failurelist> queryByCodeForAll(String code){
        try {
            return FailurelistDaoOpe.queryBuilder().where().eq("failureclass",code).and().eq("type","问题").query();
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
            FailurelistDaoOpe.delete(FailurelistDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param failurecode
     * @return
     */
    public List<Failurelist> queryByFailurecode(String failurecode){
        try {
            return FailurelistDaoOpe.queryBuilder().where().like("failurecode", "%" + failurecode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param failurelist
     * @return
     */
    public boolean isexit(Failurelist failurelist){
        try {
            List<Failurelist>workOrderList = FailurelistDaoOpe.queryBuilder().where().eq("failurecode",failurelist.failurecode)
                    .and().eq("description",failurelist.flcdescription).query();
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
