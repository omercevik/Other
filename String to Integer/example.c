/*
    A function named example1() which converts a string to an integer using recursion.
    Another function named example2() which converts a string to an integer using iteration.

    Compiler Version :
        gcc (Ubuntu 9.3.0-10ubuntu2) 9.3.0

    Compiled, Linked and Run using commands :
        gcc -c -Wall -pedantic example.c
        gcc example.o -o example
        ./example

    Test Operating System :
        Linux Ubuntu 20.04.1 LTS

    @author Omer CEVIK
*/
#include <stdio.h>

#define INT_MAX 2147483647
#define INT_MIN -2147483648

// One version of example1() using recursion.
void example1(const char* string, int* result, int isAlpha, int isNegative);

// One version of example1() using iteration.
int example2(const char* string);

int isSpace(char c);
int isNumber(char c);
int isNeg(char c);

int main(int argc, char const *argv[])
{
    const char* string1 = "42";
    const char* string2 = "   -42";
    const char* string3 = "4193 with words";
    const char* string4 = "words and 987";
    const char* string5 = "-91283472332";
    const char* string6 = "991283472332";

    int result = 0;

    printf("-------------------- Iterative example2() Results ------------------\n");


    result = example2(string1);
    printf("Input: \"%s\"\nOutput: %d\n\n", string1, result );

    result = example2(string2);
    printf("Input: \"%s\"\nOutput: %d\n\n", string2, result );

    result = example2(string3);
    printf("Input: \"%s\"\nOutput: %d\n\n", string3, result );

    result = example2(string4);
    printf("Input: \"%s\"\nOutput: %d\n\n", string4, result );

    result = example2(string5);
    printf("Input: \"%s\"\nOutput: %d\n\n", string5, result );

    result = example2(string6);
    printf("Input: \"%s\"\nOutput: %d\n\n", string6, result );



    printf("-------------------- Recursive example1() Results ------------------\n");

    result = 0;
    example1(string1, &result, 0, 0);
    printf("Input: \"%s\"\nOutput: %d\n\n", string1, result );

    result = 0;
    example1(string2, &result, 0, 0);
    printf("Input: \"%s\"\nOutput: %d\n\n", string2, result );

    result = 0;
    example1(string3, &result, 0, 0);
    printf("Input: \"%s\"\nOutput: %d\n\n", string3, result );

    result = 0;
    example1(string4, &result, 0, 0);
    printf("Input: \"%s\"\nOutput: %d\n\n", string4, result );

    result = 0;
    example1(string5, &result, 0, 0);
    printf("Input: \"%s\"\nOutput: %d\n\n", string5, result );

    result = 0;
    example1(string6, &result, 0, 0);
    printf("Input: \"%s\"\nOutput: %d\n\n", string6, result );
    return 0;
}

/*
    Iterative version of example1().
*/
int example2(const char* string)
{
    int result = 0;
    int i = 0, j = 0;
    int isAlpha = 0, isMinus = 0, isMaxMin = 0;
    int newResult = 0;

    for (; string[i] != '\0'; ++i)
    {
        // Checks is it a negative number.
        if (isNeg(string[i]) && isNumber(string[i+1]))
            isMinus = 1;

        // Checks there is non-number except space or minus character before number.
        if (!isSpace(string[i]) && !isNeg(string[i]) && !isNumber(string[i]))
            isAlpha = 1;

        // End the loop finding the first number character.
        if (isNumber(string[i]))
            break;
    }

    // Check if there is non-number character is detected then return 0.
    if (isAlpha)
        return 0;

    int digitCounter = 0;

    // Count the digits of number.
    while(isNumber(string[i]))
    {
        ++digitCounter;
        ++i;
    }

    --i;
    --digitCounter;

    while(digitCounter > -1)
    {
        // Get the least to most digit.
        newResult = string[i-digitCounter] - '0';

        // Multiply with 10 to put the number in correct place.
        for (j = 0; j < digitCounter; ++j)
            newResult *= 10;


        // Check if it there is an overflow.
        if (result >= INT_MAX/10 || result <= INT_MIN/10)
        {
            isMaxMin = 1;
            break;
        }

        // Add the new digit to result.
        result += newResult;
        --digitCounter;
    }


    // Check if the result is negative then multiply by -1.
    if (isMinus)
        return isMaxMin ? INT_MIN : -result;
    return isMaxMin ? INT_MAX : result;
}


/*
    Recursive version of example1().
*/
void example1(const char* string, int* result, int isAlpha, int isNegative)
{
    // Base case of recursion, checks the end of the string.
    if (isNumber(string[0]) == 0 && !isSpace(string[0]) && !isNeg(string[0]))
    {
        // Checks if the number is negative then multiply by -1.
        if (isNegative && (*result != INT_MIN))
            *result *= -1;
        return;
    }

    // Checks if the character is number.
    if (isNumber(string[0]) && !isAlpha)
    {
        // Counts the digits.
        int dcounter = 0, i = 0;
        for (; isNumber(string[i]); ++i)
            ++dcounter;

        // Multiply with 10 for digit counts to put the digit in right place.
        int temp = (string[0] - '0');
        while(--dcounter)
            temp *= 10;

        // Checks the overflow and inserts to result.
        if (*result >= INT_MAX/10 || *result <= INT_MIN/10)
            *result = isNegative ? INT_MIN : INT_MAX;
        else
            *result += temp;

        // Recursive call for next character.
        example1(&string[1], result, isAlpha, isNegative);
    }
    // Checks the character if it is not number and not space.
    else if (!isSpace(string[0]) && !isNumber(string[0]))
    {
        // Checks if the character is minus and next character is a number.
        if (isNeg(string[0]) && isNumber(string[1]))
            isNegative = 1;

        // Otherwise there is alpha character so result is 0.
        else
        {
            *result = 0;
            isAlpha = 1;
        }

        // Recursive call for next character.
        example1(&string[1], result, isAlpha, isNegative);
    }
    // Recursive call for next character.
    else
        example1(&string[1], result, isAlpha, isNegative);
}

// Returns the character is a number.
int isNumber(char c)
{
    return c <= '9' && c >= '0';
}

// Returns the character is a space character.
int isSpace(char c)
{
    return c == ' ';
}

// Returns the character is a minus character.
int isNeg(char c)
{
    return c == '-';
}