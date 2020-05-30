# blog-demo
## 这里存放关于微信公众号文章里面的一些案例

### 每个案例以多模块部署的方式

#### 一. 添加RabbitMQ的消息确认机制demo

#### 二. 添加RabbitMQ的RPC实现demo
##### 1.优化RPC需要用到的队列，修改为常量 
##### 2.优化RPCClient，为RPCServer返回消息添加correlationId匹配逻辑 
##### 3.优化RPC功能，将RPCServer和RPCClient两个服务单独部署

#### 三. 发布RabbitMQ系列之怎么确保消息不丢失

#### 四. RabbitMQ系列之并发测试
##### 1. 为消费端添加多个消费者test 