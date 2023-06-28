package org.acme.greeting.extension.deployment;

import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.deployment.annotations.*;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ServiceStartBuildItem;
import org.acme.greeting.extension.runtime.HelloRecorder;

import java.util.List;

/**
 * 自定义的BuildItem使用示例
 * <p>通过运行{@link org.acme.greeting.extension.test.CustomBuildItemTest} 查看效果</p>
 * <p></p>
 * <p>
 * 在每一个@BuildStep标注的方法执行前, 会事先判断该方法生产的BuildItem会不会在后期被消费, 消费的方式有下面几种:
 * <p>
 * {@link CustomBuildItemProcessor#justPrint1()}
 * <p>
 * {@link CustomBuildItemProcessor#justPrint2(BuildProducer)}
 * <p>
 * {@link CustomBuildItemProcessor#justPrint3(HelloRecorder)}
 * <p>
 * 因此自定义的BuildItem一定需要被消费, 不然这个方法就不会被执行. 这是quarkus的编译优化机制造成的现象.
 * @Author ggdefe
 * @Date 2023/6/21 17:50
 */
public class CustomBuildItemProcessor {

    /**
     * 消费BuildItem
     * <p>
     * 把消费动作放到前面是为了演示方便一些, 按一般逻辑应该写在生产方法后面
     * <p>
     * 如果不加@Produce注解, 该方法就没有产出任何可消费的产物, 故而就不会被执行
     * <p>
     * 如果对上一句话一头雾水, 先看下面的justPrint几个示例
     */
    @BuildStep
    @Produce(ServiceStartBuildItem.class)
    void consumeMyBuildItem1(List<MyCustomBuildItem> items) {
        System.out.println("method invoked: consumeMyBuildItem1");
        printCustomBuildItems(items);
    }

    /**
     * 这里也是消费BuildItem, 但更侧重于等待所有MyBuildItem生产完成才执行
     */
    @BuildStep
    @Consume(MyCustomBuildItem.class)
    @Produce(ServiceStartBuildItem.class)
    void consumeMyBuildItem2(){
        System.out.println("method invoked: consumeMyBuildItem2");
    }

    /**
     *
     * 第一种生产方式
     * <p>
     * 消费位置: {@link CustomBuildItemProcessor#consumeMyBuildItem1(List)}
     *
     */
    @BuildStep
    void createMyBuildItem1(BuildProducer<MyCustomBuildItem> producer){
        System.out.println("method invoked: createMyBuildItem1");
        producer.produce(MyCustomBuildItem.MyBuildItemBuilder.aMyBuildItem()
                .withCode("1")
                .withMsg("this is produce custom build item")
                .build()
        );
    }

    /**
     * 第二种生产方式
     */
    @BuildStep
    public MyCustomBuildItem createMyBuildItem2(){
        System.out.println("method invoked: createMyBuildItem2");
        return MyCustomBuildItem.MyBuildItemBuilder.aMyBuildItem()
                .withCode("2")
                .withMsg("this is return custom build item")
                .build();
    }



    /**
     * 这种方式不会被执行, 会提示没有产出物存在
     */
//    @BuildStep
//    void justPrint0() {
//        System.out.println("method invoked: justPrint0");
//    }

    /**
     * 被执行: 通过标记其产出物
     * <p>
     * 这里选择使用哪个BuildItem并无所谓，在这里只是确保编译器执行时会认为这个方法是有用的，所以只需要确保BeanDefiningAnnotationBuildItem会被消费, 而这个类是quarkus内置的, 所以在quarkus编译过程会被用到
     */
    @BuildStep
    @Produce(ServiceStartBuildItem.class)
    void justPrint1() {
        System.out.println("method invoked: justPrint1");
    }

    /**
     * 被执行: 通过入参判断有产出
     */
    @BuildStep
    void justPrint2(BuildProducer<ServiceStartBuildItem> producer) {
        System.out.println("method invoked: justPrint2");
    }

    /**
     * 被执行: 标注record注解, 代表在执行过程中有动作被执行, 这时quarkus也是不会自动去优化掉的
     */
    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void justPrint3(HelloRecorder recorder) {
        System.out.println("method invoked: justPrint3");
    }


    private static void printCustomBuildItems(List<MyCustomBuildItem> items) {
        for (MyCustomBuildItem item : items) {
            String code = item.getCode();
            String msg = item.getMsg();
            System.out.println("code: " + code + ", msg: " + msg);
        }
    }



}
