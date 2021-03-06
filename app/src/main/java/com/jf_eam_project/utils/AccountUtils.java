package com.jf_eam_project.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jf_eam_project.R;
import com.jf_eam_project.model.Person;


/**
 * 登录帐号管理Created by yw on 2015/5/5.
 */
public class AccountUtils {

    public static final int REQUEST_LOGIN = 0;

    private static final String key_login_member = "logined@member";
    private static final String key_fav_nodes = "logined@fav_nodes";


    /**
     * 记录是否记住密码
     *
     * @param cxt
     * @param isChecked *
     */

    public static void setChecked(Context cxt, boolean isChecked) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putBoolean(cxt.getString(R.string.logined_member_ischeck), isChecked).commit();

    }

    ;


    /**
     * 读取记住状态*
     */
    public static boolean getIsChecked(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getBoolean(cxt.getString(R.string.logined_member_ischeck), false);
    }

    /**
     * 记录登录人名称
     *
     * @param cxt
     * @param displayName *
     */

    public static void setDisplayName(Context cxt, String displayName) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_displayName), displayName).commit();

    }

    ;


    /**
     * 获取登录人名称
     */
    public static String getDisplayName(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_displayName), "");
    }

    /**
     * 记录登录人id
     *
     * @param cxt
     * @param displayName *
     */

    public static void setPersonId(Context cxt, String displayName) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_personId), displayName).commit();

    }

    ;


    /**
     * 获取登录人id
     */
    public static String getPersonId(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_personId), "");
    }


    /**
     * 记录用户名与密码
     *
     * @param cxt
     * @param userName
     * @param password
     */

    public static void setUserNameAndPassWord(Context cxt, String userName, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_username), userName).putString(cxt.getString(R.string.logined_member_password), password).commit();
    }


    /**
     * 获取记住的用户名
     */


    public static String getUserName(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_username), "");
    }

    /**
     * 获取记住的密码
     */


    public static String getUserPassword(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_password), "");
    }


    /**
     * 设置服务器IP地址*
     */
    public static void setIpAddress(Context cxt, String ip) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.ip_address), ip).commit();

    }

    /**
     * 获取服务器IP地址*
     */
    public static String getIpAddress(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.ip_address), "");
    }




    /**
     * 记录PERSON*
     */
    public static void setPerson(Context cxt, Person person) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.department), person.getDepartment()).putString(cxt.getString(R.string.departmentms), person.getDepartmentms())
                .putString(cxt.getString(R.string.displayname), person.getDisplayname()).putString(cxt.getString(R.string.locationsite), person.getLocationsite()).commit();

    }

    /**
     * 部门编号-分公司*
     */
    public static String getDepartment(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.department), "");
    }
    /**
     * 部门名称-分公司*
     */
    public static String getDepartmentms(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.departmentms), "");
    }

    /**站点**/
    public static String getSite(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.locationsite), "");
    }


    /**设置用户的显示权限**/
    public static void setPermissions(Context cxt,int level) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putInt(cxt.getString(R.string.permissions), level).commit();

    }

    /**获取用户的显示权限**/
    public static int getPermissions(Context cxt) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getInt(cxt.getString(R.string.permissions), 0);
    }



}
