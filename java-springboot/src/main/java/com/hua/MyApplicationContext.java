package com.hua;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.hua.anotation.MyAutowried;
import com.hua.anotation.MyComponent;
import com.hua.exception.NotFountBeanException;
import com.hua.util.IocUtil;
import com.sun.tools.javac.util.ArrayUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自己实现一个简单地IOC容器
 *
 * @author: huabin
 * @date: 2021/9/23 上午10:03
 */
public class MyApplicationContext {

    // 存储需要实例化的对象class全路径名
    private Set<String> classNameSet = new HashSet<String>();

    // 存储实例化的对象Class全路径名
    private Map<String,Object> beanMap = new ConcurrentHashMap<String, Object>();

    public MyApplicationContext(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) throws NotFountBeanException {
        return beanMap.get(name);
    }

    public void init() throws Exception {
        //1、定位资源
        String beanScanPath = getBeanScanPath("ioc.bean.scan");
        //2、加载需要实例化的Class
        loadBeanClass(beanScanPath);
        //3、实例化bean
        registerBean();
        //4、注入bean的属性
        dependenceInjection();

    }

    private void dependenceInjection() throws Exception {
        if (MapUtil.isEmpty(beanMap)) return;
        for (Object o : beanMap.values()) {
            doInjection(o);
        }
    }

    /**
     * 功能简述：定位需要进行实例化Java类
     * @param key
     * @return
     */
    private String getBeanScanPath(String key) throws IOException {
        Properties properties = IocUtil.getPropertyByName("application.properties");
        return properties.get(key).toString();
    }


    /**
     * 功能简述：加载JavaClass，后期根据class的全路径实例化bean
     * @param packageName 包名
     * @return
     */
    public void loadBeanClass(String packageName){
        // 路径替换
        String filePath = packageName.replace(".", "/");
        URL url = this.getClass().getClassLoader().getResource(filePath);

        // 得到根文件夹
        File rootFile = new File(url.getFile());

        // 遍历所有文件夹
        if (rootFile != null) {
            for (File file:
                 rootFile.listFiles()) {
                if (file.isDirectory()) {
                    // 如果是文件夹则递归继续往下扫描
                    loadBeanClass(packageName+"."+file.getName());
                } else {
                    if (file.getName().indexOf(".class")>0) {
                        // 保存class类名
                        classNameSet.add(packageName + "." + file.getName().replace(".class", ""));
                    }
                }
            }
        }


    }

    private void registerBean() throws Exception {
        if (!CollectionUtils.isEmpty(classNameSet)) {
            for (String className : classNameSet) {
                // 实例化对象放入beanMap
                Class<?> clazz = Class.forName(className);
                MyComponent myComponent = clazz.getAnnotation(MyComponent.class);
                if (myComponent == null) {
                    continue;
                }
                // 定义bean key名称
                String beanname = (StringUtils.isEmpty(myComponent.value())) ? IocUtil.toLowercaseIndex(clazz.getSimpleName()) : myComponent.value();
                beanMap.put(beanname, clazz.newInstance());
            }
        }
    }

    /**
     * 功能简述：对已经实例化的对象进行属性注入
     * @param o
     * @return
     */
    private void doInjection(Object o) throws Exception {
        Field[] fields = o.getClass().getDeclaredFields();
        if (ArrayUtil.isNotEmpty(fields)) {
            for (Field file : fields) {
                MyAutowried autowried = file.getAnnotation(MyAutowried.class);
                if (autowried != null) {
                    // 得到beanName 验证该Bean是否已经实例化了
                    String beanName = (StringUtils.isEmpty(autowried.value())) ? IocUtil.toLowercaseIndex(file.getType().getSimpleName()) : autowried.value();

                    if (!beanMap.containsKey(beanName)) {
                        Class<?> clazz = file.getType();
                        beanMap.put(beanName, clazz.newInstance());
                    }

                    // 调用对象set方法注入属性
                    file.setAccessible(true);
                    file.set(o, beanMap.get(beanName));

                    // 递归当前实例化的对象的属性注入
                    doInjection(beanMap.get(beanName));
                }
            }
        }
    }





}
