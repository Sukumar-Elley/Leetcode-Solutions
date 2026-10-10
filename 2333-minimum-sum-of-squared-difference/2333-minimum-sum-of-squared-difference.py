from typing import List

class Solution:
    def minSumSquareDiff(self, nums1: List[int], nums2: List[int], k1: int, k2: int) -> int:
        diff = [abs(a - b) for a, b in zip(nums1, nums2)]
        k = k1 + k2

        if sum(diff) <= k:
            return 0

        left, right = 0, max(diff)

        while left < right:
            mid = (left + right) // 2

            operations = sum(max(0, d - mid) for d in diff)

            if operations <= k:
                right = mid
            else:
                left = mid + 1

        # Reduce every difference to at most left.
        diff = [min(d, left) for d in diff]
        remaining = k - sum(
            original - reduced
            for original, reduced in zip(
                [abs(a - b) for a, b in zip(nums1, nums2)], diff
            )
        )

        # Use remaining operations to reduce the largest differences.
        for i in range(len(diff)):
            if remaining == 0:
                break
            if diff[i] == left and left > 0:
                diff[i] -= 1
                remaining -= 1

        return sum(d * d for d in diff)