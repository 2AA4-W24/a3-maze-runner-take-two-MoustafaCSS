# Assignment A1 - Maze Runner

* **Student**: [Moustafa Moustafa](moustm4@mcmaster.ca)
* **Credit for initial structure of code**: [Alexandre Lachance](alexandrelachance@me.com)
* **Program**: B. Eng. In Software Engineering
* **Course code**: SFWRENG 2AA4
* **Course Title**: Software Design I - Introduction to Software Development
* Term: *Level II - Winter 2024*

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory.
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by
  the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’.
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
    - For this assignment, the path does not have to be the shortest one.
- The program can take a path as input and verify if it's a legit one.

## How to run this software?

To build the program, simply package it with Maven:

```
moustm4@DESKTOP A1-Template % mvn -q clean package 
```

### Provided version (starter code)

The starter code assumes the maze file name is the first argument.

```
moustm4@DESKTOP A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txt
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txt
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
WALL PASS PASS PASS PASS PASS PASS PASS PASS PASS WALL 
WALL WALL WALL PASS WALL WALL WALL PASS WALL WALL WALL 
WALL PASS PASS PASS PASS PASS WALL PASS PASS PASS WALL 
WALL PASS WALL PASS WALL WALL WALL WALL WALL PASS WALL 
WALL PASS WALL PASS PASS PASS PASS PASS WALL PASS PASS 
WALL WALL WALL PASS WALL PASS WALL WALL WALL WALL WALL 
WALL PASS PASS PASS WALL PASS PASS PASS PASS PASS WALL 
PASS PASS WALL PASS WALL PASS WALL WALL WALL PASS WALL 
WALL PASS WALL PASS WALL PASS WALL PASS PASS PASS WALL 
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

When called on a non-existing file. it prints an error message

```
moustm4@DESKTOP A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txtd
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txtd
/!\ An error has occured /!\
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

### Delivered version

#### Command line arguments

The delivered program at the end of this assignment should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-method {tremaux, righthand, BFS}`: specifies which path computation method to use. (default is BFS)
- `-baseline {tremaux, righthand, BFS}`: specifies which path computation method to use. (default is BFS)

#### Examples

When no logs are activated, the programs only print the computed path on the standard output.

```
moustm4@DESKTOP A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
4F
moustm4@DESKTOP A1-Template %
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
mosser@azrael A1-Template %
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
inccorrect path
mosser@azrael A1-Template %
```

## How to Benchmark and Compare 

- `-baseline XXX`: compare current method to baseline method, XXX

for example: if right-hand gives you a path containing 113 instructions as a baseline, and Tremeaux a path with 37 instructions as the optimized method. 𝑆𝑝𝑒𝑒𝑑𝑢𝑝 = |path of baseline|/|path of method| = 3.05
So overall, using Tremeaux allows one to escape the maze three times faster in this case.

Example command to test BFS against baseline righthand: 
```
java -jar target/mazerunner.jar -i ./examples/small.maz.txt -method BFS -baseline righthand
```
3[INFO ] Main ** Starting Maze Runner
[INFO ] Main Computing path
3F L 4F R 3F
Time to load in the maze from the file: 1.00 milliseconds
[INFO ] Main Computing path
[INFO ] Main Computing path
Time to solve the maze using the BFS method: 2 ms
Time to solve the maze using the righthand method: 11 ms
The BFS algorithm is 2.17 times faster than righthand
[INFO ] Main End of MazeRunner

## Maze Solving algorithms that can be used in the code

- BFS 
- RightHand 
- Tremaux 
