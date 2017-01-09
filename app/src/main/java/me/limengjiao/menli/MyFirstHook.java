package me.limengjiao.menli;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.callMethod;
import static de.robv.android.xposed.XposedHelpers.callStaticMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by Administrator on 2017/1/6.
 */
public class MyFirstHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!(loadPackageParam.packageName.equals("me.limengjiao.test")||loadPackageParam.packageName.equals("com.tencent.mm")))
            return;


        if(loadPackageParam.packageName.equals("me.limengjiao.test")){
            XposedBridge.log("lmj now we are in lmj test");
            XposedHelpers.findAndHookMethod("me.limengjiao.test.MainActivity", loadPackageParam.classLoader, "hello",int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("before method run");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("prepare to hook");
//                String[] a = new String[] {"\u674e", "\u68a6","\u86df","\u81ea","\u5df1","\u7684","\u5fae","\u4fe1",  "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
//                XposedHelpers.setObjectField(param.thisObject,"gLa",a);
                    param.setResult("hook");
                    XposedBridge.log("hook success");
                }
            });
        } else {
            XposedBridge.log("lmj now we are in tecent mm");
            XposedHelpers.findAndHookMethod("com.tencent.mm.ui.base.AlphabetScrollBar", loadPackageParam.classLoader, "YK",new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("before method run");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("prepare to hook");
                String[] a = new String[] {"\u5B9D","\u5B9D","\u6211","\u7231","\u4F60","A","B","C","G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
                XposedHelpers.setObjectField(param.thisObject,"gLa",a);

                    XposedBridge.log("hook success");
                }
            });

        }

    }
}
