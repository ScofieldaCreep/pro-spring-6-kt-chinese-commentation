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

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

/**
 * Created by iuliana.cosmina on 06/03/2022
 */
@Component
class ConstructorConfusion {
    private var someValue: String

    constructor(someValue: String) {
        println("ConstructorConfusion(String) called")
        this.someValue = someValue
    }

    /**
     * 这里@Value("90")有什么用？直接someValue = 90 : Int不行吗
     *
     * 在这段代码中，`@Value("90")`注解用于为构造函数的参数`someValue`提供一个默认值。`@Value`注解可以用于从Spring的Environment中注入属性值。在这个例子中，它被用于直接注入一个固定的值"90"。
     *
     * 如果你直接写`someValue = 90 : Int`，那么`someValue`的值将始终为90，你无法通过Spring的配置来改变它。而使用`@Value("90")`，你可以在Spring的配置文件中通过设置相应的属性来改变`someValue`的值，增加了代码的灵活性。
     *
     * 例如，你可以在Spring的配置文件中这样设置：
     *
     * ```properties
     * someValue=100
     * ```
     *
     * 然后在你的代码中这样使用：
     *
     * ```kotlin
     * @Autowired
     * constructor(@Value("${someValue}") someValue: Int) {
     *     //...
     * }
     * ```
     *
     * 这样，`someValue`的值就会被设置为配置文件中的值，即100。如果配置文件中没有设置`someValue`，那么它的值将为`@Value`注解中的默认值，即90。
     *
     * 而且，如果写 constructor(someValue: Int = 90) 会报错 Could not autowire. No beans of 'int' type found.
     *
     * 另外，对于constructor, @Autowired 只能有一个：The example in Listing 3-14 also highlights that the @Autowired annotation can be applied to only one
     * of the constructors within a class. If we apply the annotation to more than one constructor method, Spring will
     * complain while bootstrapping ApplicationContext.
     */
    @Autowired // this is what makes this work
    constructor(@Value("90") someValue: Int) {
        println("ConstructorConfusion(int) called")
        this.someValue = "Number: " + Integer.toString(someValue)
    }

    override fun toString(): String {
        return someValue
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ctx = AnnotationConfigApplicationContext()
            ctx.register(ConstructorConfusion::class.java)
            ctx.refresh()
            val cc = ctx.getBean(ConstructorConfusion::class.java)
            println("Does this work? $cc")
        }
    }
}