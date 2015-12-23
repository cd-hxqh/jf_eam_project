package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.DatabaseHelper;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.WorkOrder;
import com.jf_eam_project.model.Wplabor;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/12/23.
 * 计划员工
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

    /**
     * 新增员工
     * @param labtrans
     */
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

    /**
     * 修改员工信息
     * @param wplabor
     */
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

    /**
     * 查询所有工单
     * @return
     */
    public List<Wplabor> queryForAll(){
        try {
            return WplaborDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有信息
     */
    public void deleteall(){
        try {
            WplaborDaoOpe.delete(WplaborDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据工单号删除信息
     */
    public void deleteByWonum(int wonum){
        try {
            WplaborDaoOpe.delete(WplaborDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照id查询工单
     * @param id
     * @return
     */
    public Wplabor SearchByNum(int id){
        try {
            return WplaborDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询本地是否存在
     * @param wplabor
     * @return
     */
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
