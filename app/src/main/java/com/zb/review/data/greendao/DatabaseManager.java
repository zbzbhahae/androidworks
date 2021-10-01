package com.zb.review.data.greendao;

import android.content.Context;

import com.zb.review.data.greendao.dao.DaoMaster;
import com.zb.review.data.greendao.dao.DaoSession;

public class DatabaseManager {

    public static final String DATABASE_NAME = "greendao_database";

    private static volatile DatabaseManager manager;
//    private Context context;

    private volatile DaoMaster daoMaster;

    private Context context;

    private DaoMaster.DevOpenHelper helper;

    /** daosession是线程安全*/
    private DaoSession daoSession;

    private DatabaseManager(){}

    public static DatabaseManager getManager(Context context) {
        if(null == manager)
            synchronized (DatabaseManager.class) {
                if(null == manager) {
                    manager = new DatabaseManager();
                    manager.context = context;
                }
            }
        return manager;
    }

    public DaoMaster getDaoMaster() {
        if(null == daoMaster) {
            synchronized (DatabaseManager.class) {
                if(null == daoMaster) {
                    helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME);
                    daoMaster = new DaoMaster(helper.getWritableDb());
                }
            }
        }
        return daoMaster;
    }

    /** daosession是线程安全*/
    public DaoSession getDaoSession() {
        if(null == daoSession)
            daoSession = manager.getDaoMaster().newSession();
        return daoSession;
    }

    public void close() {
        if(null != daoSession)
            daoSession.clear();
        if(null != daoMaster)
            daoMaster = null;
        if(null != helper)
            helper.close();
        manager = null;
    }

}
