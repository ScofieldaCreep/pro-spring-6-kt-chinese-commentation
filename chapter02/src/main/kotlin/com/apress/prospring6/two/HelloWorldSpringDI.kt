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
package com.apress.prospring6.two

import com.apress.prospring6.two.decoupled.MessageRenderer
import org.apache.tools.ant.taskdefs.email.Message
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * 为什么这里不用配置messageprovider了？
 *
 * 在Spring框架中，`ApplicationContext`负责管理和配置所有的Bean。当你通过`ApplicationContext`获取一个Bean时，Spring会自动为这个Bean注入所有需要的依赖。这就是所谓的依赖注入（Dependency Injection）。
 *
 * 在你的代码中，`MessageRenderer`是一个Bean，它需要一个`MessageProvider`的实例。在Spring的配置文件（如`app-context.xml`）中，你应该已经配置了`MessageRenderer`的`MessageProvider`依赖。所以，当你通过`ApplicationContext`获取`MessageRenderer`的实例时，Spring会自动为它注入一个`MessageProvider`的实例。
 *
 * 因此，你不需要在代码中显式地配置`MessageProvider`，Spring会自动为你处理这个问题。这就是为什么你在这里不需要配置`MessageProvider`的原因。
 */
object HelloWorldSpringDI {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx: ApplicationContext = ClassPathXmlApplicationContext("spring/app-context.xml")
        val mr = ctx.getBean("renderer", MessageRenderer::class.java)
        mr.render()
    }
}