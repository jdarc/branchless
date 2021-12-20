# Branchless Demo

Additive blending is a common graphical operation,
where the Red, Green & Blue components of each color are added together
and clamped to the range [0:255]

For efficiency colors are packed into a single 24-bit or 32-bit integer, where
each byte represents the Red, Green & Blue components, usually encoded as 0xRRGGBB.

This project demonstrates two approaches to perform the clamp operation.
One with if statements (branching) and another version that avoids branching
by using clever bitwise operations.

The branchless version was quickly coded to showcase as part of a chat on a
software development channel, I am certain it could be optimised even further!

## Instructions

To build, test and run benchmarks:
```
./gradlew clean test run
```

## Algorithms

```kotlin
fun standard(x: Int) = if (x < 0) 0 else if (x > 255) 255 else x
```

```kotlin
fun coerce(x: Int) = x.coerceIn(0, 255)
```

```kotlin
fun minmax(x: Int) = max(0, min(255, x))
```

```kotlin
fun branchless(x: Int) = x and x.shr(31).inv() or (255 - x).shr(31) and 255
```

## Benchmark

Using OpenJDK 16.0.1, all results are in milliseconds.

### Hardware

- MacBook Pro (15-inch, 2018)
- 2.2 GHz 6-Core Intel Core i7
- 16 GB 2400 MHz DDR4

### Configuration

- Iterations: 400000000
- Loops: 16
- Warmups: 4

### Results

|      |  Standard  |   CoerceIn   |  Minmax  |  Branchless  |
|-----:|:----------:|:------------:|:--------:|:------------:|
|    1 |    598     |     591      |    33    |      33      |
|    2 |    624     |     625      |    62    |      61      |
|    3 |    597     |     593      |    33    |      33      |
|    4 |    596     |     596      |    33    |      32      |
|    5 |    594     |     593      |    33    |      32      |
|    6 |    596     |     594      |    33    |      33      |
|    7 |    594     |     591      |    33    |      32      |
|    8 |    591     |     597      |    33    |      32      |
|    9 |    591     |     589      |    33    |      33      |
|   10 |    594     |     589      |    33    |      32      |
|   11 |    595     |     593      |    33    |      33      |
|   12 |    594     |     594      |    33    |      33      |
|   13 |    592     |     590      |    33    |      32      |
|   14 |    593     |     588      |    33    |      32      |
|   15 |    599     |     593      |    33    |      33      |
|   16 |    592     |     599      |    33    |      32      |
|  Avg |    596     |     594      |    34    |      34      | 

## License

Copyright © 2021 Jean d'Arc

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.