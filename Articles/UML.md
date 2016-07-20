# UML类图

UML，进阶必备专业技能，看不懂UML就会看不懂那些优秀的资料。   
 
这里简单整理  

## 类之间的关系

### 泛化关系(generalization)

1. 泛化（generalize）: 一条**带空心箭头**的线表示  
2. 实现（realize） : 一条**带空心箭头的虚线**表示


**泛化**在Java中表现为**继承（extends）** ，`is-a`的关系  

**实现**在Android中表现为`implements` 接口，或者`extends`抽象类   

### 依赖关系(dependency)

用一条**带箭头的虚线**表示；箭头的指向为调用关系    

简单来说，**类A用到了类B，就可以说A依赖于B**   

依赖关系是一种临时性的偶然性的关系，通常在运行期间产生，并且随着运行时的变化； 依赖关系也可能发生变化  

代码中的表现：比如方法的入参，构造方法的参数

### 关联关系(association)

用**一条直线表示**  

它描述不同类的对象之间的结构关系；体现的是两个类、或者类与接口之间语义级别的一种**强依赖关系**  
 
代码中的表现：关联对象通常是以**成员变量的形式**实现的  

### 聚合关系（aggregation）

**一条带空心菱形箭头的直线表示**  

聚合关系是关联关系的特例，它用于表示实体对象之间的关系，表示**整体由部分构成的语义**；是`has-a`的关系；

例如一个部门由多个员工组成；

与组合关系不同的是，**整体和部分不是强依赖的**，**整体与部分之间是可分离的**，即使整体不存在了，部分仍然存在；

例如， 部门撤销了，人员不会消失，他们依然存在  

### 组合关系(composition)

用一条**带实心菱形箭头直线**表示   

组合关系是一种**强依赖的特殊聚合关系**，是`contains-a`的关系，比聚合更强，也称为**强聚合**，如果**整体不存在了，则部分也不存在**


#### 注意点

1. 聚合跟组合其实都属于关联
2. 关系强度：组合>聚合>关联>依赖


## 类的方法与属性  

类可以拥有多个方法和属性 

属性的表达方式：**可见性  名称:类型 [ = 缺省值 ]**  
方法的表达方式：**可见性  名称(参数列表) [ : 返回类型]**  


可见性：  

* `+` public  
* `-` private  
* `#` protected  
* `~` package  

![示例](http://ww2.sinaimg.cn/large/98900c07jw1f5z65eylbij20cx068wev.jpg)

## 示例

![](http://ww2.sinaimg.cn/large/98900c07jw1f5z1qwizmuj20j3095q4f.jpg)


释义：
`ViewGroup`是一个`View`，也是一个`ViewParent`，依赖于`MotionEvent`（在`onTouch（MotionEvent）`等方法）


![](http://ww1.sinaimg.cn/large/98900c07jw1f5z1rdtd4wj20dt07edgk.jpg)  


释义：  

员工与工号相关联，员工聚合成部门，部门组成公司。

部门解散了员工依然存在（所以是聚合），而公司倒闭，部门也就不存在了（所以是组成）。  

# 资料
[看懂UML类图和时序图](http://design-patterns.readthedocs.io/zh_CN/latest/read_uml.html#id1)  
[UML类图关系](http://www.cnblogs.com/olvo/archive/2012/05/03/2481014.html)  
[ 深入浅出UML类图](http://blog.csdn.net/lovelion/article/details/7838679)  

