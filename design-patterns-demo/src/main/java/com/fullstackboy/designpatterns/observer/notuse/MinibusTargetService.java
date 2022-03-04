package com.fullstackboy.designpatterns.observer.notuse;

/**
 * 实战 不使用观察者模式
 *
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
