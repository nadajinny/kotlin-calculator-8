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


                val newDelims = mutableListOf<String>()

                if (i < input.length && input[i] == '[') {
                    // //[...][...] 다중 구분자 일 경우
                    while (i < input.length && input[i] == '[') {
                        val end = input.indexOf(']', i)
                        if (end < 0) throw IllegalArgumentException("구분자 선언 오류: ']' 누락")
                        val d = input.substring(i + 1, end)
                        if (d.isEmpty()) throw IllegalArgumentException("구분자가 비어 있습니다")
                        newDelims.add(d)
                        i = end + 1
                    }
                } else {
                    // //; 단일 구분자 일 경우
                    if (i >= input.length) throw IllegalArgumentException("구분자 선언이 비었습니다")
                    newDelims.add(input[i].toString())
                    i++
                }

                // '\n'로 닫혔는지 확인
                when {
                    i + 1 < input.length && input.startsWith("\\n", i) -> {
                        i += 2
                    }
                    i < input.length && input[i] == '\n' -> {
                        i++
                    }
                    else -> {
                        throw IllegalArgumentException("구분자 선언 뒤에는 개행(\\n)이 필요합니다")
                    }
                }

                // 구분자 추가 및 긴 구분자 우선 매칭
                spliter.addAll(newDelims)
                spliter.sortByDescending { it.length }
                continue
            }

            // 2) 현재 구분자 매칭
            var matched: String? = null
            for (d in spliter) {
                if (
                    d.isNotEmpty() &&
                    i + d.length <= input.length &&
                    input.regionMatches(i, d, 0, d.length)
                ) {
                    matched = d
                    break
                }
            }

            if (matched != null) {
                flushToken()
                i += matched.length
                continue
            }

            // 3) 일반 문자 → 숫자 누적
            current.append(input[i])
            i++
        }

        // 입력 종료 후 마지막 토큰 확정
        flushToken()

        return add(numberlist)
    }

    fun add(list: List<Int>): Int {
        return list.sum()
    }
}