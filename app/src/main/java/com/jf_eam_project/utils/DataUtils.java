package com.jf_eam_project.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;


import com.jf_eam_project.config.Constants;

import java.io.File;

/**
 * Created by apple on 15/9/24.
 * ϵͳ������
 */
public class DataUtils {

    private static final String TAG="Utils111";
    // �����ж�sdcard�Ƿ����
    public static boolean isSdCard() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * @param @param  context
     * @param @return �趨�ļ�
     * @return String ��������
     * @throws
     * @Title: getFilePath
     * @Description: TODO��ȡ�ļ�·��
     */
    public static String getFilePath(Context context) {
        boolean isSdCard = isSdCard();
        String path = null;
        if (isSdCard) {
            path = Constants.PATH_DB + context.getPackageName() + File.separator;
        } else {
            path = Constants.NOT_SDCARD_PATH_DB + context.getPackageName() + File.separator;
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String DB_NAME = path + Constants.TB_NAME;
        Log.i(TAG,"DB_NAME="+DB_NAME);

        return DB_NAME;
    }


    /**
     * ��ȡ�ļ��еĴ�С*
     */
    public static double getDirSize(File file) {

        Log.i(TAG,"asdsss");
        //�ж��ļ��Ƿ����
        if (file.exists()) {
            //�����Ŀ¼��ݹ���������ݵ��ܴ�С
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//������ļ���ֱ�ӷ������С,�ԡ��ס�Ϊ��λ
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            System.out.println("�ļ������ļ��в����ڣ�����·���Ƿ���ȷ��");
            return 0.0;
        }
    }

    /**
     * ɾ�������ļ�
     *
     * @param fileName
     *            Ҫɾ�����ļ����ļ���
     * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
                return true;
            } else {
                System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
                return false;
            }
        } else {
            System.out.println("ɾ�������ļ�ʧ�ܣ�" + fileName + "�����ڣ�");
            return false;
        }
    }

}
