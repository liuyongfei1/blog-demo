package com.fullstackboy.designpatterns.observer.use;

/**
 * 实战 使用观察者模式
 * https://bugstack.cn/md/develop/design-pattern/2020-06-30-%E9%87%8D%E5%AD%A6%20Java%20%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E3%80%8A%E5%AE%9E%E6%88%98%E8%A7%82%E5%AF%9F%E8%80%85%E6%A8%A1%E5%BC%8F%E3%80%8B.html
 * 小客车指标监控服务
 *
 * @author Liuyongfei
 * @date 2022/3/4 16:46
 */
public class MinibusTargetService {

    /**
     * 模拟摇号
     * @param uId 用户变化
     * @return 结果
     */
    public String lottery(String uId) {
        return Math.abs(uId.hashCode()) % 2 == 0
                ? "恭喜你，编码".concat(uId).concat("在本次摇号中中签")
                : "很遗憾，编码".concat(uId).concat("在本次摇号中未中签或摇号资格已过去");
    }

}
