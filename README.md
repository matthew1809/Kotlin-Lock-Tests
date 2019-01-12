## Testing locks with Kotlin

### Write Then Read Test
This test shares an instance of a ReentrantReadWriteLock.

This test starts a given number of threads.

On each individual thread, it: \
    1. Applies a write lock. \
    2. Calls a function to increment a shared variable by 1.\
    3. Ends the write lock.\
    4. Applies a read lock.\
    5. Calls a function to read the previously mutated variable.\
    6. Ends the read lock.

