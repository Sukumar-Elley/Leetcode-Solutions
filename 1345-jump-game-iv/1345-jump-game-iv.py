from collections import defaultdict, deque
from typing import List

class Solution:
    def minJumps(self, arr: List[int]) -> int:
        n = len(arr)

        if n == 1:
            return 0

        positions = defaultdict(list)
        for i, value in enumerate(arr):
            positions[value].append(i)

        queue = deque([(0, 0)])
        visited = {0}

        while queue:
            index, steps = queue.popleft()

            if index == n - 1:
                return steps

            # Jump to adjacent indices
            for next_index in (index - 1, index + 1):
                if 0 <= next_index < n and next_index not in visited:
                    visited.add(next_index)
                    queue.append((next_index, steps + 1))

            # Jump to all indices with the same value
            value = arr[index]
            for next_index in positions[value]:
                if next_index not in visited:
                    visited.add(next_index)
                    queue.append((next_index, steps + 1))

            # Prevent repeatedly processing this value's indices
            positions[value].clear()

        return -1