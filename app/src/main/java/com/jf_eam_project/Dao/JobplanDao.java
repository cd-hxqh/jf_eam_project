package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Jobplan;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 * 作业计划
 */
public class JobplanDao {
    private Context context;
    private Dao<Jobplan, Integer> JobplanDaoOpe;
    private DatabaseHelper helper;

    public JobplanDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            JobplanDaoOpe = helper.getDao(Jobplan.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新故障类信息
     * @param list
     */
    public void create(final List<Jobplan> list) {
            try {
                deleteall();
                JobplanDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Jobplan jobplan : list) {
                            JobplanDaoOpe.createOrUpdate(jobplan);
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
    public List<Jobplan> queryForAll(){
        try {
            return JobplanDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *分页查询
     * @param count
     * @param jpnum
     * @return
     */
    public List<Jobplan> queryByCount(int count,String jpnum){
        try {
            return JobplanDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("jpnum", "%"+jpnum+"%").query();
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
            JobplanDaoOpe.delete(JobplanDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param jpnum
     * @return
     */
    public List<Jobplan> queryByJobplan(String jpnum){
        try {
            return JobplanDaoOpe.queryBuilder().where().like("jpnum", "%"+jpnum+"%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param jobplan
     * @return
     */
    public boolean isexit(Jobplan jobplan){
        try {
            List<Jobplan>workOrderList = JobplanDaoOpe.queryBuilder().where().eq("jpnum",jobplan.jpnum)
                    .and().eq("description",jobplan.description).query();
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
