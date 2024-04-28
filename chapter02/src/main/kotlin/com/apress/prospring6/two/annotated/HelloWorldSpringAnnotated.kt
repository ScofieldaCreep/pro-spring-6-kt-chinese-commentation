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

import com.apress.prospring6.two.decoupled.MessageProvider
import com.apress.prospring6.two.decoupled.MessageRenderer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * In Kotlin, there are several keywords used as class and method modifiers. Here are some of them:
 *
 * 1. `open`: This keyword is used to allow a class or method to be inheritable or overridable. By default, all classes and methods in Kotlin are `final`, which means they can't be inherited or overridden.
 *
 * 2. `final`: This keyword is used to prevent a class or method from being inherited or overridden. This is the default behavior in Kotlin, so you don't usually need to use this keyword unless you're overriding a method that is `open` and you want to prevent further overriding.
 *
 * 3. `abstract`: This keyword is used to declare a class that cannot be instantiated or a method that has no implementation and must be overridden in a subclass.
 *
 * 4. `override`: This keyword is used when a method is overriding a method in a superclass or implementing a method from an interface.
 *
 * 5. `private`: This keyword is used to restrict the visibility of a class, method, or property to the file in which it's declared.
 *
 * 6. `public`: This keyword is used to make a class, method, or property visible everywhere. This is the default visibility in Kotlin, so you don't usually need to use this keyword.
 *
 * 7. `protected`: This keyword is used to make a class, method, or property visible only inside the class and its subclasses.
 *
 * 8. `internal`: This keyword is used to make a class, method, or property visible everywhere in the same module.
 *
 * 9. `sealed`: This keyword is used to represent restricted class hierarchies. A `sealed` class can have subclasses, but all of them must be declared in the same file as the sealed class itself.
 *
 * 10. `data`: This keyword is used to create a data class that automatically generates `equals()`, `hashCode()`, `toString()`, `copy()`, and some other methods.
 *
 * 11. `inline`: This keyword is used to request the compiler to inline the marked function, i.e., replace function call sites with the body of the function.
 *
 * 12. `tailrec`: This keyword is used to mark a function as tail-recursive. This allows the compiler to optimize the recursion, leaving behind a fast and efficient loop based version instead.
 *
 * 13. `suspend`: This keyword is used to mark a function as a suspending function. Suspending functions are at the heart of everything coroutines-related in Kotlin.
 */

object HelloWorldSpringAnnotated {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx: ApplicationContext = AnnotationConfigApplicationContext(HelloWorldConfiguration::class.java)
        val mr: MessageRenderer = ctx.getBean("renderer", MessageRenderer::class.java)
        mr.render()
    }
}