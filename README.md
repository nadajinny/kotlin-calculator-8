# kotlin-calculator-precourse

## main goal : 입력한 문자열에서 숫자를 추출하여 더하는 계산기

### 조건

- 입력값에 따른 조건 : 
  - 빈 문자열 -> 0 반환
  - ",", ":" 일 경우, 숫자 분리
  - "//", "\n" 사이에 있으면 구분자로 처리
  - 잘못된 값(음수, "//\n"이 없는데 특수문자) 입력 시, IllegalArgumentException 발생
- 입출력 값:
    ```
    덧셈할 문자열을 입력해 주세요.
    1,2:3
    결과 : 6
    ```

- 실행 시작점 : Application의 main()

### 1. 입력 처리 기능(readInput)

- 사용자 입력 값은 **camp.nextstep.edu.missionutils.Console**의 **readLine()**을 활용

### 2. 빈 문자열 처리(isEmptyInput)

- 사용자 입력 값이 **빈 문자열**일 경우, 0 반환

### 3. 기본 구분자 처리(splitBasic)

- ","과 ":" 이면 문자열 분리

### 4. 커스텀 구분자 처리(splitCustom)

- "//"과 "\n"을 확인하고 문자열 분리

### 5. 숫자 입력값 검증(isPositiveNumber)

- 양수값인지 확인

### 6. 합산(add)

- 처리 완료한 숫자들을 모두 합산

### 7. 출력(writeOutput)

- 합산을 출력

### 로직

1. 입력을 요구하는 문구 출력 및 입력 (Application.kt)
2. 입력을 받고 나면 연산으로 이동 (Application.kt -> StringCalculator.kt)
3. 빈 문자열 여부 파악
4. 기본 구분자를 기준으로 문자열 분리
5. 분리된 문자열에서 커스텀 구분자 확인 (최종적으로 모두 분리된 상태)
6. 양수값인지 파악
7. 처리 완료한 숫자들을 모두 합산
8. 출력
9. 종료 (StringCalculator.kt -> Application.kt)