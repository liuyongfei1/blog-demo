package com.fullstackboy.myannotations;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 在这里为自定义注解添加逻辑功能
 *
 * @author Liuyongfei
 * @date 2022/3/18 07:19
 */
public class ApplicationContext {

    /**
     * ioc 容器
     */
    private Map<String, Object> iocMap = new ConcurrentHashMap<>();

    public ApplicationContext(String packagePath) {
        // 扫描指定的包路径
        scanPackage(packagePath);
    }

    /**
     * 处理 class 文件
     * @param packagePath 包路径
     * @param classFiles File 对象
     */
    private void processClassFiles(String packagePath, File[] classFiles) {
        for (File classFile : classFiles) {
            // 1、去掉后缀，获取 class 文件名
            String className = classFile.getName().substring(0, classFile.getName().lastIndexOf("."));

            // 2、拼接出来类的全限定类名 fullClassName，后边反射要用到
            String fullClassName = packagePath + "." + className;

            // 3、将类字母转换为小写，得到 beanName
            String beanName = String.valueOf(className.charAt(0)).toLowerCase() + className.substring(1);

            // 4、创建实例，并放入到 IOC 容器中
            createBean(beanName, fullClassName);
        }
    }

    /**
     * 创建bean实例，并将bean实例放入ioc容器中
     * @param beanName bean名称
     * @param fullClassName bean全限定类名
     */
    public void createBean(String beanName, String fullClassName) {
        try {
            // 1、通过反射创建出 Class 对象
            Class<?> c = Class.forName(fullClassName);

            // 2、判断这个类上是否加了 @MyComponent 注解
            if (c.isAnnotationPresent(MyComponent.class)) {
                System.out.println("fullClassName=" + fullClassName + "加了 @MyComponent注解，准备通过反射创建实例，并放入到iocMap中去");

                // 3、通过反射创建实例
                Object instance = c.newInstance();

                // 4、将创建的实例放入到 ioc 容器中
                iocMap.put(beanName, instance);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 beanName 获取bean实例
     * @param beanName bean名称
     * @return bean实例
     */
    public Object getBean(String beanName) {
        return iocMap.get(beanName);
    }

    /**
     * 获取执行包下面的所有.class文件
     * @param packagePath 包路径
     * @return .class文件
     */
    private File[] getClassFiles(String packagePath) {
        // 1、通过packagePath 获取所有的 File 对象
        File file = getFile(packagePath);

        // 2、过滤出所有的.class文件
        return filterClassFiles(packagePath, file);
    }

    /**
     * 通过包路径获取对应File对象
     * @param packagePath 包路径
     * @return File对象
     */
    private File getFile(String packagePath) {
        // 1、将包路径中的 . 替换为 /
        String packageDir = packagePath.replaceAll("\\.","/");

        // 2、获取这个目录在类路径中的位置
        URL resource = getClass().getClassLoader().getResource(packageDir);

        // 3、通过目录获取文件对象
        return new File(resource.getFile());
    }

    /**
     * 扫描指定的包路径
     * @param packagePath 包路径
     */
    private void scanPackage(String packagePath) {
        // 1、获取这个路径下的所有.class文件
        File[] classFiles = getClassFiles(packagePath);

        // 2、处理所有的.class文件，为加了 @MyComponent 注解的类创建实例，并放入 ioc 容器中
        processClassFiles(packagePath, classFiles);
    }

    /**
     * 过滤出指定路径下的所有的.class文件
     * @param packagePath 包路径
     * @param file 这个路径下的File对象
     * @return 所有的.class文件
     */
    private File[] filterClassFiles(String packagePath, File file) {

        return file.listFiles(f -> {
           String fileName = f.getName();
           if (f.isDirectory()) {
               // 再次扫描
               scanPackage(packagePath);
           } else {
               if (fileName.endsWith(".class")) {
                   return true;
               }
           }
           return false;
        });

    }
}
