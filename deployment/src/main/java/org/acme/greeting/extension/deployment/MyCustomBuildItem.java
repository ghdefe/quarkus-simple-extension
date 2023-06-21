package org.acme.greeting.extension.deployment;

import io.quarkus.builder.item.MultiBuildItem;

/**
 * @Author 曾春苗
 * @Date 2023/6/21 16:38
 */
public final class MyCustomBuildItem extends MultiBuildItem {

    String msg;
    String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static final class MyBuildItemBuilder {
        private String msg;
        private String code;

        private MyBuildItemBuilder() {
        }

        public static MyBuildItemBuilder aMyBuildItem() {
            return new MyBuildItemBuilder();
        }

        public MyBuildItemBuilder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public MyBuildItemBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public MyCustomBuildItem build() {
            MyCustomBuildItem myCustomBuildItem = new MyCustomBuildItem();
            myCustomBuildItem.setMsg(msg);
            myCustomBuildItem.setCode(code);
            return myCustomBuildItem;
        }
    }
}
