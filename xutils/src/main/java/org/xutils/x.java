package org.xutils;

import android.app.Application;
import android.content.Context;

import org.xutils.db.DbManagerImpl;

import java.lang.reflect.Method;


/**
 * Created by wyouflf on 15/6/10.
 * 任务控制中心, http, image, db, view注入等接口的入口.
 * 需要在在application的onCreate中初始化: x.Ext.init(this);
 */
public final class x {

    private x() {
    }

    public static boolean isDebug() {
        return Ext.debug;
    }

    public static Application app() {
        if (Ext.app == null) {
            try {
                // 在IDE进行布局预览时使用
                Class<?> renderActionClass = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
                Method method = renderActionClass.getDeclaredMethod("getCurrentContext");
                Context context = (Context) method.invoke(null);
                Ext.app = new MockApplication(context);
            } catch (Throwable ignored) {
                throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate()"
                        + " and register your Application in manifest.");
            }
        }
        return Ext.app;
    }

    public static DbManager getDb(DbManager.DaoConfig daoConfig) {
        return DbManagerImpl.getInstance(daoConfig);
    }

    public static class Ext {
        private static boolean debug;
        private static Application app;

        private Ext() {
        }

        public static void init(Application app) {
            if (Ext.app == null) {
                Ext.app = app;
            }
        }

        public static void setDebug(boolean debug) {
            Ext.debug = debug;
        }

    }

    private static class MockApplication extends Application {
        public MockApplication(Context baseContext) {
            this.attachBaseContext(baseContext);
        }
    }
}
