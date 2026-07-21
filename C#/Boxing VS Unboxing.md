# Boxing and Unboxing
Boxing and unboxing are concepts that deal with `converting value types to reference types and vice versa.`

**Note1:-** `Implicit Conversions: Automatically performed by the compiler when safe.`

**Note2:-** `Explicit Conversions: Explicit Conversions: Manually specified by the developer using casting or conversion methods. Used for narrowing conversions or when there is potential for data loss or precision issues.`

## Boxing
Boxing is the process of converting a value type (like int, char, etc.) to a reference type (like object). This is done implicitly by the runtime. When a value type is boxed, it is wrapped inside an object and stored on the heap.

```c#
int num = 23;  // Value type
object obj = num;  // Boxing
```
**implicitly**

Implicit conversions are type conversions that the compiler automatically performs when it is safe to do so without any loss of data or precision.

**When Implicit Conversions Occur**

- **Widening Conversions:** Converting from a smaller type to a larger type (e.g., from int to long, or from float to double).


```c#
int intValue = 123;
double doubleValue = intValue; // Implicit conversion from int to double
```

## Unboxing
Unboxing is the process of converting a reference type (like object) to a value type (like int, char, etc.). This requires an explicit cast.

```C#
object obj = 23;  // Boxing
int num = (int)obj;  // Unboxing
```

**Explicit cast**

Explicit conversions usually done when converting from a larger type to a smaller type or when the conversion might result in loss of data or precision.

**When Explicit Conversions Are Needed**

- **Narrowing Conversions:** Converting from a larger type to a smaller type (e.g., from double to int). This can potentially result in data loss if the value doesn’t fit in the destination type.

```C#
double doubleValue = 123.45;
int intValue = (int)doubleValue; // Explicit conversion from double to int
```