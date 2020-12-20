# advanced-java-skills
1.对于java工程师相关技术进行学习<br/>
2.部分技术进行原理深入<br/>

## 学习方向
###依赖于每天的 浏览器收藏的 "学习" 文件夹 的收藏内容<br/>
1.基于这个点扩展整理回顾自己以前收藏的3000+网址<br/>
2.同步整理笔记于 [项目相关技术笔记](https://github.com/892042158/xmind-common)<br/>
3.同步博客于[博客](https://blog.csdn.net/mengxiangxingdong)<br/>

## 约定
### 0.拒绝封装工具类
 既然是学习，所以尽量都用原生的，在实际开发中再用项目的工具类相关
### 1.包名称约定
#### src.main.java<br/>
1.top.xmindguoguo.skills.config 存储对应的开源技术的配置<br/>
2.top.xmindguoguo.skills.utils  存储对应的工具类<br/>
3.top.xmindguoguo.skills.demo.xx   存放所学习相关技术  xx代码相关的jar或者某一个技术点名称 例如spring
4.top.xmindguoguo.skills.demo.xx.ext  存放基于源码的自定义扩展

#### src.main.resources

#### test.main.java
测试类统一为 xxTest


### 2.代码存放约定
#####1.小的点需要分开检验的 放在单元测试,相关通用部分放在demo.xx下<br/>
例如 学习 commons-lang-2.6.jar 的StringUtils类的使用<br/>
1.在top.xmindguoguo.skills.demo包下创建commons-lang包 初次新建CommonsLangMain 类 <br/>
2.在单元测试test的java包下 创建top.xmindguoguo.skills.demo.你的使用类的包名称 这里为org.apache.commons.lang <br/>
现在看起来长一些，但是利于维护






    
    

	
