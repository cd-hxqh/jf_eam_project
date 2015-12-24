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
     * �޸Ĺ���
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
     * ��ѯ���й���
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
     * ���������Ͳ�ѯ����
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
     * ɾ��������Ϣ
     */
    public void deleteall(){
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ݹ�����ɾ����Ϣ
     */
    public void deleteByWonum(int wonum){
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryBuilder().where().eq("wonum",wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���չ���id��ѯ����
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
     * ���չ�����Ų�ѯ�����Ƿ���ڴ˹���
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
