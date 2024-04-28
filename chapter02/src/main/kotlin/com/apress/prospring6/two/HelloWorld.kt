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

/**
 * 在Kotlin中，`@JvmStatic`是一个注解，它的作用是在生成的Java字节码中，将被注解的函数或属性生成为静态方法或属性。
 *
 * 在Kotlin中，`object`关键字用于声明单例对象。然而，在Java中，没有直接的等价概念。因此，当Kotlin代码被编译为Java字节码时，`object`会被编译为一个包含静态方法和属性的类。
 *
 * 例如，考虑以下Kotlin代码：
 *
 * ```kotlin
 * object HelloWorld {
 *     fun sayHello() {
 *         println("Hello, world!")
 *     }
 * }
 * ```
 *
 * 在Java中，你不能直接调用`HelloWorld.sayHello()`，因为`sayHello`不是一个静态方法。你需要先获取`HelloWorld`的实例，然后调用其`sayHello`方法，如下所示：
 *
 * ```java
 * HelloWorld.INSTANCE.sayHello();
 * ```
 *
 * 这是因为在Java字节码中，`HelloWorld`被编译为一个类，而`INSTANCE`是这个类的一个静态字段，它持有`HelloWorld`的唯一实例。
 *
 * 然而，如果你在Kotlin中使用`@JvmStatic`注解，那么被注解的函数或属性会被编译为Java的静态方法或属性。例如：
 *
 * ```kotlin
 * object HelloWorld {
 *     @JvmStatic
 *     fun sayHello() {
 *         println("Hello, world!")
 *     }
 * }
 * ```
 *
 * 现在，你可以在Java中直接调用`HelloWorld.sayHello()`，就像你在Kotlin中一样：
 *
 * ```java
 * HelloWorld.sayHello();
 * ```
 *
 * 因此，`@JvmStatic`注解在Kotlin和Java互操作时非常有用，它可以让你的Kotlin代码更自然地适应Java的语义。
 */
object HelloWorld {
    @JvmStatic
    fun main(args : Array<String>) {
        println("Hello World")
    }
}