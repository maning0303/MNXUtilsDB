# MNXUtilsDB 简介 [![](https://jitpack.io/v/maning0303/MNXUtilsDB.svg)](https://jitpack.io/#maning0303/MNXUtilsDB)

* MNXUtilsDB 是把 [xUtils3](https://github.com/wyouflf/xUtils3) 数据库模块单独抽取出来，方便使用！当前使用的是xUtils3的V3.5.0版本中的数据库模块。


## [xUtils3](https://github.com/wyouflf/xUtils3) 简介
* xUtils 包含了orm, http(s), image, view注解, 但依然很轻量级(246K), 并且特性强大, 方便扩展:
  - `稳定的基石`: `AbsTask`和统一的回调接口`Callback`, 任何异常, 即使你的回调方法实现有异常都会进入`onError`, 任何情况下`onFinished`总会让你知道任务结束了.
  - 基于高效稳定的`orm`工具, `http`模块得以更方便的实现cookie(支持domain, path, expiry等特性)和
    缓存(支持Cache-Control, Last-Modified, ETag等特性)的支持.
  - 有了强大的`http`及其下载缓存的支持, `image`模块的实现相当的简洁, 并且支持回收被view持有, 但被Mem Cache移除的图片, 减少页面回退时的闪烁..
  - `view`注解模块仅仅400多行代码却灵活的支持了各种View注入和事件绑定, 包括拥有多了方法的listener的支持.


## 如何添加

### 方式一:Gradle添加：
   #### 1.在Project的build.gradle中添加仓库地址

   ``` gradle
   	allprojects {
   		repositories {
   			...
   			maven { url "https://jitpack.io" }
   		}
   	}
   ```

   #### 2.在app目录下的build.gradle中添加依赖
   ``` gradle
   	dependencies {
   	     compile 'com.github.maning0303:MNXUtilsDB:V1.0.0'
   	}
   ```

### 方式二:(方便自定义修改)下载源码使用Module添加：xutils

``` gradle
	compile project(':xutils')

```

## 使用数据库(参考sample项目 或者 查看[xUtils3](https://github.com/wyouflf/xUtils3))

### 1:权限配置
```java

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

```

### 2:Application初始化
```java

    // 在application的onCreate中初始化
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        ...
    }

```

### 3:代码中使用

```java

    Parent test = db.selector(Parent.class).where("id", "in", new int[]{1, 3, 6}).findFirst();
    long count = db.selector(Parent.class).where("name", "LIKE", "w%").and("age", ">", 32).count();
    List<Parent> testList = db.selector(Parent.class).where("id", "between", new String[]{"1", "5"}).findAll();

```

## 代码来源：
[xUtils3](https://github.com/wyouflf/xUtils3)

## 感谢：
[xUtils3](https://github.com/wyouflf/xUtils3)
