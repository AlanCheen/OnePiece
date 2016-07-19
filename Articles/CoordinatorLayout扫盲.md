# 一步一步深入理解[CoordinateLayout](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.html)

Google推出Design库已经一年了，国内也出过一些文章关于CoordinateLayout，但是都是叫你怎么用，或者简单的自定义一些Behavior,并没有一篇文章深入去了解它的原理。  

刚好这两天为了实现一个UI效果，看了`CoordinateLayout`（后面*简称Col*（我懒- -））的官方文档以及源码，搞懂了它的原理，于是想着拿出来分享，特在此记录分享如何一步一步深入理解Col，希望可以填补这个空缺。    

补充说明：

1. Col等源码基于`23.2.1`版本   
2. 本文侧重在于Col与Behavior之间的交互，侧重于原理，并选择要点进行讲解，所以可能会有一些点被我忽略（不是不重要），不过看完后我相信你对Col会有更深一层的了解。      


## 初步了解

学习最好的习惯就是看官方文档，来看看Col的定义以及官网的介绍： 
 
```
public class CoordinatorLayout
extends ViewGroup implements NestedScrollingParent
```  

> CoordinatorLayout is a super-powered FrameLayout.
CoordinatorLayout is intended for two primary use cases:
1. As a top-level application decor or chrome layout
2. As a container for a specific interaction with one or more child views


从定义可以看到Col继承自`ViewGroup`，并且它被设计成一个`top-level`的根布局，它本身只是一个ViewGroup，实现了`NestedScrollingParent`接口，看似非常普通，
但是说`CoordinatorLayout`是Design库**最为重要的控件**也不为过。

这里额外需要注意的是:   

1. **由于Col只实现了`NestedScrollingParent`，所以当Col嵌套（作为一个子View）的时候会得不到你想要的效果，需要自己写一个Col去实现`NestedScrollingChild`接口！**    
2. 没有实现`NestedScrollingChild`接口的子View如：`ListView`，`ScrollView`在5.0以下版本跟Col是配合不了的需要使用`RecyclerView`，`NestedScrollView`才行  


**why？**它`super-powered`在哪里呢？    

Col最为重要的作用是：**提供给子View实现各种交互的极大便利**  
直观的表现是我们可以使用Col非常方便地实现很多交互效果,具体效果可以看[cheesesquare](https://github.com/chrisbanes/cheesesquare)这个项目。  

要知道，在没有Col的日子要实现简单的交互也不是件容易的事，需要通过各种回调/Event，相互回调，相互通知，甚至相互持有引用，复杂而且难以复用，但是现在有了Col，一切都变得方便了~  

 **How？它是怎么做到的呢？**    
说到这里，不得不提到Col的静态内部类--->Behavior  
接下去来了解一下它,老司机要开车了，快上车~  

## 拦截一切的[Behavior](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.Behavior.html)  
Behavior是什么，有什么作用？

	Interaction behavior plugin for child views of CoordinatorLayout.

	A Behavior implements one or more interactions that a user can take on a child view. These interactions may include drags, swipes, flings, or any other gestures.

简单说，Behavior可以负责所有的交互甚至测量以及布局。

其实官网资料说得挺含蓄的，官方在Medium有一篇文章，叫:[Intercepting everything with CoordinatorLayout Behaviors
](https://medium.com/google-developers/intercepting-everything-with-coordinatorlayout-behaviors-8c6adc140c26#.p1v2t3rxo)，私以为用这个标题来形容Behavior，再合适不过，**intercepting-everything！**（这篇文章讲得很好也很全面，**极力推荐阅读**）  


拦截一切！！迫不及待进一步了解！！  

### 深入了解

#### 如何实例化指定Behavior


1. 通过构造方法实例,并在代码中设置到LayoutParamas里 
2. Xml里指定，比如`app:app:layout_behavior="me.yifeiyuan.demo.HeaderBehavior"`
3. 通过`DefaultBehavior`注解指定，比如`@CoordinatorLayout.DefaultBehavior(AppBarLayout.Behavior.class)`


第一种方式很简单，不多说，这里针对其他两种方式讲解一下，有一些注意点我们需要知道：    

###### Xml方式
先撇一下`Behavior`的定义以及其构造方法如下：    

```
//定义 V 为泛型，可指定针对哪种类型的View
public static abstract class Behavior<V extends View>
//默认的构造方法
public Behavior() {}
// xml里使用
public Behavior(Context context, AttributeSet attrs) {}
```

当我们在Xml里指定的时候，在`LayoutParams`的构造方法里会去调用`parseBehavior`这个方法，`parseBehavior`关键代码如下(不贴代码不行了，已尽量精简):  

``` 
static Behavior parseBehavior(Context context, AttributeSet attrs, String name) {
    //...省略了很多代码，只留下关键的部分
    try {
    	//获取构造方法
        Map<String, Constructor<Behavior>> constructors = sConstructors.get(); 
        //...
        return c.newInstance(context, attrs); // 注意这一行，这里传递了attrs，所以我们必须要有第二个构造方法！！！
    } catch (Exception e) {
        throw new RuntimeException("Could not inflate Behavior subclass " + fullName, e);//否则会报错 crash
    }
}
```  

这里我们需要注意的是: **如果要在xml里使用Behavior 那么第二个构造方法必不可少，所以我们自定义Behavior的时候需要注意;另外你在xml定义的属性会传递到第二个构造方法里去，可以获取你在xml里配置的属性，非常方便，可以说考虑还是非常周到的**  


###### 注解方式

第三种通过注解的方式，又是在什么时候，怎么去实例化的呢？  

在Col中的`onMeasure`中会去调用`prepareChildren`方法，而`prepareChildren`方法又调用了一个叫`getResolvedLayoutParams`的方法如下:  

```
LayoutParams getResolvedLayoutParams(View child) {
    final LayoutParams result = (LayoutParams) child.getLayoutParams();
    if (!result.mBehaviorResolved) {//如果没有解析过 则去解析
        Class<?> childClass = child.getClass();
        DefaultBehavior defaultBehavior = null;
        while (childClass != null &&
                (defaultBehavior = childClass.getAnnotation(DefaultBehavior.class)) == null) {//如果 有DefaultBehavior这个注解
            childClass = childClass.getSuperclass();
        }
        if (defaultBehavior != null) {
            try {
                result.setBehavior(defaultBehavior.value().newInstance());//实例化Behavior并把这个Behavior赋值给result
            } catch (Exception e) {
                Log.e(TAG, "Default behavior class " + defaultBehavior.value().getName() +
                        " could not be instantiated. Did you forget a default constructor?", e);
            }
        }
        result.mBehaviorResolved = true;//标记已经解析过  
    }
    return result;
}
```

所以注解方式是在`onMeasure`中通过`getResolvedLayoutParams`去实例化的。  


另外还需要知道的是，`Behavior`是Col.LayoutParams的成员变量，那么也就是说只有当你的`Behavior`设置给Col的 **直接子View** 才会有效果，这点要记住，不然徒劳无功。(Col的子View的子View就不要给它设置Behavior啦，没效果的)    


以上需要牢记，不过仅仅知道这些显然是不够的！至少我不会到这里就停~    

接下去继续深入阅读Behavior的源码一探究竟（一言不合就看源码）  

在我阅读了`Behavior`的源码后，我觉得非常有必要先搞清楚几个非常重要的概念。  

#### child与dependency

1. **child**，*the child view associated with this Behavior*
它是一个View，**是该Behavior的关联对象，也即Behavior所要操作的对象**    
2. **dependency**，也是个View，是 **child的依赖对象，同时也是Behavior对child进行操作的根据**  

弄清楚这些个概念后看源码会比较简单了，`Behavior`除了构造方法外，有23个方法，限于篇幅与精力，我挑选几个最重要的方法来讲解，当然我不会死板的一个一个毫无逻辑地解释过去。  

#### 那些不能不懂的方法


##### layoutDependsOn  

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

而LayoutParamas的`dependsOn`方法会被Col调用，`dependsOn`方法就是用来确定依赖关系的。  

所以，最简单的确定依赖关系的方法是重写`layoutDependsOn`方法，并在一定条件下返回`true`即可确立依赖关系。  

那为什么说一定条件呢？  

比如FAB依赖于`SnackBar`，是因为它在`SnackBar`出现以及消失的时候需要改变自身的位置，所以FAB的`layoutDependsOn`方法中对`Snackbar.SnackbarLayout`返回了true，而没有依赖其他的控件：    

```
@Override
public boolean layoutDependsOn(CoordinatorLayout parent,FloatingActionButton child, View dependency) {
    // We're dependent on all SnackbarLayouts (if enabled)
    return SNACKBAR_BEHAVIOR_ENABLED && dependency instanceof Snackbar.SnackbarLayout;
}
```


另外需要注意的是：**当确定依赖关系后，当dependency被布局（或测量）后child会紧接着被布局（或测量）**，Col会无视子view的顺序(原因是Col内有个Comparator`mLayoutDependencyComparator`会按照依赖关系对所有的子View进行排序),这会影响它们的测量以及布局顺序   

可以说`layoutDependsOn`方法是自定义`Behavior`最为重要的方法  


##### onDependentViewChanged
建立起依赖关系之后呢？  

想要做交互，似乎还缺点什么，我想在dependency发生变化的时候改变一下child，我该如何知道这个改变的时机呢？  

其实不需要我们去主动获取去判断，其实Col跟Behavior已经帮我们做好了这一切，`onDependentViewChanged`登场。  

`onDependentViewChanged`方法的定义：

```
/**
 * Respond to a change in a child's dependent view
 * This method is called whenever a dependent view changes in size or position outside
 * of the standard layout flow. A Behavior may use this method to appropriately update
 * the child view in response.
 */
public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
    return false;
}
```

简单来说就是，当我们的`dependency`发生改变的时候，这个方法会调用，而我们在`onDependentViewChanged`方法里做出相应的改变，就能做出我们想要的交互效果了！  

可能你也注意到了`onDependentViewChanged`方法是有返回值的  

当我们改变了child的`size`或者`position`的时候我们需要返回true，差不多可以理解为 当我们的`dependency`发生了改变，同样的，child也需要发生改变，这个时候我们需要返回`true`



提一下：`onDependentViewChanged`方法是在Col的`dispatchOnDependentViewChanged`里调用的


##### 其他
除了以上两个特别重要的方法外，Nested系列方法也非常重要,如`onStartNestedScroll`和`onStopNestedScroll`来监听嵌套滚动的开始和结束，不过限于篇幅，想再另外开篇去写，这里就不写了    

另外还有`onMeasureChild`，`onLayoutChild`这个后面会讲。

## 为什么Behavior可以拦截一切？

我们知道，ViewGroup的测量，布局，事件分发都是需要自己处理的，那么Col究竟给了`Behavior`什么特权，让它能够让它拦截一切？  

让我们挨个一点一点看下去  

### onMeasure

直接备注在源码里了，不多说啦！~  

```
@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //之前已经提到过了 解析Behavior，并按依赖顺序重排子View顺序 
    prepareChildren();
    //用于addPreDrawListener，OnPreDrawListener里会调用 dispatchOnDependentViewChanged(false)
    ensurePreDrawListener();
    //...
    // 计算 padding width height 处理 fitSystemWindow等
    //...
    final int childCount = mDependencySortedChildren.size();
    for (int i = 0; i < childCount; i++) {
        final View child = mDependencySortedChildren.get(i);
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int keylineWidthUsed = 0;
        //...处理keyline childWidthMeasureSpec等
        final Behavior b = lp.getBehavior();
        // 跟onMeasure相同，当behavior的onMeasureChild方法返回true的时候，我们就可以拦截Col默认的measure
        if (b == null || !b.onMeasureChild(this, child, childWidthMeasureSpec, keylineWidthUsed,
                childHeightMeasureSpec, 0)) {
            onMeasureChild(child, childWidthMeasureSpec, keylineWidthUsed,
                    childHeightMeasureSpec, 0);
        }
        //...
    }
    //...
    final int width = ViewCompat.resolveSizeAndState(widthUsed, widthMeasureSpec,
            childState & ViewCompat.MEASURED_STATE_MASK);
    final int height = ViewCompat.resolveSizeAndState(heightUsed, heightMeasureSpec,
            childState << ViewCompat.MEASURED_HEIGHT_STATE_SHIFT);
    setMeasuredDimension(width, height);
}
```

### onLayout

```
@Override
protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int layoutDirection = ViewCompat.getLayoutDirection(this);
    //mDependencySortedChildren 在 onMeasure里已经排过序了
    final int childCount = mDependencySortedChildren.size();
    for (int i = 0; i < childCount; i++) {
        final View child = mDependencySortedChildren.get(i);
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        final Behavior behavior = lp.getBehavior();
        //可以看到，当behavior.onLayoutChild()返回true的时候，就可以拦截掉Col的默认Layout操作！    
        if (behavior == null || !behavior.onLayoutChild(this, child, layoutDirection)) {
            onLayoutChild(child, layoutDirection);
        }
    }
}
```

原理其实跟`onMeasure`方法一样的。  

### onInterceptTouchEvent & onTouchEvent

在处理touch事件中，Col重写了`onInterceptTouchEvent`和`onTouchEvent`，另外，它们都调用了Col里定义的一个处理拦截的方法，`performIntercept`（关键代码都在这方法之中），就看一下它们的实现吧：  

`onInterceptTouchEvent`的实现：  


```
@Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
    MotionEvent cancelEvent = null;
    final int action = MotionEventCompat.getActionMasked(ev);
    // Make sure we reset in case we had missed a previous important event.
    if (action == MotionEvent.ACTION_DOWN) {
        //down的时候，跟大部分ViewGroup一样，需要重置一些状态以及变量，比如 mBehaviorTouchView
        resetTouchBehaviors();
    }
    //这里看performIntercept TYPE_ON_INTERCEPT标记是 onInterceptTouchEvent
    final boolean intercepted = performIntercept(ev, TYPE_ON_INTERCEPT);
    if (cancelEvent != null) {
        cancelEvent.recycle();
    }
    //当事件为UP和Cancel的时候去重置（同down）
    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
        resetTouchBehaviors();
    }
    return intercepted;
}
```


`onTouchEvent`的实现：  

```
@Override
public boolean onTouchEvent(MotionEvent ev) {
    boolean handled = false;
    boolean cancelSuper = false;
    MotionEvent cancelEvent = null;
    final int action = MotionEventCompat.getActionMasked(ev);
    // mBehaviorTouchView不为null（代表之前有behavior处理了down事件） 或者 performIntercept返回true 那么事件就交给mBehaviorTouchView
    if (mBehaviorTouchView != null || (cancelSuper = performIntercept(ev, TYPE_ON_TOUCH))) {
        // Safe since performIntercept guarantees that
        // mBehaviorTouchView != null if it returns true
        final LayoutParams lp = (LayoutParams) mBehaviorTouchView.getLayoutParams();
        final Behavior b = lp.getBehavior();
        if (b != null) {
            // 交给 behavior去处理事件  
            handled = b.onTouchEvent(this, mBehaviorTouchView, ev);
        }
    }
    // Keep the super implementation correct
    // 省略调用默认实现 up&cancel的时候重置状态
    //...
    return handled;
}
```

可以看到，其实这两个方法做的事情并不多，其实都交给`performIntercept`方法去做处理了！  

`performIntercept`的实现如下：  

```
// type 标记是intercept还是touch
private boolean performIntercept(MotionEvent ev, final int type) {
    boolean intercepted = false;
    boolean newBlock = false;
    MotionEvent cancelEvent = null;
    final int action = MotionEventCompat.getActionMasked(ev);
    final List<View> topmostChildList = mTempList1;
    //按Z轴排序 原因很简单 让最上面的View先处理事件  
    getTopSortedChildren(topmostChildList);
    // Let topmost child views inspect first 
    final int childCount = topmostChildList.size();
    for (int i = 0; i < childCount; i++) {
        final View child = topmostChildList.get(i);
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        final Behavior b = lp.getBehavior();
        //当前事件已经被某个behavior拦截了（or newBlock），并且事件不为down，那么就发送一个 取消事件 给所有在拦截的behavior之后的behavior
        if ((intercepted || newBlock) && action != MotionEvent.ACTION_DOWN) {
            // Cancel all behaviors beneath the one that intercepted.
            // If the event is "down" then we don't have anything to cancel yet.
            if (b != null) {
                if (cancelEvent == null) {
                    final long now = SystemClock.uptimeMillis();
                    cancelEvent = MotionEvent.obtain(now, now,
                            MotionEvent.ACTION_CANCEL, 0.0f, 0.0f, 0);
                }
                switch (type) {
                    case TYPE_ON_INTERCEPT:
                        b.onInterceptTouchEvent(this, child, cancelEvent);
                        break;
                    case TYPE_ON_TOUCH:
                        b.onTouchEvent(this, child, cancelEvent);
                        break;
                }
            }
            continue;
        }
        // 如果还没有被拦截，那么继续询问每个Behavior 是否要处理该事件
        if (!intercepted && b != null) {
            switch (type) {
                case TYPE_ON_INTERCEPT:
                    intercepted = b.onInterceptTouchEvent(this, child, ev);
                    break;
                case TYPE_ON_TOUCH:
                    intercepted = b.onTouchEvent(this, child, ev);
                    break;
            }
            //如果有behavior处理了当前的事件，那么把它赋值给mBehaviorTouchView,它其实跟ViewGroup源码中的mFirstTouchTarget作用是一样的
            if (intercepted) {
                mBehaviorTouchView = child;
            }
        }
        // Don't keep going if we're not allowing interaction below this.
        // Setting newBlock will make sure we cancel the rest of the behaviors.
        // 是否拦截一切在它之后的交互 好暴力-0-
        final boolean wasBlocking = lp.didBlockInteraction();
        final boolean isBlocking = lp.isBlockingInteractionBelow(this, child);
        newBlock = isBlocking && !wasBlocking;
        if (isBlocking && !newBlock) {
            // Stop here since we don't have anything more to cancel - we already did
            // when the behavior first started blocking things below this point.
            break;
        }
    }
    topmostChildList.clear();
    return intercepted;
}
```

通过分析源码，可以知道，Col在关键的方法里把处理权优先交给了Behavior，所以才让Behavior拥有了拦截一切的能力，所以，原来是Col放任了Behavior！！~  

## 结语

Col以及Behavior的重要的几个环节分析完毕，相信大家看完后能够对它们有更深层次的了解，而不是仅仅停留在使用上面。  

这篇文章断断续续写了快一个月，思路断断续续，也有几次推翻重来，原本也打算想讲得更多更细一些，只是限于篇幅与精力，有些内容没有写，最终的效果跟我最初的预期有所差距，可能也有些错误或者讲解不清晰的地方。如有可能，会开下一篇继续讲解。    

如果你发现任何错误，或者写得不好的地方，或者不理解的地方，非常欢迎批评指正，也非常欢迎吐槽！！！！

其实我还顺带看了AppBarLayout等的源码，如有可能，我还想把Design库下的所有控件都分析一遍。  

感谢你的阅读。 


## 推荐阅读  
[Intercepting everything with CoordinatorLayout Behaviors
](https://medium.com/google-developers/intercepting-everything-with-coordinatorlayout-behaviors-8c6adc140c26#.p1v2t3rxo)  

[CoordinatorLayout.Behavior](https://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.Behavior.html)  

<http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0224/3991.html>
