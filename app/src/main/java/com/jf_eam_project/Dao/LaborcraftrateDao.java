package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Laborcraftrate;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/1/6.
 * 员工工种表
 */
public class LaborcraftrateDao {
    private Context context;
    private Dao<Laborcraftrate, Integer> LaborcraftrateDaoOpe;
    private DatabaseHelper helper;

    public LaborcraftrateDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            LaborcraftrateDaoOpe = helper.getDao(Laborcraftrate.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Laborcraftrate> list) {
        try {
            deleteall();
            LaborcraftrateDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Laborcraftrate laborcraftrate : list) {
                        LaborcraftrateDaoOpe.createOrUpdate(laborcraftrate);
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
    public List<Laborcraftrate> queryForAll() {
        try {
            return LaborcraftrateDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public List<Laborcraftrate> queryByCount(int count,String laborcode) {
        try {
            return LaborcraftrateDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("laborcode", "%" + laborcode + "%").query();
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
            LaborcraftrateDaoOpe.delete(LaborcraftrateDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param laborcode
     * @return
     */
    public List<Laborcraftrate> queryByLabor(String laborcode) {
        try {
            return LaborcraftrateDaoOpe.queryBuilder().where().like("laborcode", "%" + laborcode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param craft
     * @return
     */
    public List<Laborcraftrate> queryByCraft(int count,String laborcode,String craft) {
        try {
            return LaborcraftrateDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().eq("craft", craft).and().like("laborcode", "%" + laborcode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param labor
     * @return
     */
    public boolean isexit(Laborcraftrate labor) {
        try {
            List<Laborcraftrate> workOrderList = LaborcraftrateDaoOpe.queryBuilder().where().eq("location", labor.laborcode).query();
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
