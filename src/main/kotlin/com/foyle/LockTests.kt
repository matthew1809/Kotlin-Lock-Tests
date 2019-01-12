package com.foyle

import kotlin.concurrent.thread
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

class LockTests {

    private val lock = ReentrantReadWriteLock()
    private val lockedProcesses = LockedProcesses()

    fun writeThenReadTest(numOfThreadsToUse: Int) {
        for (i in 0 until numOfThreadsToUse) {
            thread(start = true) {
                writeAction()
                readAction()
            }
        }
    }

    private fun writeAction() {
        lock.write {
            if(lock.isWriteLockedByCurrentThread) {
                println("Writing locked by the thread ${Thread.currentThread()}")
            }
            lockedProcesses.writeValue(1)
        }
    }

    private fun readAction() {
        lock.read {
            println("Read lock count is:  " + lock.readLockCount + ". Read hold count is: " + lock.readHoldCount)
            lockedProcesses.getVariable()
        }
    }
}