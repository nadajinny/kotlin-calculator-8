package calculator

class StringCalculator {
    fun run(input: String): Int {
        if(input.isEmpty()) return 0
        if(input.isBlank()) return 0

        val spliter = mutableListOf(",", ":")
        val numberlist = mutableListOf<Int>()
        var current = StringBuilder()

        fun add(list: List<Int>): Int {
            return list.sum()
        }
    }
}