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
     *  查询位置
     * @return
     */
    public List<Location> queryForAll(){
        try {
            return LocationDaoOpe.queryBuilder().where().eq("type","操作中").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *分页查询
     * @param count
     * @param location
     * @return
     */
    public List<Location> queryByCount(int count,String location){
        try {
            return LocationDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().eq("type", "操作中").and().like("location", "%" + location + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  查询库房
     * @return
     */
    public List<Location> queryForLocations(){
        try {
            return LocationDaoOpe.queryBuilder().where().eq("type", "库房").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *分页查询库房
     * @param count
     * @param location
     * @return
     */
    public List<Location> queryByCountForLocations(int count,String location){
        try {
            return LocationDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().eq("type", "库房").and().like("location", "%" + location + "%").query();
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
     *  条件查询位置
     * @param location
     * @return
     */
    public Location queryLocation(String location){
        try {
            return LocationDaoOpe.queryBuilder().where().eq("type","操作中").and().eq("location", location).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  条件查询位置list
     * @param location
     * @return
     */
    public List<Location> queryByLocation(String location){
        try {
            return LocationDaoOpe.queryBuilder().where().eq("type","操作中").and().like("location", "%"+location+"%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  条件查询库房list
     * @param location
     * @return
     */
    public List<Location> queryByLocations(String location){
        try {
            return LocationDaoOpe.queryBuilder().where().eq("type", "库房").and().like("location", "%"+location+"%").query();
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
