
# [CoordinateLayout]()

CoordinateLayout以下简称为`Col`



	By attaching a Behavior to a direct child of CoordinatorLayout, you’ll be able to intercept touch events, window insets, measurement, layout, and nested scrolling.  

## Behavior
behavior可以用来协助col对子view的交互，如布局 手势

Behavior中有几个概念：

**child**

> the child view associated with this Behavior
它是一个View，是该Behavior的关联对象，也即Behavior所要操作的对象


**dependency**

dependency是child的依赖对象，是Behavior对child进行操作的根据


如何建立依赖关系？

`public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency)` 

判断`dependency`是否是我们所依赖的对象，如果是，返回true即可建立  


建立依赖关系后，何时Behavior该对child进行操作？

首先我们得知道dependency发生了改变，比如它移动了，然后我们根据它的改变去对child进行操作。

幸好系统帮我们做了，建立依赖后，当dependency的布局或者位置发生改变，会调用Behavior的`onDependentViewChanged`方法，

在方法`public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency)`里我们去响应dependency的改变，当我们确实需要响应并且改变了child的大小或者位置的时候，我们需要返回`true`  



另外如果实现了`onDependentViewChanged`那么也应该实现`public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection)`方法，`onLayoutChild`会在dependency被layout之后调用，在`onLayoutChild`我们可以返回true来表示自己布局，比如我们把child放在dependency的下面（返回false则表示不由Behavior控制child的layout）



或许有用的资料

<https://medium.com/google-developers/intercepting-everything-with-coordinatorlayout-behaviors-8c6adc140c26#.p1v2t3rxo>  

<https://guides.codepath.com/android/Handling-Scrolls-with-CoordinatorLayout>  

<http://blog.csdn.net/xyz_lmn/article/details/48055919> 
<https://www.aswifter.com/2015/11/12/mastering-coordinator/>

<https://github.com/saulmm/CoordinatorBehaviorExample>  

<https://lab.getbase.com/introduction-to-coordinator-layout-on-android/>

<http://gold.xitu.io/entry/570062861ea4930055f6980d>  

<http://www.jianshu.com/p/d372d37e8640>



