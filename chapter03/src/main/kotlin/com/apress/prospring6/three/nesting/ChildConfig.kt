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
package com.apress.prospring6.three.nesting

import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by iuliana.cosmina on 06/03/2022
 */
@Configuration
open class ChildConfig : ApplicationContextAware {
    var applicationContext1: ApplicationContext? = null

    /**
     * 在这个例子中，song1和song2的定义都在子上下文ChildConfig中，但是它们的title属性值却来自父上下文ParentConfig中的bean。  song1的title属性值来自父上下文中的parentProvider bean，这是通过@Value("#{parentProvider.title}")注解实现的。  song2的title属性值来自父上下文中的childProvider bean，这是通过@Value("#{childConfig.applicationContext1.parent.getBean(\"childProvider\").title}")注解实现的。这个表达式稍微复杂一些，它首先获取了childConfig bean（也就是ChildConfig类的实例），然后通过applicationContext1.parent.getBean("childProvider")获取了父上下文中的childProvider bean。  这两个bean的定义都在父上下文中，但是可以在子上下文中被访问和使用。这就是Spring的上下文嵌套和bean的继承的特性。
     */
    @Bean // overrides {@code childProvider} bean from parent context
    open fun childProvider(): TitleProvider {
        return TitleProvider.instance("No Such Thing")
    }

    @Bean
    open fun song1(@Value("#{parentProvider.title}") title: String?): Song {
        return Song(title)
    }

    @Bean
    open fun song2(@Value("#{childConfig.applicationContext1.parent.getBean(\"childProvider\").title}") title: String?): Song {
        return Song(title)
    }

    @Bean
    open fun song3(@Value("#{childProvider.title}") title: String?): Song {
        return Song(title)
    }

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext1 = applicationContext
    }
}