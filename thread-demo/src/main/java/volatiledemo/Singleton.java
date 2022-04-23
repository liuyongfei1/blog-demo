package volatiledemo;

/**
 * uniqueInstance = new Singleton(); 这段代码其实严格讲（从.class被加载到JVM这个过程来讲）会分为以下几个阶段：
 *
 * 验证（判断.class文件是否被篡改，是否合法） -》 准备（给这个类分配内存空间，给类的成员变量赋一个默认值）
 * -》 解析（符号引用替换为直接引用的过程） -》 初始化（正式执行我们的类初始化代码，比如类里边有一个成员变量需要读取配置文件，会在这个阶段做）
 *
 *
 * 是分
 * 为三步执行（不太准确）：
 * 1. 为 uniqueInstance 分配内存空间
 * 2. 初始化 uniqueInstance
 * 3. 将 uniqueInstance 指向分配的内存地址
 *
 * 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2 。指令重排在单线程环境下不会出先问题，但是在
 * 多线程环境下会导致一个线程获得还没有初始化的实例。
 * 例如，线程 T1 执行了 1 和 3 ，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance ，
 * 但此时 uniqueInstance 还未被 初始化。
 * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
 */
public class Singleton {
 private volatile static Singleton uniqueInstance ;
  private Singleton () {
 }
 public static Singleton getUniqueInstance () {
  // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
  if ( uniqueInstance == null ) {
   // 类对象加锁
   synchronized ( Singleton . class ) {
    if ( uniqueInstance == null ) {
     uniqueInstance = new Singleton ();
    }
   }
  }
  return uniqueInstance ;
 }
}