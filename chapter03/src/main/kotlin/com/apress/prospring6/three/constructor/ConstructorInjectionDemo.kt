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
package com.apress.prospring6.three.constructor

import com.apress.prospring6.two.decoupled.MessageProvider
import com.apress.prospring6.two.decoupled.MessageRenderer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

/**
 * Created by iuliana.cosmina on 05/03/2022
 */
object ConstructorInjectionDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx: ApplicationContext = AnnotationConfigApplicationContext(HelloWorldConfiguration::class.java) // <1>
        val mr: MessageRenderer = ctx.getBean("renderer", MessageRenderer::class.java)
        mr.render()
    }
}

// --- Kotlin configuration class  ---
@Configuration
@ComponentScan
internal open class HelloWorldConfiguration()

//  --- bean definitions using @Component ---
//simple bean
@Component("provider")
internal class HelloWorldMessageProvider() : MessageProvider {
    override val message: String
        get() = "Hello World!"
}

//complex bean requiring a dependency
/**
 * In Spring 4.x, it was decided that if a bean declares a single constructor that initializes all dependencies, the
 * Autowired annotation was redundant, so in the spirit of convention over configuration, the Spring IoC was
 * modified to call the only constructor present to create the bean regardless of the presence/absence of the
 * annotation. So, the renderer bean declared in Listing 3-13 is valid even if the @Autowired annotation is removed.
 *
 * 为什么构造函数那里MessageProvider要加？
 *
 * Autowired constructor(override var messageProvider: MessageProvider?)这行代码的意思是，当Spring创建StandardOutMessageRenderer的实例时，它会查找一个类型为MessageProvider的bean，并将其注入到StandardOutMessageRenderer的构造函数中。这就是所谓的构造函数注入。  如果没有提供MessageProvider，那么StandardOutMessageRenderer的实例将无法创建，因为它的构造函数需要一个MessageProvider参数。这就确保了在StandardOutMessageRenderer的实例被使用之前，它的所有依赖都已经被满足。  这种方式的好处是，你可以在对象的生命周期的早期阶段就确保所有的依赖都已经被满足，而不是等到对象被使用时才检查依赖是否存在。这可以提高代码的健壮性和可维护性。
 */
@Component("renderer")
internal class StandardOutMessageRenderer
@Autowired constructor(override var messageProvider: MessageProvider?) :
    MessageRenderer {

    init {
        println(" ~~ Injecting dependency using constructor ~~")
    }

    override fun render() {
        val msg = messageProvider ?: throw RuntimeException(
            "You must provide a messageProvider constructor param for:"
                    + StandardOutMessageRenderer::class.java.name
        )
        println(msg.message)
    }
}
