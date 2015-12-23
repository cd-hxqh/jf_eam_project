package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.DatabaseHelper;
import com.jf_eam_project.model.Labtrans;
import com.jf_eam_project.model.WorkOrder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/12/23.
 * 实际员工
 */
public class LabtransDao {
    private Context context;
    private Dao<Labtrans, Integer> LabtransDaoOpe;
    private DatabaseHelper helper;

    public LabtransDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            LabtransDaoOpe = helper.getDao(WorkOrder.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 新增员工
     * @param labtrans
     */
    public void create(Labtrans labtrans) {
        try
        {
            if(!isexit(labtrans)){
                LabtransDaoOpe.createOrUpdate(labtrans);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 修改员工信息
     * @param labtrans
     */
    public void Update(Labtrans labtrans) {
        try
        {
            List<Labtrans>labtransList = LabtransDaoOpe.queryBuilder().where().eq("wonum", labtrans.wonum)
                    .and().eq("laborcode",labtrans.laborcode).query();
            if(labtransList.size()!=0){
                LabtransDaoOpe.delete(labtransList);
            }
            LabtransDaoOpe.createOrUpdate(labtrans);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有工单
     * @return
     */
    public List<Labtrans> queryForAll(){
        try {
            return LabtransDaoOpe.queryForAll();
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
            LabtransDaoOpe.delete(LabtransDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据工单号删除信息
     */
    public void deleteByWonum(int wonum){
        try {
            LabtransDaoOpe.delete(LabtransDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照id查询工单
     * @param id
     * @return
     */
    public Labtrans SearchByNum(int id){
        try {
            return LabtransDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询本地是否存在
     * @param labtrans
     * @return
     */
    public boolean isexit(Labtrans labtrans){
        try {
            List<Labtrans>workOrderList = LabtransDaoOpe.queryBuilder().where().eq("wonum",labtrans.wonum)
                    .and().eq("laborcode",labtrans.laborcode).query();
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
