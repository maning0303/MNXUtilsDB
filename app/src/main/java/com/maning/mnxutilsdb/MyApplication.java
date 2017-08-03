package com.maning.mnxutilsdb;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by maning on 2017/8/3.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化
        x.Ext.init(this);
        x.Ext.setDebug(true);

    }

    public static DbManager.DaoConfig getDbConfig() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("test.db")
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });
        return daoConfig;
    }

    public static DbManager getDbManager() {
        DbManager db = x.getDb(getDbConfig());
        return db;
    }

}
