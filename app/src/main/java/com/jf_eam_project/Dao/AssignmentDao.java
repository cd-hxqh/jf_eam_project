package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Assignment;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 *
 */
public class AssignmentDao {
    private Context context;
    private Dao<Assignment, Integer> AssignmentDaoOpe;
    private DatabaseHelper helper;

    public AssignmentDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            AssignmentDaoOpe = helper.getDao(Assignment.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储任务
     * @param list
     */
    public void create(final List<Assignment> list) {
        try {
            deleteall();
            AssignmentDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Assignment assignment : list) {
                        AssignmentDaoOpe.createOrUpdate(assignment);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Assignment assignment) {
        try
        {
            AssignmentDaoOpe.createOrUpdate(assignment);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Assignment> queryForAll(){
        try {
            return AssignmentDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id
     */
    public List<Assignment> queryByWonum(int id) {
        try {
            return AssignmentDaoOpe.queryBuilder().where().eq("belongid", id ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteall(){
        try {
            AssignmentDaoOpe.delete(AssignmentDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteByWonum(int wonum){
        try {
            AssignmentDaoOpe.delete(AssignmentDaoOpe.queryBuilder().where().eq("belongid",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Assignment SearchByNum(int id){
        try {
            return AssignmentDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
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
