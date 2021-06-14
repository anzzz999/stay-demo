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

数据库第一个实例会比较慢，大概两分钟

docker run -d -p 3306:3306  -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456  -v v1:/var/lib/mysql   --privileged --name=node1  --net=net1  --ip 172.18.0.2 pxc
docker run -d -p 3307:3306  -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=node1  -v v2:/var/lib/mysql   --privileged --name=node2  --net=net1  --ip 172.18.0.3 pxc
docker run -d -p 3308:3306  -e MYSQL_ROOT_PASSWORD=123456 -e CLUSTER_NAME=PXC -e XTRABACKUP_PASSWORD=123456 -e CLUSTER_JOIN=node1  -v v3:/var/lib/mysql   --privileged --name=node3  --net=net1  --ip 172.18.0.4 pxc