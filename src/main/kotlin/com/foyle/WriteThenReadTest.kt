package com.foyle

import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.thread
import kotlin.concurrent.write

class WriteThenReadTest {
    private val lock = ReentrantReadWriteLock()
    private val lockedProcesses = LockedProcesses()

    fun writeThenReadTest(numOfThreadsToUse: Int) {
        println("writeThenReadTest running")

        for (i in 0 until numOfThreadsToUse) {
            thread(start = true) {
                writeAction()
                readAction()
            }
        }
    }

    private fun writeAction() {
        lock.write {
            lockedProcesses.writeValue(1)
        }
    }

    private fun readAction() {
        lock.read {
            lockedProcesses.getVariable()
        }
    }
}