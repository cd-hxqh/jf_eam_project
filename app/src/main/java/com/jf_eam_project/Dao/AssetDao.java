package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Assets;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/28.
 *资产
 */
public class AssetDao {
    private Context context;
    private Dao<Assets, Integer> AssetDaoOpe;
    private DatabaseHelper helper;

    public AssetDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            AssetDaoOpe = helper.getDao(Assets.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新位置信息
     * @param list
     */
    public void create(final List<Assets> list) {
            try {
                deleteall();
                AssetDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Assets location : list) {
                            AssetDaoOpe.createOrUpdate(location);
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
    public List<Assets> queryForAll(){
        try {
            return AssetDaoOpe.queryForAll();
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
            AssetDaoOpe.delete(AssetDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param assetnum
     * @return
     */
    public List<Assets> queryByNum(String assetnum){
        try {
            return AssetDaoOpe.queryBuilder().where().like("assetnum", assetnum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param asset
     * @return
     */
    public boolean isexit(Assets asset){
        try {
            List<Assets>workOrderList = AssetDaoOpe.queryBuilder().where().eq("assetnum",asset.assetnum)
                    .and().eq("description",asset.description).query();
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
