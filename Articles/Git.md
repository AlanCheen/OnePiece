# 高效地使用Git


![](http://ww2.sinaimg.cn/large/98900c07jw1f6akqjacr3j20je088dgf.jpg)

- workspace/working directory 工作区/工作目录（就是你写代码的地方）
- index/stage  暂存区
- repository 本地git仓库
- remote 远程git仓库
# 配置

## 基本配置


`--global`代表全局 


```
# 用户名
git config --global user.name "yifeiyuan"  
# 邮箱
git config --global user.email yifeiyuan@gmail.com
# 设置大小写敏感
git config --global core.ignorecase false
# 设置支持显示中文名
git config --global core.quotepath false
# 查看所有配置
git config --list  
```

## 别名

配置常用的别名，提高效率。  

```
# checkout
git config --global alias.co checkout
# checkout
git config --global alias.gco "git checkout"
# commit
git config --global alias.ci commit
# status
git config --global alias.st status
# branch
git config --global alias.br branch
# push
git config --global alias.gpo "git push origin"
# pull
git config --global alias.glo "git pull origin"
# 更漂亮的log
git config --global alias.lg "log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative"
```



# Merge

<<<<<<<<<< HEAD 到 ======== 是当前所在分支的内容  
======= 到 >>>>>>>> 是合并过来的分支的内容




## Stash

```
git stash 
git stash pop
git stash list
```


## 资料
[常用 Git 命令清单](http://www.ruanyifeng.com/blog/2015/12/git-cheat-sheet.html)   
[Chap2.2](https://git-scm.com/book/zh/v1/Git-%E5%9F%BA%E7%A1%80-%E8%AE%B0%E5%BD%95%E6%AF%8F%E6%AC%A1%E6%9B%B4%E6%96%B0%E5%88%B0%E4%BB%93%E5%BA%93)