# Space Complexity — Precise Notes

> **Purpose:** A practical guide to analyzing memory usage in algorithms, with C# examples for interviews.
>
> **Core idea:** Space complexity describes how memory requirements grow as the input size grows.

## 1. What does `n` mean?

`n` usually represents input size: the number of elements in an array, characters in a string, nodes in a tree, or records being processed.

Space complexity is about how memory scales with `n`, not a fixed number of bytes on one particular computer.

## 2. Space complexity vs. time complexity

- **Time complexity:** How computational work grows with input size.
- **Space complexity:** How memory usage grows with input size.

An algorithm can use little extra memory but take more time, or use extra memory to run faster.

## 3. Auxiliary space vs. total space

These terms are sometimes used differently across interview questions, so state your assumption.

- **Auxiliary space:** Extra memory used by the algorithm, usually excluding the input storage itself.
- **Total space:** Input storage plus auxiliary memory and other memory included by the analysis.

Example: Scanning an existing array of `n` integers using a single counter takes `O(1)` auxiliary space. If the input array itself is included, its storage is `O(n)`.

## 4. Common space complexities

| Complexity | Meaning | Typical example |
|---|---|---|
| `O(1)` | Constant extra space | A few counters and variables |
| `O(log n)` | Logarithmic extra space | Recursive binary search stack |
| `O(n)` | Linear extra space | Creating a copy of an array |
| `O(n log n)` | Extra space with this growth | Some algorithms or data structures at particular scales; analyze the specific implementation |
| `O(n²)` | Quadratic extra space | An `n × n` matrix |
| `O(2ⁿ)` | Exponential space | Storing every subset of an `n`-element set |

These are patterns, not automatic labels. Count what the code allocates and retains.

## 5. How to analyze space complexity

### Rule A: A fixed number of variables → `O(1)`

```csharp
int sum = 0;
int count = 0;
int current = 0;
```

The number of variables does not grow with `n`, so the auxiliary space is **`O(1)`**.

### Rule B: A new array of size `n` → `O(n)`

```csharp
int[] copy = new int[n];

for (int i = 0; i < n; i++)
{
    copy[i] = numbers[i];
}
```

The new array stores `n` elements, so auxiliary space is **`O(n)`**. The loop also takes `O(n)` time.

### Rule C: A fixed-size array → `O(1)` with respect to `n`

```csharp
int[] counters = new int[26];
```

If the alphabet is fixed at 26 entries, its space is **`O(1)`** relative to input length. If the array size were based on `n`, it would be `O(n)`.

### Rule D: A two-dimensional `n × n` array → `O(n²)`

```csharp
int[,] grid = new int[n, n];
```

The array stores `n × n` values, so its space is **`O(n²)`**.

### Rule E: Recursion uses call-stack memory

```csharp
int Sum(int n)
{
    if (n <= 0) return 0;
    return n + Sum(n - 1);
}
```

There are about `n` active recursive calls before the base case returns. The call stack therefore uses **`O(n)` auxiliary space**.

### Rule F: Recursion with logarithmic depth → `O(log n)` stack space

```csharp
int BinarySearch(int[] a, int low, int high, int target)
{
    if (low > high) return -1;

    int mid = low + (high - low) / 2;
    if (a[mid] == target) return mid;

    if (target < a[mid])
        return BinarySearch(a, low, mid - 1, target);

    return BinarySearch(a, mid + 1, high, target);
}
```

Each call halves the search range, and the maximum recursion depth is `O(log n)`. Therefore, recursive stack space is **`O(log n)`**. This assumes the input array is excluded from auxiliary space.

An **iterative** binary search uses **`O(1)` auxiliary space** because it uses a fixed number of variables.

### Rule G: A collection that grows with input → usually `O(n)`

```csharp
var result = new List<int>();

foreach (int value in numbers)
{
    result.Add(value);
}
```

If the list retains up to `n` values, its auxiliary space is **`O(n)`**. A list's internal capacity may be larger than its count by a constant factor, but the asymptotic complexity remains linear.

## 6. Common data structures: typical space costs

| Structure | Typical space |
|---|---|
| One integer / a fixed number of variables | `O(1)` |
| Array with `n` elements | `O(n)` |
| List storing `n` elements | `O(n)` |
| Hash set storing up to `n` items | `O(n)` |
| Dictionary storing up to `n` key-value pairs | `O(n)` |
| Adjacency list for a graph | `O(V + E)` |
| Adjacency matrix for a graph with `V` vertices | `O(V²)` |
| Recursion with depth `d` and constant data per call | `O(d)` stack space |

`V` means number of vertices and `E` means number of edges. Actual memory depends on representation and implementation details.

## 7. In-place algorithms

An algorithm is often called **in-place** when it modifies data directly and uses only a small amount of extra memory, typically `O(1)` auxiliary space. Definitions can vary, especially when recursion or library buffers are involved.

Example: swapping two array elements:

```csharp
int temp = numbers[i];
numbers[i] = numbers[j];
numbers[j] = temp;
```

Only a fixed number of extra variables are used: **`O(1)` auxiliary space**.

“In-place” does not mean “uses no memory.” It means it uses little additional memory under the stated definition.

## 8. Recursion: count the maximum active stack

The number of total recursive calls is not always the same as the maximum stack space. Space on the call stack depends mainly on the maximum recursion depth and what each active call retains.

Examples:
- `Sum(n)` calls `Sum(n - 1)`: depth `n` → `O(n)` stack space.
- Recursive binary search halves the range: depth `log n` → `O(log n)` stack space.
- A depth-first traversal of a tree may use `O(h)` stack space, where `h` is tree height; in the worst case, `h` can be `n`.

For a recursive algorithm that creates and retains additional arrays or collections, count those allocations too.

## 9. Time and space can differ

| Example | Time | Auxiliary space |
|---|---:|---:|
| Scan an array once, using counters | `O(n)` | `O(1)` |
| Copy an array | `O(n)` | `O(n)` |
| Iterative binary search | `O(log n)` | `O(1)` |
| Recursive binary search | `O(log n)` | `O(log n)` stack |
| Build an `n × n` matrix | `O(n²)` to initialize all cells | `O(n²)` |
| Merge sort on an array | `O(n log n)` | Common array implementations use `O(n)` auxiliary memory |

Complexities can vary by implementation. For example, sorting algorithms have different memory requirements even when their time complexities are similar.

## 10. Common mistakes

1. Saying an array scan uses `O(n)` auxiliary space just because it visits `n` elements. If it stores only a fixed number of variables, auxiliary space is `O(1)`.
2. Forgetting recursive call-stack memory.
3. Counting only the number of variables while ignoring arrays, lists, dictionaries, sets, or buffers.
4. Confusing total space with auxiliary space.
5. Assuming “in-place” means absolutely no extra memory.
6. Assuming all sorting algorithms use the same extra space.
7. Counting total recursive calls instead of maximum simultaneous call depth for stack space.
8. Ignoring what a function or library retains in memory.

## 11. A quick interview checklist

When analyzing space:
1. Clarify whether input storage is excluded.
2. Identify variables and allocations that grow with `n`.
3. Count arrays, collections, maps, sets, and temporary buffers.
4. Count maximum recursion depth and active call-stack storage.
5. Check whether memory is retained simultaneously or released/reused.
6. Keep the dominant growth term and drop constant factors.
7. State auxiliary space and total space separately when ambiguity matters.

## 12. Quick practice

Try each question before checking the answers.

1. Scan an existing array of `n` elements with one counter. What is the auxiliary space?
2. Create a new array containing `n` copied elements. What is the auxiliary space?
3. Run iterative binary search on an existing sorted array. What is the auxiliary space?
4. Run recursive binary search on an existing sorted array. What is the auxiliary stack space?
5. Allocate a matrix with `n` rows and `n` columns. What is its space complexity?
6. Store all `n` items in a dictionary. What is its typical space complexity?
7. Recursively process `n` items with one call per item and no tail-call optimization assumed. What is the maximum stack space?

**Answers:**
1. `O(1)`
2. `O(n)`
3. `O(1)`
4. `O(log n)`
5. `O(n²)`
6. `O(n)`
7. `O(n)`

---

## One-line summary

**Space complexity measures how memory grows with input size; count extra allocations and maximum active recursion depth, then keep the dominant term.**
