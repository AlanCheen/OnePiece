# HashMap整理

> Java 7

Map的实现，使用数组存储(加链表)，线程不安全，支持 null 作为 key 与 value。

- capacity 容量，2的 N次方
- loadFactor 加载因子，散列表的实际元素数目(n)/ 散列表的容量(m)，默认0.75，比如容量为16，当数据超过12的时候就需要扩充容量了，每次翻一倍。（resize）
	- 因子越大 可填满的元素越多，空间利用高了，但是这样碰撞的概率会越大；
	- 因子小，碰撞的机会小，但是浪费的空间大。
- threshold 阈值，threshold = capacity*loadFactor；

	PS：resize 非常耗性能，因为需要把原来的数据都重新计算并放入新的位置。所以预估容量，减少 resize 的次数能够提高 HashMap 的性能。


如何查找元素？

通过 Key 的 hashCode 查找 table 的位置，再通过 key 的 equals 去获取 value。

所以Key 的 hashCode 以及 equals 显得格外重要。

hashCode即要性能好 又要尽量把不同的对象计算出不同的 hashCode（更加散列，减少碰撞）；而 equals 是要尽量让相同的对象获得相同的值。

如何扩容？
每次扩充为两倍；

影响 HashMap 的性能因数？如何提高性能？

1.1. 减少碰撞  

我们知道HashMap的时间复杂度取决于O(N//buckets)，所以为了尽量少挂链表，获取hashCode的算法一定要高效；在JDK8中引入了红黑二叉树，当链表元素达到8的时候，把链表动态转成树，可以把最差时间复杂度从O(N)降到O(logN)

1.2. 选好初始容量  

resize需要遍历所有的数组并重新计算Hash，所以初始容量确定了，就可以减少损失。

比如你有1000个数据 x * 0.75 = 1000 ,x = 1333，又HashMap中都是按照2^N排序的，所以应该选择2048作为初始容量。

1.3. 数据结构的选择
当数据非常多，对搜索要求高的情况下，考虑使用树。


HashMap hash碰撞如何解决？


HashMap 的攻击？
put N 多 hashCode 一样但是 equals 不一样的对象。


已知数据个数， 如何高效使用 HashMap?

已知个数，则选择好最初的容量，减少 resize 的次数。


多线程问题

因为 HashMap 多线程不安全，所以多线程下可以使用 以下类：

ConcurrentHashMap 最佳 锁的粒度更小 性能更好  
SynchronizedMap 给每个（？）操作都加了一个 mutex 锁（互斥锁）  
Hashtable 给每个方法前加了synchronized（性能差） 
 
[HashMap的实现与优化](http://www.jianshu.com/p/e54047b2b563)  
[HashMap有哪些考察点](https://github.com/android-cn/android-discuss/issues/109)  
[Java集合---HashMap源码剖析](http://www.cnblogs.com/ITtangtang/p/3948406.html)  
