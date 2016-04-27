## HotFix 的抉择

现有框架 ：  

- [AndFix](https://github.com/alibaba/AndFix) 
- [dexposed](https://github.com/alibaba/dexposed)  
- [Xposed](https://github.com/rovo89/Xposed)
- [Nuwa](https://github.com/jasonross/Nuwa) 
- [HotFix](https://github.com/dodola/HotFix) 

qq空间的暂时还没开源      

## [AndFix](https://github.com/alibaba/AndFix)

alibaba出品  

功能：`method body's replacing` 即支持方法级别修复    

### Support  

Android Version :2.3-6.0  
dalvik & art runtime  


### 优缺点

**无需重启**  

andfix只能替换方法而不能增减新的字段，也不能下发类  


### issue（122个）

查看了一些issue，记录一些**可能存在的**问题以及缺陷：  

1. 兼容性问题  
2. 部分手机奔溃  
3. 部分手机ANR  
4. 不能改变量的值，不过方法的添加修改，删除，都可以
5. 需要注意多进程    
6. ART下模式无法对同一个方法进行多次更新  

只是说可能，因为很多issue都没人回复  

## [Nuwa](https://github.com/jasonross/Nuwa)

贾吉鑫（原点评）出品    

## Support
dalvik & art runtime    
productFlavor & buildType    
proguard & multidex     
纯Java 实现   


## 优缺点

基于类级别的修复，不仅仅只是修复方法    

**需要重启**  

### issue(46个)

1. ART环境支持的不好
2. [i50](https://github.com/jasonross/Nuwa/issues/50) nuwa只支持 4.0以上，并不是作者所描述的2.3~6.0,作者使用的BaseDexClassLoader这个4.0才有的API，导致4.0以下的手机用Nuwa就必定崩溃，特此提醒
  
同AndFix  


## Compare


dexposed **不支持art(5.0+)** 比较**致命**，所以最开始就被排除了    

Nuwa HotFix DroidFix 都基于ClassLoader,兼容性以及稳定性好，支持4.0~6.0,对于启动速度有影响，补丁**重启后才能生效** 

AndFix,支持2.3~6.0，但是兼容性以及稳定性较差，关键是**不需要重启**    


原本想画个表，但是时间有限，不画了，就是这么任性。。。   

|HotFix|Reboot|Version|LastUpdateTime|
|:---:|:---:|:---:|  
|AndFix|No|0.4.0|2016.4|
|Nuwa|Yes|1.0.0|五个月之前|


## 结果

虽然我原本从各个维度去考虑如何选择，比如Star数，版本号，更新频率，issue解答率等等，
但是到最后其实可选的余地就两个，**想要不重启即可生效，就选AndFix，否则可以考虑Nuwa**  

另外据小道消息听闻，QQ空间团队的热补（ClassLoader的鼻祖）会在qcan结束后开源，时间未知  

我这个只是简单的比较，较为详细的可以看看大帅的『各大热补丁方案分析和比较』一文  

另外提供了些许资料，有兴趣的可以看看  
## 资料
[Android热更新实现原理](http://blog.csdn.net/lzyzsd/article/details/49843581)  
[各大热补丁方案分析和比较
](http://blog.zhaiyifan.cn/2015/11/20/HotPatchCompare/)  
[安卓App热补丁动态修复技术介绍
](http://zhuanlan.zhihu.com/p/20308548)
