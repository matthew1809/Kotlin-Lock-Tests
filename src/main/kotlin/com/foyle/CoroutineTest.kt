package com.foyle

import kotlinx.coroutines.*
import kotlin.system.*
import kotlinx.coroutines.sync.*

class CoroutineTest {

    suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
        val n = 100  // number of coroutines to launch
        val k = 1000 // times an action is repeated by each coroutine
        val time = measureTimeMillis {
            val jobs = List(n) {
                launch {
                    repeat(k) { action() }
                }
            }
            jobs.forEach { it.join() }
        }
        println("Completed ${n * k} actions in $time ms")
    }

    var mutexCounter = 0
    var singleThreadCounterWithCoroutineScope = 0
    var singleThreadCounterWithGlobalScope = 0
    var NoControlCounter = 0
    val counterContextWithSingleThread = newSingleThreadContext("CounterContext")
    val mutex = Mutex()

    fun run() = runBlocking {

        GlobalScope.massiveRun {
            mutex.withLock {
                mutexCounter++
            }
        }
        println("Mutex Counter = $mutexCounter\n")

        CoroutineScope(counterContextWithSingleThread).massiveRun { // run each coroutine in the single-threaded context
            singleThreadCounterWithCoroutineScope++
        }
        println("Single Thread counter with CoroutineScope = $singleThreadCounterWithCoroutineScope\n")


        GlobalScope.massiveRun { // run each coroutine with DefaultDispathcer
            withContext(counterContextWithSingleThread) { // but confine each increment to the single-threaded context
                singleThreadCounterWithGlobalScope++
            }
        }
        println("Single Thread counter with GlobalScope = $singleThreadCounterWithGlobalScope\n")


        GlobalScope.massiveRun {
            NoControlCounter++
        }
        println("No control counter with GlobalScope = $NoControlCounter\n")
    }
}