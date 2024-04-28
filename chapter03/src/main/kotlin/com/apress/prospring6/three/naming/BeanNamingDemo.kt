/*
Freeware License, some rights reserved

Copyright (c) 2023 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy
of this software and associated documentation files (the "Software"),
to work with the Software within the limits of freeware distribution and fair use.
This includes the rights to use, copy, and modify the Software for personal use.
Users are also allowed and encouraged to submit corrections and modifications
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for
commercial use in any way, or for a user's educational materials such as books
or blog articles without prior permission from the copyright holder.

The above copyright notice and this permission notice need to be included
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/
package com.apress.prospring6.three.naming

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer

/**
 * Created by iuliana.cosmina on 08/03/2022
 */
object BeanNamingDemo {
    private val logger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(BeanNamingDemo::class.java)

    /**
     * 这些打印的信息是Spring框架在启动时创建的Bean的名称。
     *
     * 1. `org.springframework.context.annotation.internalConfigurationAnnotationProcessor`、`org.springframework.context.annotation.internalAutowiredAnnotationProcessor`、`org.springframework.context.annotation.internalCommonAnnotationProcessor`、`org.springframework.context.event.internalEventListenerProcessor`和`org.springframework.context.event.internalEventListenerFactory`这些都是Spring内部使用的Bean，用于处理注解和事件监听等。
     *
     * 2. `beanNamingCfg`是你在`BeanNamingCfg`类上使用`@Configuration`注解定义的配置Bean。
     *
     * 3. `simpleBean`是你在`SimpleBean`类上使用`@Component`注解定义的Bean。
     *
     * 这些信息是通过`ctx.beanDefinitionNames.forEach`遍历Spring上下文中所有的Bean定义并打印出来的。这可以帮助你了解Spring在启动时创建了哪些Bean。
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext(BeanNamingCfg::class.java)
        ctx.beanDefinitionNames.forEach { beanName ->
            logger.debug("$beanName!")
        }
        //try {
        //    ctx.getBean(SimpleBean::class.java)
        //} catch (e: Exception) {
        //    logger.debug("More beans than expected found. ", e)
        //}
        logger.info(" ... All beans of type: {} ", SimpleBean::class.java.simpleName)
        val beans = ctx.getBeansOfType(SimpleBean::class.java)
        //logger.info(" ... All beans")
        //val beans = ctx.getBeansOfType(Any::class.java)
        beans.forEach { (k, v) ->
            println(k)
        }
    }
}

@Configuration
@ComponentScan
internal open class BeanNamingCfg {
//    @Bean
//    open fun anotherSimpleBean(): SimpleBean {
//        return SimpleBean()
//    }
}

@Component
internal class SimpleBean