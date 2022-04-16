Klock组件的源码学习与使用



1.介绍

2.解决的问题

3.使用及使用注意事项 （AOP限制、切面优先级、redisson主从/哨兵可能存在的问题）

4.原理（自定义注解、AOP、redisson）

5.代码组成（分析）

6.代码组成（设计）

7.代码组成（实现）

8.代码快速集成和配置原理spring-xxx-start





### 一、介绍

**Klock是开源的基于redis的分布式锁spring-boot starter组件**，使得项目拥有分布式锁能力。

源码地址：https://github.com/kekingcn/spring-boot-klock-starter



### 二、解决的问题

使用Klock可以保证分布式服务在运行时，各个服务中有且仅有运行着相同的一个任务，从而避免对共享资源的竞争（比如db等），防止脏数据和异常情况的产生。

意义：实现了单机锁所无法实现的集群锁能力，解决了分布式环境下锁的需求。



### 三、使用方法及主要技术

**使用方法**

例子：

```java
@Klock(name = "annotationName", keys = {"#key1"}, isLockedStrategy = IsLockedStrategy.FAIL_FAST)
public void Method（String key1, @KlockKey key2）{

}
```

**主要技术**

1. 自定义注解

2. Spring AOP

3. redisson

   

### 四、切面代码主要流程

org.springframework.boot.autoconfigure.klock.core.KlockAspectHandler

![image-20220326162801029](C:\Users\99512\Desktop\image-20220326162801029.png)



### 五、使用的注意事项

#### 1.AOP的限制

问题

1）**调用private方法注解不生效**

2）**本类内部直接调用方法注解不生效**

以上两个问题都是AOP原理所限制的，AOP是使用动态代理将切面代码进行织入的，有两个使用原则：

①注解应该只被应用到 public 方法上 

②只有来自外部的方法调用才会被AOP代理捕获。（本类内部调用可使用特殊方法解决）



#### 2.切面优先级的限制

一般项目中都会有多个AOP切面，执行顺序可以通过@Order(数字)进行排序，数字越小，执行优先级越大。两个切面@Order相同，spring不保证执行顺序。

当然，项目中不止AOP,还可能有过滤器、拦截器等，那在以上都有的情况下，执行顺序是怎么样的呢?

下图能比较好的解释：

<img src="https://img2020.cnblogs.com/blog/1034738/202004/1034738-20200421101638753-296721912.png" alt="img" style="zoom:67%;" />





#### 3.redis主从/哨兵问题

在Redis主从/哨兵模式下:

`客户端1` 对某个`master节点`写入了redisson锁，此时会异步复制给对应的 slave节点。但是这个过程中一旦发生 master节点宕机，主备切换，slave节点从变为了 master节点。

这时`客户端2` 来尝试加锁的时候，在新的master节点上也能加锁，此时就会导致多个客户端对同一个分布式锁完成了加锁。

这时系统在业务语义上一定会出现问题，**导致各种脏数据的产生**。

`缺陷`在哨兵模式或者主从模式下，如果 master实例宕机的时候，可能导致多个客户端同时完成加锁。



#### 4.加锁超时释放问题

Klock的 leaseTime() 默认为60秒（KlockConfig 中可见）。**当设置了leaseTime()时，时间到期就会释放对应的锁，这时候如果第一个业务没执行完，第二个业务又来申请相同的锁，是能申请到的，此时就会造成任务并发问题**。

当然，redisson是有看门狗机制的，需要我们将leaseTime设置成-1，就会进行“锁续命”，保证业务时间 > 超时时间。

而**一般在开发中，最佳实践其实推荐的是传入超时时间**，因为这样省去了创建定时任务不断续时的过程。把超时时间设的大一点就可以了，比如Klock默认的60秒，如果超过60秒业务还没完成就该想想怎么优化业务逻辑。



tip:有疑惑可以查看下面这篇文章：

Redis分布式锁原理（二）——Redisson分布式锁源码浅析

https://blog.csdn.net/h2503652646/article/details/119514951





### 六、其他

#### 1.代码快速集成和配置原理spring-xxx-start

**原理**
一个公用是starter我们只需要引入pom文件，SpringBoot就会进行自动配置。那么SpringBoot是如何知道要实例化哪些类，并进行简单配置呢？

首先，**SpringBoot在启动时会去依赖的Starter包中寻找resources/META-INF/spring.factories 文件，然后根据文件中配置的Jar包去扫描项目所依赖的Jar包**，这类似于 Java 的 SPI 机制。

第二步**，根据 spring.factories配置加载AutoConfigure类**，读取以**EnableAutoConfiguration**的全限定类名对应的值，作为候选配置类。

第三步，自动配置类可能会再导入一些依赖（比如**@Import**），或者给出一些配置条件，并且会通过@Bean注解把该组件所包含的组件注入到spring容器中以供使用。

以Klock为例：

<img src="C:\Users\99512\Desktop\image-20220309163253034.png" alt="image-20220309163253034" style="zoom:80%;" />

![image-20220309163508924](C:\Users\99512\Desktop\image-20220309163508924.png)



@Configuration
标记为配置类

@ConditionalOnProperty
通过@ConditionalOnProperty控制配置类是否生效

@AutoConfigureAfter
@AutoConfigureAfter 在加载配置的类之后再加载当前类

@EnableConfigurationProperties
使用 @ConfigurationProperties 注解的类生效。

@Bean / @Import 
第三方库jar包中的类要加入IOC容器的话，使用@Bean注解就可以做到。当然除了@Bean注解能做到还有@Import也能把第三方库中的类实例交给spring管理