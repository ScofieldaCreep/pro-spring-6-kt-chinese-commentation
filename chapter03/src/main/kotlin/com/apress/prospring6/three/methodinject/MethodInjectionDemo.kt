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
package com.apress.prospring6.three.methodinject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Lookup
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

/**
 * Created by iuliana.cosmina on 07/03/2022
 */
object MethodInjectionDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext(LookupConfig::class.java)
        val abstractLockOpener = ctx.getBean("abstractLockOpener", LockOpener::class.java)
        val standardLockOpener = ctx.getBean("standardLockOpener", LockOpener::class.java)
        displayInfo("abstractLockOpener", abstractLockOpener)
        displayInfo("standardLockOpener", standardLockOpener)
    }

    fun displayInfo(beanName: String, lockOpener: LockOpener) {
        val keyHelperOne = lockOpener.createKeyOpener()
        val keyHelperTwo = lockOpener.createKeyOpener()
        println("[" + beanName + "]: KeyHelper Instances the Same?  " + (keyHelperOne === keyHelperTwo))
        val stopWatch = StopWatch()
        stopWatch.start("lookupDemo")
        for (x in 0 until 100000) {
            val keyHelper = lockOpener.createKeyOpener()
            keyHelper!!.open()
        }
        stopWatch.stop()
        println("100000 gets took " + stopWatch.totalTimeMillis + " ms")
    }
}

@Configuration
@ComponentScan
internal open class LookupConfig

interface LockOpener {
    fun createKeyOpener(): KeyHelper?
    fun openLock()
}

/**
 * StandardLockOpener类中的createKeyOpener方法返回的是一个已经注入的KeyHelper实例，这个实例是在Spring容器启动时创建并注入的。由于KeyHelper的作用域是"prototype"，所以每次通过Spring容器的getBean方法请求KeyHelper时，都会创建一个新的实例。但是在StandardLockOpener中，我们并没有每次都去请求新的KeyHelper实例，而是复用了在Spring容器启动时注入的那个实例。因此，无论调用多少次createKeyOpener方法，返回的都是同一个KeyHelper实例。
 *
 * 相比之下，AbstractLockOpener类中的createKeyOpener方法使用了@Lookup注解，这意味着每次调用createKeyOpener方法时，Spring容器都会返回一个新的KeyHelper实例。这就是方法注入的作用：它允许我们在运行时动态地覆盖容器管理的bean的方法。  总的来说，StandardLockOpener和AbstractLockOpener的主要区别在于，前者复用了同一个KeyHelper实例，而后者每次都创建新的KeyHelper实例。
 */
@Component("standardLockOpener")
internal class StandardLockOpener : LockOpener {
    var keyOpener: KeyHelper? = null

    override fun createKeyOpener(): KeyHelper? {
        return keyOpener
    }

    @Autowired
    @Qualifier("keyHelper")
    fun setKeyHelper(keyHelper: KeyHelper?) {
        keyOpener = keyHelper
    }

    override fun openLock() {
        keyOpener!!.open()
    }
}

/**
 * 虽然AbstractLockOpener类是抽象的，但是Spring容器可以创建它的实例。这是因为Spring在运行时会动态地生成AbstractLockOpener类的子类，并实现其抽象方法。这个动态生成的子类是由CGLIB库创建的，这是Spring AOP（面向切面编程）的底层技术。因此，虽然AbstractLockOpener类是抽象的，但是我们仍然可以从Spring容器中获取到它的实例。
 */
@Component("abstractLockOpener")
internal abstract class AbstractLockOpener : LockOpener {
    @Lookup("keyHelper")
    abstract override fun createKeyOpener(): KeyHelper?

    override fun openLock() {
        createKeyOpener()!!.open()
    }
}

/**
 * 在Spring框架中，`@Scope("prototype")`注解用于指定Spring容器如何创建bean的实例。当一个bean的作用域被设置为"prototype"时，每次请求（通过Spring容器的`getBean`方法）该bean时，Spring容器都会创建一个新的bean实例。
 *
 * 这与"singleton"作用域不同，"singleton"作用域是Spring的默认作用域，当一个bean的作用域被设置为"singleton"时，Spring容器只会创建一个bean实例，并且所有对该bean的请求都会返回这个单一的实例。
 *
 * 所以，`@Scope("prototype")`注解在`KeyHelper`类上，意味着每次请求`KeyHelper` bean时，Spring都会创建一个新的`KeyHelper`实例。
 */
@Component("keyHelper")
@Scope("prototype")
class KeyHelper {
    fun open() {}
}