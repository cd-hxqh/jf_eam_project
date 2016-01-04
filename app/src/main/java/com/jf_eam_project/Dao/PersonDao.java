package com.jf_eam_project.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jf_eam_project.OrmLiteHelper.DatabaseHelper;
import com.jf_eam_project.model.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2015/12/31.
 *人员
 */
public class PersonDao {
    private Context context;
    private Dao<Person, Integer> PersonDaoOpe;
    private DatabaseHelper helper;

    public PersonDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            PersonDaoOpe = helper.getDao(Person.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *更新位置信息
     * @param list
     */
    public void create(final List<Person> list) {
            try {
                deleteall();
                PersonDaoOpe.callBatchTasks(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        for (Person person : list) {
                            PersonDaoOpe.createOrUpdate(person);
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
    public List<Person> queryForAll(){
        try {
            return PersonDaoOpe.queryForAll();
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
            PersonDaoOpe.delete(PersonDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param personid
     * @return
     */
    public List<Person> queryByPerson(String personid){
        try {
            return PersonDaoOpe.queryBuilder().where().like("personid",personid).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param person
     * @return
     */
    public boolean isexit(Person person){
        try {
            List<Person>workOrderList = PersonDaoOpe.queryBuilder().where().eq("location",person.personid).query();
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
