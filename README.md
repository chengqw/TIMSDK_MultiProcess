
本文介绍如何在 TUIKitDemo 中实现一个 APP 内启动两个进程，分别使用不同 SDKAPPID 和 userID 登录接入 IM 云通信服务。

## 简介

一个 APP 启动多个进程，借助 Android 组件自带的属性：android:process 和 android:multiprocess 来实现的。

比如：

多进程 Activity
```
<activity
     android:name=".main.MainActivity"
     android:launchMode="singleTask"
     android:screenOrientation="portrait"
     android:multiprocess="true"
     android:windowSoftInputMode="adjustResize|stateHidden">

     <!-- 离线推送打开应用内页面 -->
     <intent-filter>
          <action android:name="android.intent.action.VIEW" />
          <category android:name="android.intent.category.DEFAULT" />
          <data
              android:host="com.tencent.qcloud"
              android:path="/detail"
              android:scheme="pushscheme" />
      </intent-filter>
 </activity>
```

多进程登录界面
```
<activity
    android:name=".login.LoginForDevActivity"
    android:screenOrientation="portrait" />

<activity
    android:name=".login.LoginForDevOtherActivity"
    android:screenOrientation="portrait"
    android:process=":other"/>
```

## 多线程区分

Application在多进程模式下，OnCreate会调用多次，因为会有多个相同的 Application。可以通过 AMS 获取进程列表，通过判断进程号和进程名称是否为主进程名称，来区分主子进程。

```
android.os.Process.myPid() // 获取当前的进程号
getPackageName() // 获取主进程名称，子进程名称是 getPackageName():子进程 或者 getPackageName().子进程
```
				
## 注意

#### 多进程带来的一些问题

1、Application的多次重建。

2、静态成员的失效。

3、文件共享问题。

4、断点调试问题。

#### Demo 适配

1、所有 Activity 组件需要多进程处理。

2、需要关注下公共存储路径、DB、配置文件等，多进程访问会带来并发问题。

3、Demo使用自研组件架构，各个组件有自启动 Service（contentProvider），需要在子进程兼容下各个 Service 的逻辑。

4、Java多进程会启动多个虚拟机，各个类和资源都会拷贝加载，包括so库的native空间，也会随应用进程而运行在对应进程，这些在多进程中都不需要特殊处理。

5、如果是类似于C/S模式的多进程，不需要关注以上，做好进程通信即可。