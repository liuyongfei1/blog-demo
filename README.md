# blog-demo
## 这里存放关于微信公众号文章里面的一些案例

### 微信公众号

![微信公众号二维码的副本](README.assets/微信公众号二维码的副本.jpeg)

### 每个案例以多模块部署的方式

#### 一. 添加RabbitMQ的消息确认机制demo

#### 二. 添加RabbitMQ的RPC实现demo
##### 1.优化RPC需要用到的队列，修改为常量 
##### 2.优化RPCClient，为RPCServer返回消息添加correlationId匹配逻辑 
##### 3.优化RPC功能，将RPCServer和RPCClient两个服务单独部署

#### 三. 发布RabbitMQ系列之怎么确保消息不丢失

#### 四. RabbitMQ系列之并发测试
##### 1. 为消费端添加多个消费者test 
##### 2. 添加service模块
##### 3. 添加model和mapper模块
##### 4. 添加抢单逻辑
##### 5. 添加数据库连接配置
##### 6. 模拟高并发抢单v1
##### 7. 模拟高并发抢单，添加数据库查询判断商品是否有库存、更新商品库存、保存抢单成功用户记录的方法
##### 8. 添加抢单的前置逻辑
##### 9. 设置消费者channel.basicQos(1)
##### 10. 解决生产端高并发时消息发送重复的bug
##### 11. 补全注释
##### 12. 确保每个消费者在同一个时间点最多只处理一个Message

#### 五. 消息轮询分发和公平分发demo
##### 1. 添加轮询分发
##### 2. 添加公平分发
##### 3. 修复PrefetchCount设置不生效的bug
##### 4. 去掉容器工厂，改为在配置文件中设置prefetchCount

#### 六. 来看一看，如何在魔盒（大数据开发协作平台）中提高RabbitMQ消费速度
#### 七. 编辑魔盒(大数据协作平台)是如何实现离线计算任务的工作流调度
#### 八. 发布魔盒(大数据协作平台)是如何实现离线计算任务的工作流调度
#### 九. 编辑移山(数据迁移平台)的数据迁移是怎么实现的
#### 十. 继续编辑移山(数据迁移平台)的数据迁移是怎么实现的
#### 十一. 发布移山(数据迁移平台)的数据迁移是怎么实现的
#### 十二. 编辑阿里canal是怎么通过zookeeper实现HA机制的？
#### 十三. 发布阿里canal是怎么通过zookeeper实现HA机制的？
#### 十四. 本机跑通canal-kafka-client集群连接方式
#### 十四. 编辑：移山(数据迁移平台)的实时数据同步服务是怎么实现的？
##### 1. 移山(数据迁移平台)的实时数据同步服务是怎么实现的？
##### 2. 编辑：移山(数据迁移平台)的实时数据同步服务是怎么实现的？
##### 3. 编辑：画移山创建实时数据任务流程图
##### 4. 编辑完成：画移山创建实时数据同步架构图
##### 5. 发布移山(数据迁移平台)实时数据同步服务的架构设计
#### 十五. 编辑：实时数据同步服务是如何保证消息的顺序性？
#### 十六. 添加canalKafka2Hbase模块
#### 十七. 编辑：实时数据同步服务是如何保证消息的顺序性
#### 十八. 发布：实时数据同步服务是如何保证消息的顺序性
#### 十九. 编辑：遇到Idea External Libraries显示的jar版本与pom.xml中指定的jar版本不一致怎么解决？
#### 二十. 编辑：Maven项目为什么会产生jar包冲突？
#### 1. 编辑：Maven项目为什么会产生jar包冲突：jar包冲突：NoClassDefFoundError
#### 2. 编辑：Maven项目为什么会产生jar包冲突：jar包冲突：NoSuchMethodError
#### 3. 完成：Maven项目为什么会产生NoClassDefFoundError或NoSuchMethodError这样的jar包冲突？
#### 4. 修改：Maven项目为什么会产生NoClassDefFoundError或NoSuchMethodError这样的jar包冲突？
#### 5. 发布：Maven项目为什么会产生NoClassDefFoundError的jar包冲突？
#### 二十一. 编辑：使用easyexcel时遇到jar包冲突：NoClassDefFoundError
#### 1. 使用easyexcel时遇到Could not initialize class cglib.beans.BeanMap怎么解决