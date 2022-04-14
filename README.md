# COP4520-Assignment-3
Geela Margo Ramos

## INSTALLATION
For Part One: (Was not completed)

For Part Two:
1. Open a terminal and navigate to the location of ```AtmosphereTemp```
2. Compile the program with the following command: ```javac AtmosphereTemp.java```
3. Run the compiled program with the following command: ```java AtmosphereTemp```

## PROOF OF CORRECTNESS
As this was a simulation, I depended on what text I had displayed throughout the simulation
to determine how the threads interacted with the shared variable(s). This code implements
from the book "Coarse-grained synchronization." The list itself , thus, has a single lock which
every method call must acquire. The principle advantage of this algorithm is that it is correct.
All methods act on the list only while holding the lock so the execution is essentially sequential.

## EXPERIMENTAL EVALUATION/EFFICIENCY
For efficiency, the time for the code to run a simulated "hour" is printed at
the end of each report; this is because minutes are conducted by every iteration calling
for the threads to run a reading.

Otherwise, in evaluating this code, as is for any implementation of the CoarseList, we know
that the Coarse List class satisifies the same progress condition as its lock: if the Lock is
starvation-free, so is our implementation. If contention is very low, this algorithm is an excellent
wa to immplement list. On the opposite hand, if there is contention, then even if the the lock itself
performs well, threads will still be delayed waiting for one another. This can occur over time as
readings occur at the same time as reports are being generated. Concurrency could have been improved by
locking individual nodes, such as with fine-grained synchronization.

Citation: Chapter 9 from the Textbook, Page 206-207

## SIDE NOTE
My homework couldn't be completed because I've been recovering from surgery. I already let the professor know, 
but have included the emails here. Thus, any points in general and feedback on how this HW
assignment could have been completed would be appreciated.
![image](https://user-images.githubusercontent.com/59593004/163306263-87f2ade8-b80a-42c7-8065-1dc930d9f6a4.png)
