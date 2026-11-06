# Time Complexity — Precise Notes

> **Purpose:** A practical guide to analyzing algorithm time complexity for coding interviews and everyday programming.
>
> **Core idea:** Time complexity describes how an algorithm's work grows as the input size grows. It is not a promise of exact seconds.

## 1. What does `n` mean?

`n` usually represents the input size: the number of elements in an array, characters in a string, nodes in a tree, or records being processed.

Example: If an array contains 100 elements, then `n = 100`.

## 2. Big O notation

Big O, written `O(...)`, describes an asymptotic **upper bound** on growth. In interview discussions, it is commonly used to describe an algorithm's worst-case growth unless another case is stated.

We usually ignore:
- Constant factors: `O(3n)` becomes `O(n)`.
- Lower-order terms: `O(n² + n + 10)` becomes `O(n²)`.

We do **not** ignore meaningful differences in growth: `O(n)` and `O(n²)` scale very differently.

## 3. Common time complexities

From generally more scalable to less scalable as `n` becomes large:

| Complexity | Name | Typical example |
|---|---|---|
| `O(1)` | Constant | Access an array element by index |
| `O(log n)` | Logarithmic | Binary search in a sorted array |
| `O(n)` | Linear | Scan an array once |
| `O(n log n)` | Linearithmic | Merge sort; typical efficient comparison sorting |
| `O(n²)` | Quadratic | Compare every pair using two nested loops |
| `O(n³)` | Cubic | Three nested loops, each running `n` times |
| `O(2ⁿ)` | Exponential | Generate all subsets of an `n`-element set |
| `O(n!)` | Factorial | Generate all permutations |

These are common patterns, not automatic rules. Analyze what the code actually does.

## 4. How the growth compares

For an illustrative input size `n = 10`:

| Complexity | Approximate growth value |
|---|---:|
| `O(1)` | 1 |
| `O(log₂ n)` | about 3.32 |
| `O(n)` | 10 |
| `O(n log₂ n)` | about 33.2 |
| `O(n²)` | 100 |
| `O(n³)` | 1,000 |
| `O(2ⁿ)` | 1,024 |
| `O(n!)` | 3,628,800 |

These are values of representative growth functions, **not measured execution times**. Real runtimes depend on constants, hardware, language, data, and implementation.

## 5. How to calculate complexity

### Rule A: One simple operation

```csharp
int first = numbers[0];
```

Assuming the array is non-empty and indexing is constant-time: **`O(1)`**.

### Rule B: One loop over `n` items

```csharp
for (int i = 0; i < n; i++)
{
    Console.WriteLine(i);
}
```

The body runs `n` times: **`O(n)`**.

### Rule C: Two separate loops

```csharp
for (int i = 0; i < n; i++) { /* constant work */ }
for (int j = 0; j < n; j++) { /* constant work */ }
```

Total work is about `n + n = 2n`: **`O(n)`**, not `O(n²)`.

### Rule D: Nested loops

```csharp
for (int i = 0; i < n; i++)
{
    for (int j = 0; j < n; j++)
    {
        // constant work
    }
}
```

The inner body runs `n × n` times: **`O(n²)`**.

### Rule E: A loop that halves the input

```csharp
while (n > 1)
{
    n /= 2;
}
```

The value is halved each iteration, so the number of iterations is proportional to `log₂ n`: **`O(log n)`**.

### Rule F: Outer loop plus logarithmic inner loop

```csharp
for (int i = 0; i < n; i++)
{
    for (int j = 1; j < n; j *= 2)
    {
        // constant work
    }
}
```

The inner loop runs `O(log n)` times for each of `n` outer iterations: **`O(n log n)`**.

### Rule G: Triangular nested loop

```csharp
for (int i = 0; i < n; i++)
{
    for (int j = 0; j < i; j++)
    {
        // constant work
    }
}
```

The total iterations are `0 + 1 + 2 + ... + (n - 1) = n(n - 1)/2`: **`O(n²)`**.

### Rule H: Input shrinks by one in a loop

```csharp
while (n > 0)
{
    n--;
}
```

There are about `n` iterations: **`O(n)`**.

### Rule I: Recursive calls

```csharp
int Sum(int n)
{
    if (n <= 0) return 0;
    return n + Sum(n - 1);
}
```

There are `n` recursive calls/levels and constant work per level: **`O(n)` time** and **`O(n)` auxiliary stack space**.

By contrast, a recurrence such as `T(n) = 2T(n/2) + O(n)` is typically **`O(n log n)`** (as in merge sort). Recursion must be analyzed by counting the subproblems and work at each level.

## 6. Binary search: why `O(log n)`?

Binary search requires sorted data. Each comparison discards roughly half of the remaining search range:

`n → n/2 → n/4 → n/8 → ... → 1`

After `k` steps, the remaining size is approximately `n / 2ᵏ`. Setting this to 1 gives `k ≈ log₂ n`, so the time complexity is **`O(log n)`**.

## 7. Best, average, and worst case

- **Best case:** The least work for an input of size `n`.
- **Average case:** Expected work under a stated input distribution.
- **Worst case:** The most work for an input of size `n`.

Example: Linear search through an array:
- Best case: target is first → `O(1)`.
- Average case: target is around the middle, under a simple uniform-position assumption → `O(n)`.
- Worst case: target is last or absent → `O(n)`.

Be clear about which case you are discussing.

## 8. Big O, Big Omega, and Big Theta

- **`O(g(n))`:** Asymptotic upper bound.
- **`Ω(g(n))`:** Asymptotic lower bound.
- **`Θ(g(n))`:** Tight asymptotic bound; both upper and lower bounds have the same growth order.

For example, `3n² + 5n + 2` is `O(n²)` and also `Θ(n²)`. Saying `O(n³)` is technically an upper bound too, but it is not the tightest useful description.

## 9. Time complexity is not space complexity

- **Time complexity:** How computational work grows with input size.
- **Space complexity:** How memory usage grows with input size.

Examples:
- Iterative scan of an array: `O(n)` time, often `O(1)` auxiliary space.
- Copying an array of `n` elements: `O(n)` time and `O(n)` additional space.
- Recursive `Sum(n)` above: `O(n)` time and `O(n)` call-stack space.

State whether you mean **auxiliary space** (extra memory beyond the input) or total space.

## 10. Common mistakes

1. Calling two sequential `O(n)` loops `O(n²)`. They add; they do not multiply.
2. Assuming every nested loop is `O(n²)`. A loop may run a constant number of times, logarithmically, or depend on another variable.
3. Calling a loop that doubles or halves a counter `O(n)`. It is often `O(log n)`.
4. Keeping constants in the final Big O result: simplify `O(5n + 20)` to `O(n)`.
5. Confusing Big O with exact runtime. Big O describes growth, not seconds.
6. Ignoring library operations. For example, list lookup, hash lookup, sorting, and inserting into a data structure have different costs and assumptions.
7. Forgetting that worst-case and average-case hash-table operations may differ.
8. Assuming recursion is automatically exponential. Count recursive calls and analyze the recurrence.

## 11. A quick interview checklist

When given code:
1. Identify the input size `n`.
2. Count how many times each loop/body runs.
3. For sequential sections, add the work.
4. For nested independent loops, multiply iteration counts.
5. For doubling/halving patterns, consider logarithmic growth.
6. For recursion, write a recurrence or count levels and branching.
7. Drop constant factors and lower-order terms.
8. State the case (best, average, or worst) and any assumptions.
9. If requested, analyze auxiliary space separately.

## 12. Quick practice — try before checking answers

1. One loop from `0` to `n - 1`: `O(?)`
2. Two nested loops, each running `n` times: `O(?)`
3. Repeatedly divide `n` by 2 until it reaches 1: `O(?)`
4. Two separate loops, each running `n` times: `O(?)`
5. A loop over `n` items, with binary search in each iteration: `O(?)`

**Answers:** 1. `O(n)`; 2. `O(n²)`; 3. `O(log n)`; 4. `O(n)`; 5. `O(n log n)`.

---

## One-line summary

**Count the work as input grows, identify its growth pattern, then keep the dominant term.**
