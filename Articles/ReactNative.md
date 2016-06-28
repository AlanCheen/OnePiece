# ReactNative初探

博客写累了，玩玩 ReactNative！~  

## 环境安装

如果没有Homebrew就先安装brew：  

```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

有的话接下就去安装下面的工具：  
`Node`，`React Native Command Line Tools`，`XCode`，`Watchman`，`Flow`（已有的就不需要了）	  

```
brew install node
npm install -g react-native-cli
brew install watchman
brew install flow
```

## IDE [Nuclide](https://nuclide.io/) 
还有IDE需要下载，`Nuclide`基于`Atom`（以后Atom不再只是用来写博客了），由于Atom已经安装好了，so，也很简单  

```
apm install nuclide
```

安装完后会多出一栏Nuclide：

![Nuclide](http://ww1.sinaimg.cn/large/98900c07gw1f5b2x5763hj209i0dn75t.jpg)  


## Run Demo

所有工具都安装好了，可以跑demo试试了  

```
react-native init AwesomeProject
cd AwesomeProject
react-native run-ios
```

一切成功的话，可以看到下面的效果图：  

![demo-效果图](http://ww4.sinaimg.cn/large/98900c07gw1f5b2t1krqzj20af0j50t3.jpg)

over！  

## 资料  
[ReactNative](http://facebook.github.io/react-native/docs/getting-started.html#content)  
[react-native-guide](https://github.com/reactnativecn/react-native-guide)  
[reactnative.cn](http://reactnative.cn/docs/0.27/getting-started.html)  
