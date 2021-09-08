# Maven



### IDEA中Maven生命周期

  IDEA工具Maven projects里面有9种生命周期，今天刚好遇到，顺便分享下自己的理解。

  Maven生命周期（lifecycle）由各个阶段组成，每个阶段由Maven的插件plugin来执行完成。

最常用的两种打包方法：
  （1）clean，package（如果报错，很可能就是jar依赖的问题）
  （2）clean，install

9种生命周期：

![img](https://img-blog.csdnimg.cn/20200908190120292.png)

1 clean
    清理，在进行真正的构建之前进行一些清理工作，移除所有上一次构建生成的文件。执行该命令会删除项目路径下的target文件，但是不会删除本地的maven仓库已经生成的jar文件。

2 valitate
    验证，验证工程是否正确，所需的信息是否完整。

3 compile
    编译源码，编译生成class文件,编译命令，只编译选定的目标，不管之前是否已经编译过，会在你的项目路径下生成一个target目录，在该目录中包含一个classes文件夹，里面全是生成的class文件及字节码文件。

4 test
    单元测试，测试。

5 package
    打包，将工程文件打包为指定的格式，例如JAR，WAR等。这个命令会在你的项目路径下一个target目录，并且拥有compile命令的功能进行编译，同时会在target目录下生成项目的jar/war文件。如果a项目依赖于b项目，打包b项目时，只会打包到b项目下target下，编译a项目时就会报错，因为找不到所依赖的b项目，说明a项目在本地仓库是没有找到它所依赖的b项目，这时就用到install命令了。

6 verify
    核实，检查package是否有效、符合标准。

7 install
    安装至本地仓库，将包安装至本地仓库，以让其它项目依赖。该命令包含了package命令功能，不但会在项目路径下生成class文件和jar包，同时会在你的本地maven仓库生成jar文件，供其他项目使用（如果没有设置过maven本地仓库，一般在用户/.m2目录下。如果a项目依赖于b项目，那么install b项目时，会在本地仓库同时生成pom文件和jar文件，解决了上面打包package出错的问题）

8 build
    功能类似compile，只是只对整个项目进行编译。

9 site
    站点，生成项目的站点文档。

10 deploy
    复制到远程仓库。



**build和compile的区别**
    Compile：只编译选定的目标，不管之前是否已经编译过。

​	Build：是对整个工程进行彻底的重新编译，而不管是否已经编译过。Build过程往往会生成发布包，这个具体要看对IDE的配置了，Build在实际中应用很少，因为开发时候基本上不用，发布生产时候一般都用ANT等工具来发布。Build因为要全部编译，还要执行打包等额外工作，因此时间较长。



**package、install和deploy的区别**
    **package**命令完成了项目编译、单元测试、打包功能，但**没有把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库和远程maven私服仓库**。

​	**install**命令完成了项目编译、单元测试、打包功能，同时把打好的可执行jar包（war包或其它形式的包）**布署到本地maven仓库**，但没有布署到远程maven私服仓库。

​	**deploy**命令完成了项目编译、单元测试、打包功能，同时**把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库和远程maven私服仓库。**





### 坐标（gav）

由三个向量组成，唯一定位一个Maven项目，三者同时定义路径，groupId和version决定Jar包名称

**groupId**：组织名，通常是一个公司或组织名称倒序+项目名

**artifactId**：模块名：通常是工程名

**version**：版本号





### 单词学习

Execution：执行

Deployment：部署

archetype：模板、原型