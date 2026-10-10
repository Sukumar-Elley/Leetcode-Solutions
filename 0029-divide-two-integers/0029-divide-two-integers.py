
class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        INT_MAX = 2**31 - 1
        INT_MIN = -(2**31)

        # Handle overflow case
        if dividend == INT_MIN and divisor == -1:
            return INT_MAX

        # Determine the sign of the result
        negative = (dividend < 0) != (divisor < 0)

        # Work with positive values
        dividend = abs(dividend)
        divisor = abs(divisor)

        quotient = 0

        # Subtract the largest possible shifted divisor
        for shift in range(31, -1, -1):
            if (dividend >> shift) >= divisor:
                dividend -= divisor << shift
                quotient += 1 << shift

        # Apply the sign
        if negative:
            quotient = -quotient

        return quotient