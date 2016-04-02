# 第1章-Activity的生命周期和启动模式


## Activity的生命周期

1. 正常状态
2. 异常状态(系统杀死/Configuration变化如屏幕旋转)

### 正常状态

1. onCreate   表示Activity正在创建,可以做一些初始化操作
2. onRestart  正在重新启动,onstop后回来会调用 
3. onStart    正在被启动,后台,不可交互
4. onResume   **可见可交互前台**,非常重要,许许多多的重要的类在这里初始化 
4. onPause    正在停止 **后台(不一定不可见)** 不可做太耗时的操作,**因为onPause之后新启动Activity的onResume才能被调用**
5. onStop    即将停止,**不可见** 可以做一些稍微重量级  的回收
6. onDestroy 即将销毁,可以做一些回收资源,关闭线程,移除Handler消息等操作
  

生命周期配对去记忆效果更佳:  
create -- destroy
start -- stop	 
resume -- pause  
	 
	 
注意点:  

A 启动 B
如果B是透明的Activity,那么A的onStop不会被调用  


### 异常状态

Activity的销毁与重建涉及到这两方法:  
1. `onSaveInstanceState(@Nullable Bundle state)`    系统会调用它来保存状态,以便之后恢复
2. `onRestoreInstanceState(@NonNull Bundle state)`  为系统恢复所用
 

**当Activity将处于可能被销毁或要被销毁的状态,就会调用`onSaveInstanceState`** 
而**`onRestoreInstanceState`则是重建的时候被调用**  
 
 
#### 调用时机
 
`onSaveInstanceState` 在`onStop`之前调用,而不一定在`onPause`之后  
`onRestoreInstanceState` 在`onStart`之后,`onResume`之前
 
所以,大致的完整的生命周期是这样的:  
onCreate  
onRestart  
onStart   
onRestoreInstanceState  
onResume  
onPause  
onSaveInstanceState  
onStop  
onDestroy  
 
 
PS:`onSaveInstanceState`如下情况会调用:  
1. 启动了新的Activity  
2. 按了Home键等等    

另外:可以在onCreate里判断bundle是否为null来判断是新建还是重新创建  


#### View 的恢复
A系统默认做了一定的恢复,如视图结构,LV的滑动的位置等等(View也有save,restore方法)  

	> PS 看到有的文章说解决Fragment重叠的问题,就是注释掉Activity的save方法,简直是误人子弟!!!

扯远了,保存和恢复View的层次结构,系统的工作流程是这样子的:  
Activity==>Window==>DecorView==>ContentView==>View  
一层一层**委托**保存恢复状态  

### Activity优先级

1. 前台Activity  正在交互的,onResume状态的Activity
2. 可见但非前台,弹了Dialog,依然可见但是不能交互
3. 后台Activity ,执行onStop之后

### Configuration 改变
Configuration改变的时候系统默认会重建Activity,如果我不想重建,那么可以选择配置`android:configChanges`属性,一般常用的就`orientation`,`screenSize`,`keyboardHidden`  

so,一般给Activity配上这个就行了:  
`android:configChanges="orientation|screenSize|keyboardHidden"`  

## 启动模式

1. Standard 标准模式,默认的启动模式,每次启动都会新建一个Activity实例



需要注意的是
