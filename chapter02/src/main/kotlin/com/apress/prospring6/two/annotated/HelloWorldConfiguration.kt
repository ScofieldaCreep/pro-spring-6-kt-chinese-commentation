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
package com.apress.prospring6.two.annotated

import com.apress.prospring6.two.decoupled.HelloWorldMessageProvider
import com.apress.prospring6.two.decoupled.MessageProvider
import com.apress.prospring6.two.decoupled.MessageRenderer
import com.apress.prospring6.two.decoupled.StandardOutMessageRenderer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * 在Kotlin中，`open`关键字用于表示一个类、方法或属性可以被继承或覆盖。默认情况下，Kotlin中的所有类和方法都是`final`的，也就是说它们不能被继承或覆盖。如果你想让一个类可以被继承，或者一个方法可以被覆盖，你需要使用`open`关键字。
 *
 * 在你提供的代码中，`open`关键字用于`HelloWorldConfiguration`类和它的方法。这意味着这个类可以被其他类继承，而它的方法也可以在子类中被覆盖。这在Spring框架中是常见的做法，因为Spring经常使用CGLIB库通过子类化的方式来创建代理类，以实现例如事务管理、安全检查等功能。如果一个类或方法没有被声明为`open`，那么Spring就不能创建它的代理类，这可能会导致一些功能无法正常工作。
 */
@Configuration
open class HelloWorldConfiguration {
    @Bean
    open fun provider(): MessageProvider {
        return HelloWorldMessageProvider()
    }

    @Bean
    open fun renderer(): MessageRenderer {
        val renderer: MessageRenderer = StandardOutMessageRenderer()
        renderer.messageProvider = provider()
        return renderer
    }
}