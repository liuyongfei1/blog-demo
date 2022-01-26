package com.fullstackboy.reflection;

import java.lang.reflect.Method;

/**
 * method.invoke() 方法，用来执行某个对象的目标方法。
 * invoke()方法包含多态的特性。=》见下面的代码。
 */
public class MethodInvoke {
 
	public static void main(String[] args) throws Exception {
		// 1、通过反射获取print()的Method对象
		Method animalMethod = Animal.class.getDeclaredMethod("print");
		Method catMethod = Cat.class.getDeclaredMethod("print");

		// 2、分别创建Animal和Cat的实例对象
		Animal animal = new Animal();
		Cat cat = new Cat();

		// 3、分别用Animal和Cat的实例对象去执行print()方法

		// 由于Cat是Animal的子类，按照多态的特性，子类调用父类的方法，方法在执行时会动态链接到子类的实现方法上。=》因此，这里会调用 Cat的print()方法。
		animalMethod.invoke(cat);

		// 实例对象的真实类型和Method声明的Class是相同的 =》所以会按照预期的结果打印
		animalMethod.invoke(animal);

		// 实例对象的真实类型和Method声明的Class是相同的 =》所以会按照预期的结果打印
		catMethod.invoke(cat);

		// 传入的参数类型Animal是父类，却期望调用子类Cat的方法 =》因此，会抛出异常：
		// Exception in thread "main" java.lang.IllegalArgumentException: object is not an instance of declaring class
		//	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		//	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		//	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		//	at java.lang.reflect.Method.invoke(Method.java:498)
		//	at com.fullstackboy.reflection.MethodInvoke.main(MethodInvoke.java:28)
		catMethod.invoke(animal);
	}
	
}