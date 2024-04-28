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

import java.lang.Exception
import java.util.*

/**
 * 在Kotlin中，`object`关键字用于声明单例对象。当你声明一个`object`时，Kotlin会在程序运行时自动创建这个对象的唯一实例。这个实例在第一次被访问时创建，然后在后续的访问中重复使用。
 *
 * 在你的代码中，`MessageSupportFactory`是一个`object`，所以它是一个单例对象。它的初始化发生在第一次被访问时，这是由Kotlin自动处理的。在`MessageSupportFactory`的定义中，有一个`init`块，这个块中的代码在`MessageSupportFactory`被初始化时执行。
 *
 * 在`HelloWorldDecoupledWithFactory`的`main`函数中，当你访问`MessageSupportFactory.renderer`和`MessageSupportFactory.provider`时，`MessageSupportFactory`会被初始化，`init`块中的代码会被执行。这个过程只会发生一次，因为`MessageSupportFactory`是一个单例对象。
 */
object MessageSupportFactory {
    var renderer: MessageRenderer? = null
    var provider: MessageProvider? = null

    init {
        val props = Properties()
        try {
            props.load(this.javaClass.getResourceAsStream("/msf.properties"))
            val rendererClass = props.getProperty("renderer.class")
            val providerClass = props.getProperty("provider.class")
            renderer = Class.forName(rendererClass).getDeclaredConstructor().newInstance() as MessageRenderer
            provider = Class.forName(providerClass).getDeclaredConstructor().newInstance() as MessageProvider
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}