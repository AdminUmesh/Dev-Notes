# Data Structures
A data structure is a way to organize, manage, and store data in a computer so that it can be accessed and modified efficiently.

`Think of data structures as containers that hold data, and each type of container is optimized for certain operations, like searching, inserting, or deleting data.`

## Types of Data Structures

### 1. Linear Data Structures
Elements are arranged sequentially, one after another.

**Examples:**

- Array: Fixed-size collection of elements accessible by index.

- Linked List: Nodes linked one after another.

- Stack: Last In First Out (LIFO) structure. Think of a stack of plates.

- Queue: First In First Out (FIFO) structure. Like a line at a store.

### 2. Non-Linear Data Structures
Elements are not arranged sequentially; they are connected in a hierarchical or graph-based way.

**Examples:**

- Tree: Hierarchical structure with a root node and children nodes (e.g., binary tree).

- Graph: A set of nodes connected by edges, can be cyclic or acyclic.

- Heap: A special tree used to implement priority queues.


## 🔸 Essential Data Structures

- Arrays & Strings
  - Sliding Window
  - Two Pointers
  - Prefix Sum
  - In-place algorithms

- Linked Lists
  - Singly & Doubly Linked List
  - Reversals, Detecting Cycles (Floyd’s algorithm)

- Stacks & Queues
  - Monotonic Stack/Queue
  - Implementing Queue using Stacks
  - LRU Cache (with LinkedHashMap or custom)

- Hashing
  - HashMaps, HashSets
  - Frequency counting
  - Anagram detection

- Trees
  - Binary Tree, Binary Search Tree (BST)
  - Tree Traversals (DFS, BFS)
  - Lowest Common Ancestor (LCA)
  - Diameter, Height, Invert, Serialize/Deserialize
  - Trie (Prefix Tree)

- Heaps / Priority Queues
  - Min/Max Heaps
  - Top-K Elements, Median Stream
  - Custom Comparators

- Graphs
  - BFS, DFS
  - Topological Sort
  - Union-Find (Disjoint Sets)
  - Dijkstra’s, Prim’s, Kruskal’s Algorithm
  - Cycle Detection (Directed & Undirected)

- Advanced Data Structures
  - Segment Trees / Fenwick Tree (Binary Indexed Tree)
  - Tries with advanced use cases (autocomplete, IP routing)
  - Disjoint Set Union (DSU)

# What is an Algorithm?
An algorithm is a step-by-step procedure or set of rules to solve a specific problem or perform a computation. It’s like a recipe that tells you exactly how to do something, from start to finish.

### Key Characteristics of an Algorithm:
- **Input:** Receives zero or more inputs.

- **Output:** Produces at least one output.

- **Finiteness:** Must finish after a finite number of steps.

- **Definiteness:** Each step must be clearly defined.

- **Effectiveness:** Each step should be simple enough to be performed.

# Types of Algorithms (based on different criteria)

## 1. Based on Design Approach
- **Divide and Conquer:** Breaks problem into smaller subproblems, solves them independently, then combines the results.
**Example:** Merge Sort, Quick Sort

- **Greedy Algorithms:** Makes the best choice at each step to find a global optimum.
**Example:** Prim’s Algorithm, Kruskal’s Algorithm

- **Dynamic Programming:** Solves problems by breaking them down into overlapping subproblems and storing their solutions.
**Example:** Fibonacci sequence, Knapsack problem

- **Backtracking:** Tries all possible solutions and backtracks when a solution doesn’t work.
**Example:** Sudoku solver, N-Queens problem

## 2. Based on Purpose
- **Sorting Algorithms:** Arrange data in a certain order (ascending or descending).
**Examples:** Bubble Sort, Quick Sort, Merge Sort

- **Searching Algorithms:** Find specific data within a structure.
**Examples:** Linear Search, Binary Search

- **Graph Algorithms:** Work on graph data structures.
**Examples:** Dijkstra’s algorithm, BFS, DFS

## 3. Based on Implementation
**Recursive Algorithms:** Solve problems by calling themselves with smaller inputs.

**Iterative Algorithms:** Use loops to repeat steps.

- Simple Example of an Algorithm: Find the Maximum Number in a List
Initialize a variable max with the first element.

For each element in the list:

If the element is greater than max, update max.

After checking all elements, max holds the largest value.

# Summary Topic

- Sorting & Searching
  - Binary Search and Variants
  - Merge Sort, Quick Sort
  - In-place Sorting Techniques

- Recursion & Backtracking
  - Subsets, Permutations
  - N-Queens, Sudoku Solver
  - Palindrome Partitioning

- Dynamic Programming (DP)
  - 1D DP: Fibonacci, Climbing Stairs
  - 2D DP: Knapsack, Matrix Path
  - DP on Trees and Graphs
  - Memoization vs Tabulation

- Greedy Algorithms
  - Activity Selection
  - Huffman Coding
  - Job Scheduling

- Bit Manipulation
  - XOR tricks, Bitmask DP
  - Counting bits, Subsets

- Sliding Window
  - Longest Substring
  - Maximum Sum Subarray (Kadane’s)

- Two Pointers
  - Sorted arrays
  - Trapping Rain Water
  - 3Sum / 4Sum
