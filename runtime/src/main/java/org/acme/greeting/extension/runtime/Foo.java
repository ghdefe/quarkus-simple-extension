package org.acme.greeting.extension.runtime;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

/**
 * @Author 曾春苗
 * @Date 2023/6/21 9:12
 */
@ApplicationScoped
public class Foo {

    int count = 0;

    public void hello() {
        count++;
        System.out.println("hello from foo: " + count);
    }
}
