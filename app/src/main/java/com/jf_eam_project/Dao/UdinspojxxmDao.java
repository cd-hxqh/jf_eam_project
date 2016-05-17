package com.jf_eam_project.Dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Udinspo;
import com.jf_eam_project.model.Udinspoasset;
import com.jf_eam_project.model.Udinspojxxm;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/28.
 *检修项目标准
 */
public class UdinspojxxmDao {
    private static final String TAG="UdinspojxxmDao";
    private Context context;
    private Dao<Udinspojxxm, Integer> udinspojxxmDaoOpe;
    private DatabaseHelper helper;

    public UdinspojxxmDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            udinspojxxmDaoOpe = helper.getDao(Udinspojxxm.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * 新增
     */
    public void insert(Udinspojxxm udinspojxxm) {
        try {
            udinspojxxmDaoOpe.createOrUpdate(udinspojxxm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 更新
     */
    public void update(Udinspojxxm udinspojxxm) {
        try {
            udinspojxxmDaoOpe.update(udinspojxxm);
        } catch (SQLException e) {
            Log.i(TAG,"this is sqlException");
            e.printStackTrace();
        }
    }
    /**
     *
     * @param list
     */
    public void create(final List<Udinspojxxm> list) {
            try {
                udinspojxxmDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Udinspojxxm udinspojxxm : list) {
                            deleteudUdinspojxxmid(udinspojxxm.udinspojxxmid, 0);
                            udinspojxxmDaoOpe.createOrUpdate(udinspojxxm);
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
    public List<Udinspojxxm> queryForAll(){
        try {
            return udinspojxxmDaoOpe.queryForAll();
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
            udinspojxxmDaoOpe.delete(udinspojxxmDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**根据巡检备件编号删除信息**/
    public void deleteudUdinspoassetNum(String udinspoassetnum) {
        try {

            List<Udinspojxxm> list = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum", udinspoassetnum).query();

            udinspojxxmDaoOpe.delete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**根据巡检备件编号删除信息**/
    public void deleteudUdinspojxxmid(int udinspojxxmid,int local) {
        try {

            List<Udinspojxxm> list = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspojxxmid", udinspojxxmid).and().eq("local",local).query();

            udinspojxxmDaoOpe.delete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     *
     * @param insponum
     * @return
     */
    public List<Udinspojxxm> queryByNum(String insponum){
        try {
            return udinspojxxmDaoOpe.queryBuilder().where().like("udinspoassetnum", insponum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param insponum
     * @return
     */
    public List<Udinspojxxm> queryByNumAndLocal(String insponum){
        try {
            return udinspojxxmDaoOpe.queryBuilder().where().like("udinspoassetnum", insponum).and().eq("local", "1").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    /**根据设备编号查询信息**/

    public List<Udinspojxxm> queryByUdinspoassetnum(String udinspoassetnum){
        try {
            return udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum", udinspoassetnum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     *
     * @param udinspojxxm
     * @return
     */
    public boolean isexit(Udinspojxxm udinspojxxm){
        try {
            List<Udinspojxxm>workOrderList = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum",udinspojxxm.udinspoassetnum)
                    .and().eq("description",udinspojxxm.description).query();
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

    /**根据新增就设备单号查询检修项目标准**/
    public List<Udinspojxxm> findByudinspoassetnum(String udinspoassetnum){
        List<Udinspojxxm> udinspojxxms=null;
        try {
            udinspojxxms = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum",udinspoassetnum).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return udinspojxxms;
    }


    /**
     * 根据,巡检备件编号，描述查询udinspo信息*
     */
    public List<Udinspojxxm> queryByDesc(String desc) {
        List<Udinspojxxm> list = null;
        try {
            list = udinspojxxmDaoOpe.queryBuilder().where().like("description", "%" + desc + "%").and().like("udinspoassetnum","%" + desc + "%").and().eq("local",1).query();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @param list
     */
    public void deleteList(final List<Udinspojxxm> list) {
        try {
            udinspojxxmDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinspojxxm udinspo : list) {
                        udinspojxxmDaoOpe.delete(udinspo);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**计算已完成的任务
     * 0表示未完成
     * 1表示已完成
     *
     * **/
    public List<Udinspojxxm> findBycompletion(String udinspoassetnum,int completion){
        List<Udinspojxxm> udinspojxxms=null;
        try {
            udinspojxxms = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum",udinspoassetnum).and().eq("completion",completion).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return udinspojxxms;
    }


    /**计算未完成的单子**/
    public List<Udinspojxxm> findByabnormal(String udinspoassetnum,String abnormal){
        List<Udinspojxxm> udinspojxxms=null;
        try {
            udinspojxxms = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum",udinspoassetnum).and().eq("udinspojxxm1",abnormal).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return udinspojxxms;
    }

    /**根据udinspoassetnum查询本地已操作的单子**/
    public List<Udinspojxxm> findByLocation(String udinspoassetnum,int local){
        List<Udinspojxxm> udinspojxxms=null;
        try {
            udinspojxxms = udinspojxxmDaoOpe.queryBuilder().where().eq("udinspoassetnum",udinspoassetnum).and().eq("local",local).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return udinspojxxms;

    }



}
