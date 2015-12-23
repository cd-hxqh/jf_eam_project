package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.DatabaseHelper;
import com.jf_eam_project.model.WorkOrder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by think on 2015/12/23.
 */
public class WorkOrderDao {
    private Context context;
    private Dao<WorkOrder, Integer> WorkOrderDaoOpe;
    private DatabaseHelper helper;

    public WorkOrderDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            WorkOrderDaoOpe = helper.getDao(WorkOrder.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 新增工单
     * @param workOrder
     */
    public void create(WorkOrder workOrder) {
        try
        {
            if(!isexitByNum(workOrder.wonum)){
                WorkOrderDaoOpe.createOrUpdate(workOrder);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 修改工单
     * @param workOrder
     */
    public void Update(WorkOrder workOrder) {
        try
        {
            List<WorkOrder>workOrderList = WorkOrderDaoOpe.queryBuilder().where().eq("wonum",workOrder.wonum).query();
            if(workOrderList.size()!=0){
                WorkOrderDaoOpe.delete(workOrderList);
            }
            WorkOrderDaoOpe.createOrUpdate(workOrder);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有工单
     * @return
     */
    public List<WorkOrder> queryForAll(){
        try {
            return WorkOrderDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按工单类型查询工单
     * @param udwotype
     */
    public List<WorkOrder> queryByUdwotype(String udwotype){
        try {
                return WorkOrderDaoOpe.queryBuilder().orderBy("date", false)
                        .where().eq("udwotype", udwotype).query();

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
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据工单号删除信息
     */
    public void deleteByWonum(int wonum){
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照工单id查询工单
     * @param id
     * @return
     */
    public WorkOrder SearchByNum(int id){
        try {
            return WorkOrderDaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照工单编号查询本地是否存在此工单
     * @param wonum
     * @return
     */
    public boolean isexitByNum(String wonum){
        try {
            List<WorkOrder>workOrderList = WorkOrderDaoOpe.queryBuilder().where().eq("wonum",wonum).query();
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
