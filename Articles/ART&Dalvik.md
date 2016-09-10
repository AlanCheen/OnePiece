# ART&Dalvik


## Dalvik

Dalvik 是 Google 设计用于 Android 平台的 Java 虚拟机。

可以支持 .dex(Dalvik Executable)格式的 Java 应用程序的运行；
dex 格式是专门为 Dalvik 设计的一种压缩格式，适合内存和处理器速度有限的系统；

Dalvik 经过优化，允许在有限的内存中同时运行多个虚拟机的实例，并且每一个 Dalvik 应用作为一个独立的 Linux 进程执行；

独立的进程可以防止在虚拟机崩溃的时候所有程序都被关闭。

在**每次执行应用**时，Dalvik虚拟机都会将程序的语言由高级语言编译为机器语言，Dalvik依靠一个Just-In-Time(JIT)编译器去解释字节码，每次运行程序都需要做上述操作，将**字节码编译为机器语言**；所以很长时间以来，Dalvik虚拟机一直被用户指责为拖慢安卓系统运行速度不如IOS的根源。

开发者编译后的应用代码需要通过一个解释器在用户的设备上运行，这一机制并不高效，但让应用能更容易在不同硬件和架构上运行。

## ART

	Android4.4开始引入，5.0彻底从Dalvik转换到ART。  

ART代表Android Runtime，在**应用安装时就预编译字节码到机器语言**，这一机制叫`Ahead-Of-Time (AOT）`预编译。在移除Dalvik的解释代码这一过程后，应用程序执行将更有效率，启动更快。

> 预编译，在安装应用程序时会先将代码转换为机器语言存储在本地，这样运行程序时就不会每次都进行一次编译了，将提高执行效率。  

ART 使用 AOT 在安装 时用 dex2oat 完全翻译成 native code

ART 的功能：

##### AOT
1. ART引入了AOT，能够提升 App 性能，并且拥有更严格的安装时检查。
2. 在安装的时候，ART 使用 **dex20at**工具编译,把 DEX 文件编译后，生成设备能执行代码。但是有部分文件可能 ART 编译不了，所以 ART 的兼容性比 Dalvik 更差一些。

##### 提升 GC

GC 是会损害 App 性能的，会导致UI卡顿 等其他问题。

ART在以下方面提升了 GC：

1. One GC pause instead of two 
2. Parallelized processing during the remaining GC pause
3. Collector with lower total GC time for the special case of cleaning up recently-allocated, short-lived objects
4. Improved garbage collection ergonomics, making concurrent garbage collections more timely, which makes GC_FOR_ALLOC events extremely rare in typical use cases
5. Compacting GC to reduce background memory usage and fragmentation





ART优点：
1. 系统性能的显著提升。
2. 应用启动更快、运行更快、体验更流畅、触感反馈更及时。
3. 更长的电池续航能力。
4. 支持更低的硬件。
5. App 的启动速度更快。

ART缺点：
1、更大的存储空间占用，可能会增加10%-20%。
2、更长的应用安装时间。


| 对比 | Dalvik | ART |
| :-: | :-: | :-: |
| 编译器 |  JIT即时编译 |  AOT静态编译 |
| 何时编译成机器码 | 每次启动 | 安装时 |
|  RAM内存 | 大 | 小 |
| ROM内存 | 大 | 小 |
| 流畅度| 普通 | 好 |
| 省电 | 普通 | 好 |
| 兼容性 | 好 | 有待优化 |
| 加载速度 | 慢 | 快 |
|  |  |  |
|  |  |  |




[Android Runtime (ART)](https://developer.android.com/about/versions/android-5.0-changes.html#ART)  
[ART and Dalvik](https://source.android.com/devices/tech/dalvik/)  
[ Android运行环境Dalvik模式和ART模式的区别](http://blog.csdn.net/wxdjaqgs/article/details/44303461)  
[art和dalvik的区别？](https://www.zhihu.com/question/29406156)

