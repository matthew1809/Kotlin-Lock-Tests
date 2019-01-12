package com.foyle

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

class WriteAndReadTest {
    private val lock = ReentrantLock()
    private val lockedProcesses = LockedProcesses()

    fun writeAndReadTest(numOfThreadsToUse: Int) {
        println("writeAndReadTest running")

        for (i in 0 until numOfThreadsToUse) {
            thread(start = true) {
                lock.withLock {
                    writeAction()
                    readAction()
                }
            }
        }
    }

    private fun writeAction() {
        lockedProcesses.writeValue(1)
    }

    private fun readAction() {
        lockedProcesses.getVariable()
    }
}