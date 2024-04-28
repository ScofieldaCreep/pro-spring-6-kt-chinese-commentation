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
package com.apress.prospring6.four

import com.apress.prospring6.four.impl.provider.ProviderConfig
import com.apress.prospring6.four.impl.renderer.RendererConfig
import com.apress.prospring6.two.decoupled.MessageProvider
import com.apress.prospring6.two.decoupled.MessageRenderer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

/**
 * Created by iuliana.cosmina on 01/04/2022
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {RendererConfig.class, ProviderConfig.class})
@SpringJUnitConfig(classes = [RendererConfig::class, ProviderConfig::class])
class MessageRenderThreeIT {
    @Autowired
    var messageRenderer: MessageRenderer? = null

    @Autowired
    var messageProvider: MessageProvider? = null
    @Test
    fun testProvider() {
        Assertions.assertNotNull(messageProvider)
    }

    @Test
    fun testRenderer() {
        Assertions.assertAll("messageTest",
            Executable { Assertions.assertNotNull(messageRenderer) },
            Executable { Assertions.assertNotNull(messageRenderer!!.messageProvider) }
        )
        messageRenderer!!.render()
    }
}