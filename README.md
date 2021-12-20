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

Run tests:
> ./gradlew test

Run console application:
> ./gradlew run

## Benchmark
Using OpenJDK 16.0.1, all results are in milliseconds.

### Hardware
- MacBook Pro (15-inch, 2018)
- 2.2 GHz 6-Core Intel Core i7
- 16 GB 2400 MHz DDR4

### Configuration
- Iterations: 100000000
- Loops: 10
- Warmups: 2

### Results
| Pass | Branching                     | Branchless                    |
|------|-------------------------------|-------------------------------|
| 1    | 304                           | 379                           |
| 2    | 332                           | 376                           |
| 3    | 319                           | 380                           |
| 4    | 323                           | 375                           |
| 5    | 320                           | 373                           |
| 6    | 321                           | 374                           |
| 7    | 320                           | 376                           |
| 8    | 321                           | 374                           |
| 9    | 320                           | 375                           |
| 10   | 326                           | 374                           |
|      | Average: 320 <br/>Median: 321 | Average: 375 <br/>Median: 375 |

## License
Copyright © 2021 Jean d'Arc

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.