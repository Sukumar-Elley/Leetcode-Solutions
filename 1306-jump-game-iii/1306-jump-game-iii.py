from collections import deque
from typing import List

class Solution:
    def canReach(self, arr: List[int], start: int) -> bool:
        n = len(arr)
        queue = deque([start])
        visited = set()

        while queue:
            i = queue.popleft()

            if arr[i] == 0:
                return True

            if i in visited:
                continue

            visited.add(i)

            for next_index in (i + arr[i], i - arr[i]):
                if 0 <= next_index < n and next_index not in visited:
                    queue.append(next_index)

        return False