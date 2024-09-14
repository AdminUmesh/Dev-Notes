# Boxing and Unboxing
Boxing and unboxing are important concepts in C# that deal with `converting value types to reference types and vice versa.`

**Note1:-** `Implicit Conversions: Automatically performed by the compiler when safe. Used for widening conversions or when a conversion is predefined and safe.`

**Note2:-** `Explicit Conversions: Manually specified by the developer using casting or conversion methods. Used for narrowing conversions or when there is potential for data loss or precision issues.`

## Boxing
Boxing is the process of converting a value type (like int, char, etc.) to a reference type (like object). This is done implicitly by the runtime. When a value type is boxed, it is wrapped inside an object and stored on the heap.

```c#
int num = 23;  // Value type
object obj = num;  // Boxing
```
**implicitly**

Implicit conversions are type conversions that the compiler automatically performs when it is safe to do so without any loss of data or precision. You don’t need to provide any special instructions for the conversion to occur; it happens automatically.

**When Implicit Conversions Occur**

- **Widening Conversions:** Converting from a smaller type to a larger type (e.g., from int to long, or from float to double).

- **Conversions Defined by the Framework:** Some conversions are predefined in the .NET framework. For instance, converting an int to a double is implicitly allowed because a double can represent all values that an int can.

```c#
int intValue = 123;
double doubleValue = intValue; // Implicit conversion from int to double
```

## Unboxing
Unboxing is the reverse process, where a reference type is converted back to a value type. This requires an explicit cast.

```C#
object obj = 23;  // Boxing
int num = (int)obj;  // Unboxing
```

**Explicit cast**

Explicit conversions require you to explicitly state the conversion you want to perform. This is usually done when converting from a larger type to a smaller type or when the conversion might result in loss of data or precision. In C#, you use casting or conversion methods for explicit conversions.

**When Explicit Conversions Are Needed**

- **Narrowing Conversions:** Converting from a larger type to a smaller type (e.g., from double to int). This can potentially result in data loss if the value doesn’t fit in the destination type.

- **Custom Conversion Logic:** When you need to define how a conversion should be performed for your own classes.

```C#
double doubleValue = 123.45;
int intValue = (int)doubleValue; // Explicit conversion from double to int
```