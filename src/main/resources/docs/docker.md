# docker

### docker常用指令

//更新yum
yum -y update 

//安装docker
yum install -y docker

启动
systemctl start docker

//docker指令
service docker start / stop /restart /pause /unpause 

//创建文件
touch test.txt

//往test最后追加文件
echo hollo world~ > test 

docker search XXX

//docker拉镜像
docker  pull XXX

//查看自己docker安装了什么镜像
docker images

//导出镜像
docker save java > /home/java.tar.gz

//导入镜像
docker load < /home/java.tar.gz

//删除镜像
docker rmi java

docker rm java

//查看运行的容器
docker ps -a

//运行镜像    宿主机：容器
docker run -it -p 9000:8080 -p 9001:8085 -v /home/project:/soft --privileged --name myjava docker.io/java bash

-p 端口 -v 映射路径 -d 后台运行  -e 传入的环境变量参数  --privileged 最高权限  -it 启动交互界面

docker -v 宿主机：容器

常见MySQL集群方案
Replication     速度快、弱一致性、低价值    日志、新闻、帖子	异步复制，无法保证数据一致性
PXC				速度慢、强一致性、高价值	订单、账户、财务    同步复制，事务在所有集群节点要么同时提交。要么不提交

docker tag 原名字 新名字
docker rm 原名字

创建内部网络
docker network create net1        docker network create --subnet=172.18.0.0/24 net1
查看内部网络
docker network inspect net1		
删除内部网络	
docker network rm net1

创建数据卷
docker volume create [--name] v1
查看数据卷
docker inspect v1



查看端口映射

可以通过如下命令查看容器映射了哪些端口及协议：

docker port container-id



数据库第一个实例会比较慢，大概两分钟

docker run -d -p 3306:3306  -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456  -v v1:/var/lib/mysql   --privileged --name=node1  --net=net1  --ip 172.18.0.2 pxc
docker run -d -p 3307:3306  -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=node1  -v v2:/var/lib/mysql   --privileged --name=node2  --net=net1  --ip 172.18.0.3 pxc
docker run -d -p 3308:3306  -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=node1  -v v3:/var/lib/mysql   --privileged --name=node3  --net=net1  --ip 172.18.0.4 pxc







要查看Docker容器的日志，可以使用以下命令：

```
docker logs [OPTIONS] CONTAINER
-t：加入时间戳；
-f：跟随最新的日志打印；
-n：显示最后多少条。
```

其中，`CONTAINER`是要查看日志的容器的名称或ID。而`OPTIONS`则是一些可选参数，可以用来控制日志输出的格式和内容。

以下是一些常用的选项：

- `-f`：实时跟踪日志输出，类似于`tail -f`命令。
- `--tail N`：只显示最后N行日志，默认为所有日志。
- `--since TIME`：只显示从指定时间开始的日志，例如`2019-01-02T13:23:37`。
- `--until TIME`：只显示到指定时间为止的日志，例如`2019-01-02T13:23:37`。



docker容器中时间跟服务器不一致

```
Linux 系统时，我们可以直接将宿主机上的/etc/timezone和/etc/localtime挂载到容器中，这样可以保持容器和宿主机时区和时间一致。
-v /etc/timezone:/etc/timezone:ro -v /etc/localtime:/etc/localtime:ro
```



进入容器

```
docker exec -it CONTAINER ID  bash
```



连接到正在运行容器
docker attach container-id：连接到正在运行的容器；

要attach上去的容器必须正在运行，可以同时连接上同一个container来共享屏幕（与screen命令的attach类似）。

官方文档中说 attach 后可以通过 CTRL-C 来 detach，但实际上经过我的测试，如果 container 当前在运行 bash，CTRL-C 自然是当前行的输入，没有退出；如果 container 当前正在前台运行进程，如输出 nginx 的 access.log 日志，CTRL-C 不仅会导致退出容器，而且还 stop 了。这不是我们想要的，detach 的意思按理应该是脱离容器终端，但容器依然运行。好在 attach 是可以带上 --sig-proxy=false 来确保 CTRL-D 或 CTRL-C 不会关闭容器。

```
docker attach --sig-proxy=false 7f237caad43b
```

`exec` 和 `attach` 区别：

- `attach`：直接进入容器启动命令的终端，不会启动新的进程；
- `exec`：在容器中打开新的终端，并且可以启动新的进程，可在宿主机中直接执行操作容器的命令，eg. `docker exec -it 7f237caad43b ls /tmp`。



#### 查看容器内部细节

可用通过如下命令查看容器内部细节，返回为 json：

```sh
docker inspect container-id
```



排查docker占用磁盘

```
查看磁盘占用
docker system df

TYPE列出了 Docker 使用磁盘的 4 种类型：

Images ：所有镜像占用的空间，包括拉取下来的镜像，和本地构建的。
Containers ：运行的容器占用的空间，表示每个容器的读写层的空间。
Local Volumes ：容器挂载本地数据卷的空间。
Build Cache ：镜像构建过程中产生的缓存空间（只有在使用 BuildKit 时才有，Docker 18.09 以后可用）。
最后的 RECLAIMABLE 是可回收大小。

```



回收docker磁盘

```
docker system prune : 可以用于清理磁盘，删除关闭的容器、无用的数据卷和网络，以及 dangling 镜像（即无 tag 的镜像）。
docker system prune -a : 清理得更加彻底，可以将没有容器使用 Docker镜像都删掉。 注意，这两个命令会把你暂时关闭的容器，以及暂时没有用到的 Docker 镜像都删掉了
```



rollback选项 - 回滚服务

```
[root@centos181001 nginx]# docker service rollback --help

Usage:    docker service rollback [OPTIONS] SERVICE

Revert changes to a service's configuration

Options:
  -d, --detach   Exit immediately instead of waiting for the service to converge
                    立即退出而不是等待服务收敛
  -q, --quiet    Suppress progress output
                    抑制进度输出
```



删除docker日志

```
find /var/lib/docker/overlay2 -type f -name "*.log.*"

find /var/lib/docker/overlay2 -type f -name "*.log.*" -delete

find / -type f -name "*.log.*" -delete
```



### docker swarm

查看一个服务

```bash
 docker service ps 服务名
```

动态扩缩容

```bash
方法一：docker service update --replicas (副本数)  服务名
方法二：docker service scale 服务名=副本数
```

强制重启服务

```bash
docker service update --force 服务名

例子：
docker service update hudson_locals-hudson-provider
```

查看节点的详细信息，默认json格式

```bash
docker node inspect 主机名
```