package org.acme.greeting.extension.it;


import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.acme.greeting.extension.runtime.Foo;

/**
 * @Author 曾春苗
 * @Date 2023/6/21 9:07
 */
public class GreetingConfiguration {

    @Produces
    @Named("foo-custom")
    @ApplicationScoped
    @DefaultBean
    public Foo produceFoo(){
        Foo foo = new Foo();
        return foo;
    }





}
