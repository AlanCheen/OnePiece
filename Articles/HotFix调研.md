


## HotFix 调研




现有框架 ：  

- [AndFix](https://github.com/alibaba/AndFix) 
- [dexposed](https://github.com/alibaba/dexposed)  
- [Xposed](https://github.com/rovo89/Xposed)
- [Nuwa](https://github.com/jasonross/Nuwa) 
- [HotFix](https://github.com/dodola/HotFix) 


## [AndFix](https://github.com/alibaba/AndFix)

alibaba

功能：`method body's replacing` 即支持方法级别修复    

Support:  
Android Version :2.3-6.0  
dalvik & art runtime  


andfix只能替换方法而不能增减新的字段  
也不能下发类  
无需重启  


## [Nuwa](https://github.com/jasonross/Nuwa)

贾吉鑫（点评）出品  


支持 dalvik & art runtime  
支持 productFlavor & buildType  
支持 proguard & multidex    
纯Java 实现  

基于类级别的修复  

需要重启

## Compare


dexposed **不支持art(5.0+)** 比较**致命**  

Nuwa HotFix DroidFix 都基于ClassLoader,兼容性以及稳定性好，支持2.3~6.0,对于启动速度有影响，补丁**重启后才能生效** 

AndFix,支持2.3~6.0，但是兼容性以及稳定性较差，


|HotFix|Reboot|Version|LastUpdateTime|
|:---:|:---:|:---:|  
|AndFix|No|0.4.0|2016.4|
|Nuwa|Yes||
## 资料
[Android热更新实现原理](http://blog.csdn.net/lzyzsd/article/details/49843581)  
[各大热补丁方案分析和比较
](http://blog.zhaiyifan.cn/2015/11/20/HotPatchCompare/)  
[安卓App热补丁动态修复技术介绍
](http://zhuanlan.zhihu.com/p/20308548)
