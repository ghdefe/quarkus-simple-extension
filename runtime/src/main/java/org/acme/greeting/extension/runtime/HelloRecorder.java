package org.acme.greeting.extension.runtime;

import io.quarkus.runtime.annotations.Recorder;

import java.time.LocalDateTime;

/**
 * @Author 曾春苗
 * @Date 2023/6/20 18:03
 */
@Recorder
public class HelloRecorder {

    public void sayHello(String name) {
        System.out.println("hello from HelloRecorder");
        System.out.println("now time: " + LocalDateTime.now());
        System.out.println("Hello" + name);
        System.out.println("end from HelloRecorder");
    }

}
