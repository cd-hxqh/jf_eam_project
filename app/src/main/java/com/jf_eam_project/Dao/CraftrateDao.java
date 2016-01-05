package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Craftrate;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/1/4.
 *工种
 */
public class CraftrateDao {
    private Context context;
    private Dao<Craftrate, Integer> CraftrateDaoOpe;
    private DatabaseHelper helper;

    public CraftrateDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            CraftrateDaoOpe = helper.getDao(Craftrate.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新工种信息
     * @param list
     */
    public void create(final List<Craftrate> list) {
            try {
                deleteall();
                CraftrateDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Craftrate craftrate : list) {
                            CraftrateDaoOpe.createOrUpdate(craftrate);
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
    public List<Craftrate> queryForAll(){
        try {
            return CraftrateDaoOpe.queryForAll();
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
            CraftrateDaoOpe.delete(CraftrateDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param craft
     * @return
     */
    public List<Craftrate> queryByCraft(String craft){
        try {
            return CraftrateDaoOpe.queryBuilder().where().like("craft", craft).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param craftrate
     * @return
     */
    public boolean isexit(Craftrate craftrate){
        try {
            List<Craftrate>craftrateList = CraftrateDaoOpe.queryBuilder().where().eq("location",craftrate.craft).query();
            if(craftrateList.size()>0){
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
