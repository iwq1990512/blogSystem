

> 使用Java语言开发的博客系统<br>

## 部署环境前提
* jdk7
* git
* maven
* tomcat
* mysql


## 部署到线上（生产）
1. 复制 /src/main/resources/cms.properties 到 /
2. 修改 cms.properties 里的数据库链接、用户名和密码为生产环境的值
3. mvn package
4. 生成/dist文件夹，此文件夹为生产环境的编译目录

## 功能

### 首页头条
* 滚动大图上传
* 可设置链接、标题

### 目录
* 无层级限制
* 目录拥有自己的内容
* 可设置此目录的所有文章的封面
* 可设置是否需要审核

### 文章
* 文章管理，整合百度UEditor，
* 自动缩小和裁剪文章封面图片
* 可定义文章发布时间
* 可设置文章摘要

### 管理员
* 设置超级管理员
* 增加管理员
* 分配管理员拥有的目录权限
* 修改密码

### 其他
* 使用标签，方便前端模板开发
* 使用注解，方便二次开发


## 技术关键词
* jQuery
* Bootstrap
* Java
* Maven
* Spring
* Spring MVC
* MyBatis
* MySQL
* FreeMarker
* Lucene

## 需求

 - 文章列表模块
 - 图片展示模块
 - 文件下载模块
 - 电子商务模块
 - 用户注册登录模块
 - 后台管理模块

