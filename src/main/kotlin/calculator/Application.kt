package calculator

import camp.nextstep.edu.missionutils.Console

fun main() {
    //입력
    println("덧셈할 문자열을 입력해주세요.")
    val input = Console.readLine()
    val output = StringCalculator().run(input)
    print("결과 : $output")
}
