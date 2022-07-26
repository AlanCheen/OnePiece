# OnePiece


## 使用

在项目根 build.gradle 加上 jetpack 的 maven 地址：
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

在模块中添加依赖：
```
implementation 'com.github.AlanCheen.OnePiece:architecture:1.0.2.1'
implementation 'com.github.AlanCheen.OnePiece:fantasy:1.0.2.1'
implementation 'com.github.AlanCheen.OnePiece:pandora:1.0.2.1'
```

最新版本请查阅 GitHub Release：https://github.com/AlanCheen/OnePiece/releases

## Architecture

关于架构的基础封装。
- ui layer
- data layer
- domain layer

Base and Common classes

## Pandora

KTX & Utils

## Fantasy

UI widgets 。

自定义控件、动画等。

Design


## 规范记录

命名规范：

- 动词 + 名词
- isXXX 

runOnMainThread 优于 mainThread，虽然 mainThread 写起来更方便，但是不是很明确


