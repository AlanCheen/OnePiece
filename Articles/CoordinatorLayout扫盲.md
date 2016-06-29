# 一步一步深入理解[CoordinateLayout](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.html)

这两天为了一个UI效果，看了CoordinateLayout（后面*简称Col*（我懒- -））的官方文档以及源码，在此记录如何一步一步深入理解。    

来看看Col的定义以及官网的介绍：  
```
public class CoordinatorLayout
extends ViewGroup implements NestedScrollingParent
```  

> CoordinatorLayout is a super-powered FrameLayout.
CoordinatorLayout is intended for two primary use cases:
1. As a top-level application decor or chrome layout
2. As a container for a specific interaction with one or more child views


可以看到Col继承自`ViewGroup`，并且它被设计成一个`top-level`的根布局，它本身只是一个ViewGroup，实现了`NestedScrollingParent`接口，看似非常普通，
但是说`CoordinatorLayout`是Design库**最为重要的控件**也不为过。

> 需要注意的是:由于Col只实现了`NestedScrollingParent`，所以当Col嵌套的时候会得不到你想要的效果，需要自己写一个Col去实现`NestedScrollingChild`接口！  

**why？**它`super-powered`在哪里呢？    

Col最为重要的作用是：**提供给子View实现各种交互的极大便利**  
在没有Col的日子要实现简单的交互也不是件容易的事，需要通过各种回调/Event相互回调，相互通知，复杂而且几乎不能复用，但是现在有了Col，一切都变得方便了~  

 **How？它是怎么做到的呢？**    
说到这里，不得不提到Col的静态内部类--->Behavior  
接下去来了解一下它,老司机要开车了，快上车~  

## [Behavior](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.Behavior.html)  
Behavior是什么，有什么作用？

	Interaction behavior plugin for child views of CoordinatorLayout.

	A Behavior implements one or more interactions that a user can take on a child view. These interactions may include drags, swipes, flings, or any other gestures.

其实官网资料说得挺含蓄的了，官方有一篇文章[Intercepting everything with CoordinatorLayout Behaviors
](https://medium.com/google-developers/intercepting-everything-with-coordinatorlayout-behaviors-8c6adc140c26#.p1v2t3rxo)，用标题来形容Behavior，再合适不过，**intercepting-everything！**（这篇文章讲得很好也很全面，**极力推荐阅读**）  

简单说，Behavior可以负责所有的交互甚至测量以及布局。  

另外我们需要知道的是(提前知道，后面不再提):  

- `Behavior`是Col.LayoutParams的成员变量


### 简单了解

`Behavior`的定义以及其构造方法  

```
//定义 V 为泛型，可指定针对哪种类型的View
public static abstract class Behavior<V extends View>
//默认的构造方法
public Behavior() {}
// xml里使用
public Behavior(Context context, AttributeSet attrs) {}
```

需要注意的是 **如果要在xml里使用Behavior 那么这构造方法必不可少，另外你在xml定义的属性可以在这里获取**

### 深入了解

仅仅知道构造方法显然是不够的，接下去深入阅读Behavior的源码一探究竟（一言不合就看源码）  

在阅读了`Behavior`的源码后，我觉得非常有必要先搞清楚几个非常重要的概念。  

#### child与dependency

1. **child**，*the child view associated with this Behavior*
它是一个View，**是该Behavior的关联对象，也即Behavior所要操作的对象**    
2. **dependency**，也是个View，是 **child的依赖对象，同时也是Behavior对child进行操作的根据**  

弄清楚概念后看源码会比较简单了，`Behavior`除了构造方法外，有23个方法，我会挑几个最重要的方法来讲解。  

#### 那些不能不懂的方法

之前提到了child与dependency有着依赖关系，那么问题来了： **这个依赖关系是如何建立的？**  

在Behavior类中有个方法：  

```
public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency)
```
它会被`Behavior`的LayoutParamas的`dependsOn`方法调用：

```
boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
    return dependency == mAnchorDirectChild
            || (mBehavior != null && mBehavior.layoutDependsOn(parent, child, dependency));
}
```  

而LayoutParamas的`dependsOn`方法会被Col调用，最简单的，我们可以重写`layoutDependsOn`方法，并在一定条件下返回`true`即可确立依赖关系。  

比如FAB依赖于SnackBar，是因为它在layoutDependsOn中对Snackbar返回了true：    
```
@Override
public boolean layoutDependsOn(CoordinatorLayout parent,FloatingActionButton child, View dependency) {
    // We're dependent on all SnackbarLayouts (if enabled)
    return SNACKBAR_BEHAVIOR_ENABLED && dependency instanceof Snackbar.SnackbarLayout;
}
```


另外需要注意的是：**当确定依赖关系后，当dependency被布局（或测量）后child会紧接着被布局（或测量）**，Col会无视子view的顺序(原因是Col内有个Comparator`mLayoutDependencyComparator`会按照依赖关系对所有的子View进行排序),这会影响它们的测量以及布局顺序   

可以说`layoutDependsOn`是最为重要的方法  





方法名|作用|调用实际  




## 为什么Behavior可以拦截一切？
Col的layout measure 说开去。。

原来是Col放任了Behavior~

## 推荐阅读  
[Intercepting everything with CoordinatorLayout Behaviors
](https://medium.com/google-developers/intercepting-everything-with-coordinatorlayout-behaviors-8c6adc140c26#.p1v2t3rxo)  

[CoordinatorLayout.Behavior](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.Behavior.html)  

<http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0224/3991.html>
