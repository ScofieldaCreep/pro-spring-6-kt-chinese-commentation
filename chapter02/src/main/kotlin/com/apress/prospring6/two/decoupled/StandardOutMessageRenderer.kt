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
package com.apress.prospring6.two.decoupled

/**
 * 在Kotlin中，`field`是一个特殊的关键字，它只能在属性的访问器（getter或setter）中使用。`field`代表了属性本身，你可以在访问器中使用`field`来访问或修改属性的值。
 *
 * 在你的代码中：
 *
 * ```kotlin
 * override var messageProvider: MessageProvider? = null
 *    set(value) {
 *        field = value
 *        println(" --> StandardOutMessageRenderer: setting the provider")
 *    }
 * ```
 *
 * `field = value`是在setter方法中设置`messageProvider`属性的值。`value`是setter方法的参数，它代表了要设置的新值。`field = value`表示将`messageProvider`的值设置为`value`。
 *
 * 关于`open`关键字，它在Kotlin中用于表示一个类、方法或属性可以被继承或重写。默认情况下，Kotlin中的所有类都是final的，也就是说它们不能被继承。如果你想让一个类可以被继承，你需要使用`open`关键字来声明这个类。同样，如果你想让一个方法或属性可以被子类重写，你也需要使用`open`关键字来声明这个方法或属性。
 *
 * 例如：
 *
 * ```kotlin
 * open class MyBaseClass {
 *     open fun myMethod() {
 *         println("Base class method")
 *     }
 * }
 *
 * class MyDerivedClass : MyBaseClass() {
 *     override fun myMethod() {
 *         println("Derived class method")
 *     }
 * }
 * ```
 *
 * 在这个例子中，`MyBaseClass`是一个可以被继承的类，因为它被声明为`open`。`myMethod`也是一个可以被重写的方法，因为它也被声明为`open`。`MyDerivedClass`是`MyBaseClass`的子类，它重写了`myMethod`方法。
 */
class StandardOutMessageRenderer : MessageRenderer {
    override var messageProvider: MessageProvider? = null
        set(value) {
            field = value
            println(" --> StandardOutMessageRenderer: setting the provider")
        }

    init {
        println(" --> StandardOutMessageRenderer: constructor called")
    }

    override fun render() {
        println(
            messageProvider?.message ?: throw RuntimeException(
                "You must set the property messageProvider of class:"
                        + StandardOutMessageRenderer::class.java.name
            )
        )
    }
}