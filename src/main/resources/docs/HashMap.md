## HashMap

HashMap MAXIMUM_CAPACITY 为什么设置成1 << 30？
1.HashMap在确定数组下标Index的时候，采用的是( length-1) & hash的方式，只有当length为2的指数幂的时候才能较均匀的分布元素
。所以HashMap规定了其容量必须是2的n次方
2.由于HashMap规定了其容量是2的n次方，所以我们采用位运算<<来控制HashMap的大小。使用位运算同时还提高了Java的处理速度。
HashMap内部由Entry[]数组构成，Java的数组下标是由Int表示的。所以对于HashMap来说其最大的容量应该是不超过int最大值的一个2的指数幂，
而最接近int最大值的2个指数幂用位运算符表示就是 1 << 30

JDK1.8的hashMap

### put()方法

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191214222552803.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly90aGlua3dvbi5ibG9nLmNzZG4ubmV0,size_16,color_FFFFFF,t_70)

①.判断键值对数组table[i]是否为空或为null，否则执行resize()进行扩容；

②.根据键值key计算hash值得到插入的数组索引i，如果table[i]==null，直接新建节点添加，转向⑥，如果table[i]不为空，转向③；

③.判断table[i]的首个元素是否和key一样，如果相同直接覆盖value，否则转向④，这里的相同指的是hashCode以及equals；

④.判断table[i] 是否为treeNode，即table[i] 是否是红黑树，如果是红黑树，则直接在树中插入键值对，否则转向⑤；

⑤.遍历table[i]，判断链表长度是否大于8，大于8的话把链表转换为红黑树，在红黑树中执行插入操作，否则进行链表的插入操作；遍历过程中若发现key已经存在直接覆盖value即可；

⑥.插入成功后，判断实际存在的键值对数量size是否超多了最大容量threshold，如果超过，进行扩容。





#### 那在什么时候用链表？什么时候用红黑树？

​	对于插入，默认情况下是使用链表节点。当同一个索引位置的节点在新增后达到8个（阈值8）：如果此时数组长度大于等于 64，则会触发链表节点转红黑树节点（treeifyBin）；而如果数组长度小于64，则不会触发链表转红黑树，而是会进行扩容，因为此时的数据量还比较小。

​	对于移除，当同一个索引位置的节点在移除后达到 6 个，并且该索引位置的节点为红黑树节点，会触发红黑树节点转链表节点（untreeify）。



#### 为什么链表转红黑树的阈值是8？

​	我们平时在进行方案设计时，必须考虑的两个很重要的因素是：时间和空间。对于 HashMap 也是同样的道理，简单来说，阈值为8是在时间和空间上权衡的结果。

红黑树节点大小约为链表节点的2倍，在节点太少时，红黑树的查找性能优势并不明显。并且到8个节点时，红黑树的性能优势也会开始展现出来，因此8是一个较合理的数字。



#### 那为什么转回链表节点是用的6而不是复用8？

​	如果我们设置节点多于8个转红黑树，少于8个就马上转链表，当节点个数在8徘徊时，就会频繁进行红黑树和链表的转换，造成性能的损耗。



#### HashMap 的默认初始容量是多少？HashMap 的容量有什么限制吗？

​	默认初始容量是16。HashMap 的容量必须是2的N次方，**HashMap 会根据我们传入的容量计算一个大于等于该容量的最小的2的N次方**，例如传 9，容量为16。



#### 这个N次方是怎么算的？

```java
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```

我们先不看第一行“int n = cap - 1”，先看下面的5行计算。

|=（或等于）：这个符号比较少见，但是“+=”应该都见过，看到这你应该明白了。例如：a |= b ，可以转成：a = a | b。

\>>>（无符号右移）：例如 a >>> b 指的是将 a 向右移动 b 指定的位数，右移后左边空出的位用零来填充，移出右边的位被丢弃。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxZGRSeW52YUpNc2pGWk1aVDFERE03UGliVGtpYnRQQ1hXS1VnM2Q5TVVKc010b1RRc2ljZjlua3dnLw?x-oss-process=image/format,png)

相信你应该看出来，这5个公式会通过最高位的1，拿到2个1、4个1、8个1、16个1、32个1。当然，有多少个1，取决于我们的入参有多大，但我们肯定的是经过这5个计算，得到的值是一个低位全是1的值，最后返回的时候 +1，则会得到1个比n 大的 2 的N次方。

这时再看开头的 cap - 1 就很简单了，这是为了处理 cap 本身就是 2 的N次方的情况。

计算机底层是二进制的，移位和或运算是非常快的，所以这个方法的效率很高。



#### HashMap 的容量必须是 2 的 N 次方，这是为什么？

囧辉：**计算索引位置的公式为：(n - 1) & hash**，当 n 为 2 的 N 次方时，n - 1 为低位全是 1 的值，此时任何值跟 n - 1 进行 & 运算的结果为该值的低 N 位，达到了和取模同样的效果，实现了均匀分布。实际上，这个设计就是基于公式：x mod 2^n = x & (2^n - 1)，因为 & 运算比 mod 具有更高的效率。


#### HashMap 的默认初始容量是 16，为什么是16而不是其他的？

实际上，我们在新建 HashMap 时，**最好是根据自己使用情况设置初始容量**，这才是最合理的方案。



#### 为什么负载因子是0.75而不是其他的？

这个也是在时间和空间上权衡的结果。如果值较高，例如1，此时会减少空间开销，但是 hash 冲突的概率会增大，增加查找成本；而如果值较低，例如 0.5 ，此时 hash 冲突会降低，但是有一半的空间会被浪费，所以折衷考虑 0.75 似乎是一个合理的值。



#### PUT过程中有个计算 key 的 hash 值，是怎么设计的？

**拿到 key 的 hashCode，并将 hashCode 的高16位和 hashCode 进行异或（XOR）运算，得到最终的 hash 值。**



#### 为什么要将 hashCode 的高16位参与运算？

主要是为了在 table 的长度较小的时候，让高位也参与运算，并且不会有太大的开销。



#### 扩容（resize）流程介绍下？

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxb0Y0a0FsQU91c0s4SmlhS0Q3VVR5VzR2dE1CTWNCOTVZTXpKODZTdERaNDdkWmpsZXdiSVJFdy8?x-oss-process=image/format,png)



#### HashMap 是线程安全的吗？

不是。HashMap 在并发下存在数据覆盖、遍历的同时进行修改会抛出 ConcurrentModificationException 异常等问题，JDK 1.8 之前还存在死循环问题。



#### 总结下 JDK 1.8 主要进行了哪些优化？

JDK 1.8 的主要优化刚才我们都聊过了，主要有以下几点：

1）底层数据结构从“数组+链表”改成“数组+链表+红黑树”，主要是优化了 hash 冲突较严重时，链表过长的查找性能：O(n) -> O(logn)。

2）计算 table 初始容量的方式发生了改变，老的方式是从1开始不断向左进行移位运算，直到找到大于等于入参容量的值；新的方式则是通过“5个移位+或等于运算”来计算。

3）优化了 hash 值的计算方式，老的通过一顿瞎JB操作，新的只是简单的让高16位参与了运算。

4）扩容时插入方式从“头插法”改成“尾插法”，避免了并发下的死循环。

5）扩容时计算节点在新表的索引位置方式从“h & (length-1)”改成“hash & oldCap”，性能可能提升不大，但设计更巧妙、更优雅。



#### 除了 HashMap，还用过哪些 Map，在使用时怎么选择？

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxdHJmYkhpYUtPZDY0S09vZ1g2bmZIeXo3b0VoZ0c4R2NpYWliem1wN01neEJESE5CUzBqTFNyaWFlZy8?x-oss-process=image/format,png)

#### 红黑树

红黑树是一种特化的AVL树（[平衡二叉树](https://baike.baidu.com/item/平衡二叉树/10421057)），都是在进行插入和删除操作时通过特定操作保持二叉查找树的平衡，从而获得较高的查找性能。

红黑树是一种含有红黑结点并能自平衡的二叉查找树。它必须满足下面性质：

- 性质1：每个节点要么是黑色，要么是红色。
- 性质2：根节点是黑色。
- 性质3：每个叶子节点（NIL）是黑色。
- 性质4：每个红色结点的两个子结点一定都是黑色。
- **性质5：任意一结点到每个叶子结点的路径都包含数量相同的黑结点。**

从性质5又可以推出：

- 性质5.1：如果一个结点存在黑子结点，那么该结点肯定有两个子结点