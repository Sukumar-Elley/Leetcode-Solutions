import scala.collection.mutable

object Solution {
    class TrieNode {
        val children = mutable.Map[Char, TrieNode]()
        var bestIndex: Int = -1
    }

    def stringIndices(
        wordsContainer: Array[String],
        wordsQuery: Array[String]
    ): Array[Int] = {

        val root = new TrieNode

        // Find the shortest word; ties keep the earliest index.
        var shortestIndex = 0

        for (i <- wordsContainer.indices) {
            if (wordsContainer(i).length < wordsContainer(shortestIndex).length) {
                shortestIndex = i
            }
        }

        root.bestIndex = shortestIndex

        // Build a trie using reversed words.
        for (i <- wordsContainer.indices) {
            var node = root
            val word = wordsContainer(i)
            var j = word.length - 1

            while (j >= 0) {
                val ch = word(j)

                if (!node.children.contains(ch)) {
                    node.children(ch) = new TrieNode
                }

                node = node.children(ch)

                if (
                    node.bestIndex == -1 ||
                    word.length < wordsContainer(node.bestIndex).length
                ) {
                    node.bestIndex = i
                }

                j -= 1
            }
        }

        // Process each query.
        wordsQuery.map { query =>
            var node = root
            var answer = shortestIndex
            var j = query.length - 1
            var searching = true

            while (j >= 0 && searching) {
                node.children.get(query(j)) match {
                    case Some(nextNode) =>
                        node = nextNode
                        answer = node.bestIndex
                        j -= 1

                    case None =>
                        searching = false
                }
            }

            answer
        }
    }
}