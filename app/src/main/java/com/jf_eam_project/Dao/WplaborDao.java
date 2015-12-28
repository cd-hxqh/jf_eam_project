package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/12/23.
 *
 */
public class WplaborDao {
    private Context context;
    private Dao<Wplabor, Integer> WplaborDaoOpe;
    private DatabaseHelper helper;

    public WplaborDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WplaborDaoOpe = helper.getDao(WorkOrder.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public void create(Wplabor labtrans) {
        try
        {
            if(!isexit(labtrans)){
                WplaborDaoOpe.createOrUpdate(labtrans);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

   
    public void Update(Wplabor wplabor) {
        try
        {
            List<Wplabor>labtransList = WplaborDaoOpe.queryBuilder().where().eq("wonum", wplabor.wonum)
                    .and().eq("laborcode",wplabor.laborcode).query();
            if(labtransList.size()!=0){
                WplaborDaoOpe.delete(labtransList);
            }
            WplaborDaoOpe.createOrUpdate(wplabor);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public List<Wplabor> queryForAll(){
        try {
            return WplaborDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteall(){
        try {
            WplaborDaoOpe.delete(WplaborDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteByWonum(int wonum){
        try {
            WplaborDaoOpe.delete(WplaborDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Wplabor SearchByNum(int id){
        try {
            return WplaborDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean isexit(Wplabor wplabor){
        try {
            List<Wplabor>workOrderList = WplaborDaoOpe.queryBuilder().where().eq("wonum",wplabor.wonum)
                    .and().eq("laborcode",wplabor.laborcode).query();
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
