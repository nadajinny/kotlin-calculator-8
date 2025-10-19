package calculator

class StringCalculator {
    fun run(input: String): Int {
        // 빈/공백 문자열 처리
        if (input.isEmpty()) return 0
        if (input.isBlank()) return 0

        // 기본 구분자(기본: , :)
        val spliter = mutableListOf(",", ":")
        // 더해야할 숫자들의 리스트
        val numberlist = mutableListOf<Int>()
        val current = StringBuilder()

        // 지금까지 누적된 숫자 토큰을 확정해서 넣어둔다.
        fun flushToken() {
            if (current.isEmpty()) return
            val token = current.toString().trim()
            if (token.isNotEmpty()) {
                val n = token.toIntOrNull()
                    ?: throw IllegalArgumentException("잘못된 입력: '$token'")
                if (n < 0) throw IllegalArgumentException("음수는 입력할 수 없습니다: $n")
                numberlist.add(n)
            }
            current.setLength(0)
        }

        var i = 0
        while (i < input.length) {
            // 1) 어디서든 // 나오면: 구분자 선언할 수 있도록(다중/가변 길이 지원)
            if (i + 1 < input.length && input[i] == '/' && input[i + 1] == '/') {
                flushToken() // 선언 전에 숫자 토큰 경계 확정
                i += 2

        }

        // 입력 종료 후 남은 토큰 확정
        flushToken()

        return add(numberlist)
    }

    fun add(list: List<Int>): Int {
        return list.sum()
    }
}
