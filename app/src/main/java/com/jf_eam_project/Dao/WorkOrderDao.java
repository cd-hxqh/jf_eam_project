package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.WorkOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/23.
 */
public class WorkOrderDao {
    private Context context;
    private Dao<WorkOrder, Integer> WorkOrderDaoOpe;
    private DatabaseHelper helper;

    public WorkOrderDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            WorkOrderDaoOpe = helper.getDao(WorkOrder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储
     * @param list
     */
    public void create(final List<WorkOrder> list) {
        try {
            deleteall();
            WorkOrderDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (WorkOrder workOrder : list) {
                        WorkOrderDaoOpe.createOrUpdate(workOrder);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量更新
     * @param list
     */
    public void Update(final List<WorkOrder> list) {
        try {
            WorkOrderDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (WorkOrder workOrder : list) {
                        if (!isexitByNum(workOrder.wonum)) {
                            WorkOrderDaoOpe.createOrUpdate(workOrder);
                        }else {
                            deleteByWonum(workOrder.wonum);
                            WorkOrderDaoOpe.createOrUpdate(workOrder);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(WorkOrder workOrder) {
        try {
            if (!isexitByNum(workOrder.wonum)) {
                WorkOrderDaoOpe.createOrUpdate(workOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param workOrder
     */
    public void Update(WorkOrder workOrder) {
        try {
            WorkOrderDaoOpe.createOrUpdate(workOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public List<WorkOrder> queryForAll() {
        try {
            return WorkOrderDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 搜索本地历史数据
     */
    public List<WorkOrder> queryForLoc() {
        try {
            return WorkOrderDaoOpe.queryBuilder().where().eq("ishistory", true).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照wonum搜索本地数据
     * @param wonum
     */
    public List<WorkOrder> queryByWonumForLoc(String wonum) {
        try {
            return WorkOrderDaoOpe.queryBuilder().where().like("wonum", "%" + wonum + "%").or().like("description", "%" + wonum + "%").and().eq("ishistory",true).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param wonum
     */
    public List<WorkOrder> queryByWonum(String wonum) {
        try {
            return WorkOrderDaoOpe.queryBuilder().where().like("wonum", "%" + wonum + "%").query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param type
     */
    public List<WorkOrder> queryByType(String type) {
        try {
            return WorkOrderDaoOpe.queryBuilder().where().eq("udwotype", type).query();

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
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void deleteList(final List<WorkOrder> list) {
        try {
            WorkOrderDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (WorkOrder workOrder : list) {
                        WorkOrderDaoOpe.delete(workOrder);
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
     */
    public void deleteById(int id) {
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryBuilder().where().eq("id", id).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void deleteByWonum(String  wonum) {
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryBuilder().where().eq("wonum", wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    public WorkOrder SearchByNum(int id) {
        try {
            return WorkOrderDaoOpe.queryBuilder().where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param wonum
     * @return
     */
    public boolean isexitByNum(String wonum) {
        try {
            List<WorkOrder> workOrderList = WorkOrderDaoOpe.queryBuilder().where().eq("wonum", wonum).and().eq("ishistory",false).query();
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
