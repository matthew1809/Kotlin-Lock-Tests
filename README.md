## Testing locks with Kotlin

### Write Then Read Test
This test shares an instance of a ReentrantReadWriteLock.

This test starts a given number of threads.

On each individual thread, it:
 1. Calls a function to increment a shared variable by 1 under a write lock.
 2. Calls a function to read the previously mutated variable under a read lock.
 
 We expect that the queued write locks will block all reads until all write operations are complete. 
 - This is proven by seeing that not of the read operations are logged until the writes are complete.
 - The opposite would occur if we called the read operation before the write operation in each thread.
 
 We expect in the same thread to read a different value than that thread has written because the read and write are not subject to the same lock.
 
 ## Write And Read Test
 
This test shares an instance of a ReentrantLock.

This test starts a given number of threads.

On each individual thread, it:
1. Starts a lock.withLock function
2. Calls a function to increment a shared variable by 1 under lock.
3. Calls a function to read the previously mutated variable under under same lock as lock read.
 
 We expect that the reads will always show their respective write value for that threads function. 
 - This is proven by seeing that a pattern of write + read is logged.
 

