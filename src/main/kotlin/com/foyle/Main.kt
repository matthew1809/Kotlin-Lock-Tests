package com.foyle

import kotlin.jvm.JvmStatic

class Main: Thread() {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            LockTests().writeThenReadTest(10)
        }
    }
}

