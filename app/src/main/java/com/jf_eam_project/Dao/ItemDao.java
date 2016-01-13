package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/1/4.
 * 项目
 */
public class ItemDao {
    private Context context;
    private Dao<Item, Integer> ItemDaoOpe;
    private DatabaseHelper helper;

    public ItemDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            ItemDaoOpe = helper.getDao(Item.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新项目信息
     * @param list
     */
    public void create(final List<Item> list) {
            try {
                deleteall();
                ItemDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Item item : list) {
                            ItemDaoOpe.createOrUpdate(item);
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
    public List<Item> queryForAll(){
        try {
            return ItemDaoOpe.queryBuilder().limit(2000).query();
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
            ItemDaoOpe.delete(ItemDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param itemnum
     * @return
     */
    public List<Item> queryByItem(String itemnum){
        try {
            return ItemDaoOpe.queryBuilder().where().like("itemnum", itemnum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean isexit(Item item){
        try {
            List<Item>craftrateList = ItemDaoOpe.queryBuilder().where().eq("location",item.itemnum).query();
            if(craftrateList.size()>0){
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
