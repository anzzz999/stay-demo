# ElasticSearch

#### 基本概念

- **索引是ElasticSearch存放数据的地方，可以理解为关系型数据库中的一个数据库**。事实上，我们的数据被存储和索引在分片(shards)中，索引只是一个把一个或多个分片分组在一起的逻辑空间。然而，这只是一些内部细节——我们的程序完全不用关心分片。对于我们的程序而言，**文档存储在索引(index)中**。剩下的细节由Elasticsearch关心既可。（索引的名字必须是全部小写，不能以下划线开头，不能包含逗号）
- **类型用于区分同一个索引下不同的数据类型,相当于关系型数据库中的表。**在Elasticsearch中，我们使用相同类型(type)的文档表示相同的“事物”，因为他们的数据结构也是相同的。每个类型(type)都有自己的映射(mapping)或者结构定义，就像传统数据库表中的列一样。所有类型下的文档被存储在同一个索引下，但是类型的映射(mapping)会告诉Elasticsearch不同的文档如何被索引。
- **文档是ElasticSearch中存储的实体，类比关系型数据库，每个文档相当于数据库表中的一行数据。** 在Elasticsearch中，文档(document)这个术语有着特殊含义。它特指最顶层结构或者根对象(root object)序列化成的JSON数据（以唯一ID标识并存储于Elasticsearch中）。
- **文档由字段组成，相当于关系数据库中列的属性**，不同的是ES的不同文档可以具有不同的字段集合。 
  对比关系型数据库：

```
Relational DB -> Databases -> Tables -> Rows -> Columns



Elasticsearch -> Indices -> Types -> Documents -> Fields
```



#### **简单介绍下ES？**

​	**ES是一种开源、RESTful、可扩展的基于文档的搜索引擎**，它构建在Lucene库上。用户**通过JSON格式的请求，使用CRUD的REST API**就可以完成存储和管理文本、数值、地理空间、结构化或者非结构化的数据。

用户使用Kibana就可以可视化使用数据，同时Kibana也提供交互式的数据状态呈现和数据分析。



####  **ES的节点是什么？**

**A:** **节点就是ES的实例。节点有不同类型，主节点（可选举节点），客户端节点，Ingest节点和Tribe节点（部落节点）**

- 主节点（可选举节点）：这样的节点可被集群选举为主节点，从而获得集群的控制权。
- 数据节点：数据节点存储数据并进行相关的数据操作，比如CRUD，搜索和聚合。
- Ingest节点：用于建索引前的预处理。
- Tribe节点（部落节点）：在多个集群之间进行协调的特殊节点，可以集群之间进行各种操作



**索引是什么?**

**A:** **ES集群包含多个索引，每个索引包含一种表，表包含多个文档，并且每个文档包含不同的属性。**



**请解释什么是分片(SHARDs)?**

**A:** 随着索引文件的增加，磁盘容量、处理能力都会变得不够，在这种情况下，**将索引数据切分成小段，这就叫分片(**SHARDS)。它的出现大大改进了数据查询的效率。



**什么是副本(REPLICA), 他的作用是什么？**

**A:** **副本是分片的完整拷贝，副本的作用是增加了查询的吞吐率和在极端负载情况下获得高可用的能力。**副本有效的帮助处理用户请求。



**ES支持哪些类型的查询？**

**A:** 主要分为匹配（文本）查询和基于Term的查询。

文本查询包括基本匹配，match phrase, multi-match, match phrase prefix, common terms, query-string, simple query string.

Term查询，比如term exists, type, term set, range, prefix, ids, wildcard, regexp, and fuzzy.





#### ES 在数据量很大的情况下（数十亿级别）如何提高查询效率？

**1 增大内存：** es性能优化的杀手锏：`filesystem cache(OS cache)：`也就是说 尽量让内存可以容纳所有的索引数据文件，那么搜索的时候就基本都是走内存的，性能会非常高。磁盘和OS cache扫描速度相差近一个数量级，可能一个是1到几百毫秒，另一个是秒。最佳的情况下，就是单机机器的内存，至少可以容纳单机数据量的一半。另一个方面就是写数据的时候，仅仅写入要用来检索的少数几个字段就可以了，其余的数据放到hbase或者mysql上

**2 数据预热**
 假设机器内存达到上面的要求，比如 内存是100G，数据是200G。那么有一半的数据存放在磁盘上，那么这个时候可以设计一个**数据预热子系统，**就是对热数据每隔一段时间，就提前访问一下，让热数据进入 filesystem cache 里面去。这样下次别人访问的时候，性能一定会好很多

**3 document 模型设计**
 document 模型设计是非常重要的，很多操作，不要在搜索的时候才想去执行各种复杂的乱七八糟的操作，尽量存放单纯的数据放到ES上去，不要考虑用 es 做一些它不好操作的事情，比如 join/nested/parent-child 搜索都要尽量避免，性能都很差的。

**4 分页性能优化**
 分页性能差的原因：https://www.jianshu.com/p/e4da06b55e63
 解决方案1:跟产品经理说，你系统不允许翻那么深的页，默认翻的越深，性能就越差。
 解决方案2:类似于 app 里的推荐商品不断下拉出来一页一页的
 就像淘宝商品一样，一页一页往下刷，不能从第一页跳到100页，从100页跳到50页，不能这样操作。
 可以使用`scroll api`来实现，scroll 会一次性给你生成所有数据的一个快照，然后**每次滑动向后翻页就是通过游标 scroll_id 移动，获取下一页下一页这样子，性能会比上面说的那种分页性能要高很多很多，基本上都是毫秒级的。**
 初始化时必须指定 scroll 参数，告诉 es 要保存此次搜索的上下文多长时间。你需要确保用户不会持续不断翻页翻几个小时，否则可能因为超时而失败。
 除了用 scroll api，你也可以用 search_after 来做，search_after 的思想是使用前一页的结果来帮助检索下一页的数据，显然，这种方式也不允许你随意翻页，你只能一页页往后翻。初始化时，需要使用一个唯一值的字段作为 sort 字段。参考：[https://www.cnblogs.com/hello-shf/p/11543453.html#4527510](https://links.jianshu.com/go?to=https%3A%2F%2Fwww.cnblogs.com%2Fhello-shf%2Fp%2F11543453.html%234527510)



要让 es 性能要好，最佳的情况下，就是你的机器的内存，至少可以容纳你的总数据量的一半。



#### **es 写数据底层原理**

![img](https://upload-images.jianshu.io/upload_images/12775885-a9b8635add95f961.png?imageMogr2/auto-orient/strip|imageView2/2/w/828/format/webp)

先写入内存 buffer，在 buffer 里的时候数据是搜索不到的；同时将数据写入 translog 日志文件。

如果 buffer 快满了，或者到一定时间，就会将内存 buffer 数据 refresh 到一个新的 segment file 中，但是此时数据不是直接进入 segment file 磁盘文件，而是先进入 os cache 。这个过程就是 refresh。只要数据进入了OS cache那么就可以被访问到了。

每隔 1 秒钟，es 将 buffer 中的数据写入一个新的 segment file**（如果 buffer 里面此时没有数据，那当然不会执行 refresh 操作）**，每秒钟会产生一个新的磁盘文件 segment file，这个 segment file 中就存储最近 1 秒内 buffer 中写入的数据。

这里就解释了为什么叫 es 是**准实时**的？ NRT，全称 near real-time。默认是每隔 1 秒 refresh 一次的，所以 es 是准实时的，因为写入的数据 1 秒之后才能被看到。可以通过 es 的 restful api 或者 java api，手动执行一次 refresh 操作，就是手动将 buffer 中的数据刷入 os cache中(但是这样会影响ES批量插入数据的效率)，让数据立马就可以被搜索到。只要数据被输入 os cache 中，buffer 就会被清空了，因为不需要保留 buffer 了，数据在 translog 里面已经持久化到磁盘去一份了。

重复上面的步骤，新的数据不断进入 buffer 和 translog，不断将 buffer 数据写入一个又一个新的 segment file 中去，每次 refresh 完 buffer 清空，translog 保留。随着这个过程推进，translog 会变得越来越大。当 translog 达到一定长度的时候，就会触发 commit 操作。

commit 操作发生第一步，就是将 buffer 中现有数据 refresh 到 os cache 中去，清空 buffer。然后，将一个 commit point 写入磁盘文件，里面标识着这个 commit point 对应的所有 segment file，同时强行将 os cache 中目前所有的数据都 fsync 到磁盘文件中去。最后清空 现有 translog 日志文件，重启一个 translog，此时 commit 操作完成。

这个 commit 操作叫做 flush。默认 30 分钟自动执行一次 flush，但如果 translog 过大，也会触发 flush。flush 操作就对应着 commit 的全过程，我们可以通过 es api，手动执行 flush 操作，手动将 os cache 中的数据 fsync 强刷到磁盘上去。

ranslog 日志文件的作用是什么？你执行 commit 操作之前，数据要么是停留在 buffer 中，要么是停留在 os cache 中，无论是 buffer 还是 os cache 都是内存，一旦这台机器死了，内存中的数据就全丢了。所以需要将数据对应的操作写入一个专门的日志文件 translog 中，一旦此时机器宕机，再次重启的时候，es 会自动读取 translog 日志文件中的数据，恢复到内存 buffer 和 os cache 中去 **这里和Redis持久化机制是类似的**。

translog 其实也是先写入 os cache 的，默认每隔 5 秒刷一次到磁盘中去，所以默认情况下，可能有 5 秒的数据会仅仅停留在 buffer 或者 translog 文件的 os cache 中，如果此时机器挂了，会丢失 5 秒钟的数据。但是这样性能比较好，最多丢 5 秒的数据。也可以将 translog 设置成每次写操作必须是直接 fsync 到磁盘，但是性能会差很多。

总结一下，**数据先写入内存 buffer，然后每隔 1s，将数据 refresh 到 os cache，到了 os cache 数据就能被搜索到（所以我们才说 es 从写入到能被搜索到，中间有 1s 的延迟）。每隔 5s，将数据写入 translog 文件(磁盘里面)（这样如果机器宕机，内存数据全没，最多会有 5s 的数据丢失），translog 大到一定程度，或者默认每隔 30mins，会触发 commit 操作，将缓冲区的数据都 flush 到 segment file 磁盘文件中。**

> 数据写入 segment file 之后，同时就建立好了倒排索引。



#### **es 读数据与搜索数据过程**

 **读**

可以通过 doc id 来查询，会根据 doc id 进行 hash，判断出来当时把 doc id 分配到了哪个 shard 上面去，从那个 shard 去查询。

客户端发送请求到任意一个 node，成为 coordinate node。
 coordinate node 对 doc id 进行哈希路由，将请求转发到对应的 node，此时会使用 round-robin 随机轮询算法，在 primary shard 以及其所有 replica 中随机选择一个，让读请求负载均衡。
 接收请求的 node 返回 document 给 coordinate node。
 coordinate node 返回 document 给客户端。

 **搜索**
 es 最强大的是做全文检索，就是比如你有三条数据：
 java真好玩儿啊
 java好难学啊
 j2ee特别牛
 你根据 java 关键词来搜索，将包含 java的 document 给搜索出来。es 就会给你返回：java真好玩儿啊，java好难学啊。
 1 客户端发送请求到一个 coordinate node。
 2 协调节点将搜索请求转发到所有的 shard 对应的 primary shard 或 replica shard，都可以。
 3 query phase：每个 shard 将自己的搜索结果（其实就是一些 doc id）返回给协调节点，由协调节点进行数据的合并、排序、分页等操作，产出最终结果。
 4 fetch phase：接着由协调节点根据 doc id 去各个节点上拉取实际的 document 数据，最终返回给客户端

#### **es 删除/更新数据底层原理**

 如果是删除操作，commit 的时候会生成一个 .del 文件，里面将某个 doc 标识为 deleted 状态，那么搜索的时候根据 .del 文件就知道这个 doc 是否被删除了。

如果是更新操作，就是将原来的 doc 标识为 deleted 状态，然后新写入一条数据。
 buffer 每 refresh 一次，就会产生一个 segment file，所以默认情况下是 1 秒钟一个 segment file，这样下来 segment file 会越来越多，此时会定期执行 merge。每次 merge 的时候，会将多个 segment file 合并成一个**(这里类似于Redis的RDB文件重写)**，同时这里会将标识为 deleted 的 doc 给**物理删除掉**，然后将新的 segment file 写入磁盘，这里会写一个 commit point，标识所有新的 segment file，然后打开 segment file 供搜索使用，同时删除旧的 segment file。





#### ES的keyword类型和text类型的区别

ES中的keyword类型,和MySQL中的字段基本上差不多,**当我们需要对查询字段进行精确匹配,左模糊,右模糊,全模糊,排序聚合等操作时,需要该字段的索引类型为keyword类型。**

**当我们需要对字段进行分词查询时,需要该字段的类型为text类型**,并且指定分词器(不指定就用ES默认分词器,效果通常不理想)