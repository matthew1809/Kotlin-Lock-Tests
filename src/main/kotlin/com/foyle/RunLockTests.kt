package com.foyle

import java.util.concurrent.TimeUnit

class RunLockTests {

    fun run() {
        val WTR = WriteThenReadTest()
        WTR.writeThenReadTest(10)

        TimeUnit.SECONDS.sleep(10L)

        val WAR = WriteAndReadTest()
        WAR.writeAndReadTest(10)

        val CT = CoroutineTest()
        CT.run()
    }
}