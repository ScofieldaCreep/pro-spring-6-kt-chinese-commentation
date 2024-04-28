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

class HelloWorldMessageProvider : MessageProvider {
    init {
        println("\tHelloWorldMessageProvider --> constructor called")
    }

    /**
     * 在Kotlin中，你可以直接使用 `val message: String = "Hello World"` 来定义一个只读的属性，并给它赋值。这种方式下，`message`的值在对象创建时就已经确定，且后续不能被修改。
     *
     * 然而，如果你想让属性的值在每次访问时都能动态计算，那么你就需要使用自定义的getter方法。在你的原始代码中，`message`属性使用了自定义的getter方法，即使这个getter方法每次都返回相同的值。
     *
     * 以下是两种定义方式的对比：
     *
     * ```kotlin
     * class HelloWorldMessageProvider1 : MessageProvider {
     *     override val message: String = "Hello World!"  // 直接赋值，值在对象创建时确定
     * }
     *
     * class HelloWorldMessageProvider2 : MessageProvider {
     *     override val message: String
     *         get() = "Hello World!"  // 使用自定义getter方法，值在每次访问时计算
     * }
     * ```
     *
     * 在这个特定的例子中，两种方式的效果是一样的，因为`get()`方法总是返回相同的值。但如果你想让`message`的值能够根据某些条件或状态动态变化，那么你就需要使用自定义的getter方法。
     */
    override val message: String
        get() = "Hello World!"
}