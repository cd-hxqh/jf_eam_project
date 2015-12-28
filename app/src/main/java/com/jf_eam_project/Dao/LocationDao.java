package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 *位置
 */
public class LocationDao {
    private Context context;
    private Dao<Location, Integer> LocationDaoOpe;
    private DatabaseHelper helper;

    public LocationDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            LocationDaoOpe = helper.getDao(Location.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新位置信息
     * @param list
     */
    public void create(final List<Location> list) {
            try {
                deleteall();
                LocationDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Location location : list) {
                            LocationDaoOpe.createOrUpdate(location);
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
    public List<Location> queryForAll(){
        try {
            return LocationDaoOpe.queryForAll();
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
            LocationDaoOpe.delete(LocationDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void deleteByWonum(int wonum){
        try {
            LocationDaoOpe.delete(LocationDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Location SearchByNum(int id){
        try {
            return LocationDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param location
     * @return
     */
    public boolean isexit(Location location){
        try {
            List<Location>workOrderList = LocationDaoOpe.queryBuilder().where().eq("location",location.location)
                    .and().eq("description",location.description).query();
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
