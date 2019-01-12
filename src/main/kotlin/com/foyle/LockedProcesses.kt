package com.foyle

class LockedProcesses {

    var testing = 1

    fun writeValue(value: Int) {

        println("${Thread.currentThread()} adding $value to $testing")

        testing+= value

    }

    fun getVariable(): Int {
        println("${Thread.currentThread()} read $testing")
        return testing
    }

}