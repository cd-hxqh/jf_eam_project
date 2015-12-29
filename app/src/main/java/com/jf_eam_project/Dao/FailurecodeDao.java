package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Failurecode;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 *故障类
 */
public class FailurecodeDao {
    private Context context;
    private Dao<Failurecode, Integer> FailurecodeDaoOpe;
    private DatabaseHelper helper;

    public FailurecodeDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            FailurecodeDaoOpe = helper.getDao(Failurecode.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新故障类信息
     * @param list
     */
    public void create(final List<Failurecode> list) {
            try {
                deleteall();
                FailurecodeDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Failurecode failurecode : list) {
                            FailurecodeDaoOpe.createOrUpdate(failurecode);
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
    public List<Failurecode> queryForAll(){
        try {
            return FailurecodeDaoOpe.queryForAll();
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
            FailurecodeDaoOpe.delete(FailurecodeDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param failurecode
     * @return
     */
    public List<Failurecode> queryByFailurecode(String failurecode){
        try {
            return FailurecodeDaoOpe.queryBuilder().where().like("failurecode",failurecode).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param failurecode
     * @return
     */
    public boolean isexit(Failurecode failurecode){
        try {
            List<Failurecode>workOrderList = FailurecodeDaoOpe.queryBuilder().where().eq("failurecode",failurecode.failurecode)
                    .and().eq("description",failurecode.description).query();
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
