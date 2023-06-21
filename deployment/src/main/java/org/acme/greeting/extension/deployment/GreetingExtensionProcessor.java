package org.acme.greeting.extension.deployment;

import io.quarkus.arc.deployment.*;
import io.quarkus.deployment.annotations.*;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.undertow.deployment.ServletBuildItem;
import org.acme.greeting.extension.runtime.Foo;
import org.acme.greeting.extension.runtime.GreetingExtensionServlet;
import org.acme.greeting.extension.runtime.HelloRecorder;

import java.util.List;

class GreetingExtensionProcessor {

    private static final String FEATURE = "greeting-extension";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    ServletBuildItem createServlet() {
        System.out.println("hello from createServlet");
        ServletBuildItem servletBuildItem = ServletBuildItem.builder("greeting-extension", GreetingExtensionServlet.class.getName())
                .addMapping("/greeting")
                .build();
        System.out.println("end from createServlet");
        return servletBuildItem;
    }

    @Record(ExecutionTime.RUNTIME_INIT)
    @BuildStep
    public void helloBuildStep(HelloRecorder recorder) {
        System.out.println("hello from helloBuildStep");
        recorder.sayHello("World");
        System.out.println("end from helloBuildStep");
    }

    @BuildStep
    AdditionalBeanBuildItem additionalBeans() {
        System.out.println("hello from additionalBeans");
        AdditionalBeanBuildItem item = new AdditionalBeanBuildItem(Foo.class);
        System.out.println("end from additionalBeans");
        return item;
    }


}
