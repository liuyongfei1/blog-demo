package com.fullstackboy.test;

/**
 * 练习泛型的使用
 * 类型转换处理器接口
 * @param <S>
 * @param <T>
 */
public interface Converter<S, T> {

    T convert(S source);
}
