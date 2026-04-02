# 0402

# 🍃 Spring Framework Core 개념 학습 (DI & AOP)

## 📝 프로젝트 개요
이 저장소는 Spring 프레임워크의 핵심인 **DI(의존성 주입)**와 **AOP(관점 지향 프로그래밍)**의 동작 원리를 이해하기 위해 작성된 실습 코드입니다.
최신 어노테이션(Annotation) 방식 이전의 기초적인 **XML 기반(Legacy Spring) 설정 방법**을 직접 다루며, 스프링 컨테이너가 객체(Bean)를 어떻게 생성하고 관리하는지 깊이 있게 학습했습니다.

---

## 📚 주요 학습 내용

### 1. Spring Bean과 IoC 컨테이너 (`di03.xml`)
* 자바에서 직접 `new` 키워드로 객체를 생성하는 대신, 스프링 컨테이너가 객체를 생성하고 관리하도록 위임(IoC, 제어의 역전).
* `<bean class="..." id="...">` 태그를 사용하여 XML 파일에 빈(Bean)을 정의하고 선언하는 방법 학습.
* **객체 생성 흐름:** XML 빈 정의 ➔ 스프링 컨테이너 생성 ➔ 빈(Bean) 생성 및 반환(`context.getBean()`)

### 2. DI (Dependency Injection, 의존성 주입) (`di04.xml`)
* 객체 간의 결합도를 낮추기 위해 스프링 컨테이너를 통해 의존성을 주입받는 방법 테스트.
* **생성자 주입 (Constructor Injection):**
  * `<constructor-arg ref="id">`를 사용하여 객체 생성 시점에 의존성 주입.
* **수정자 주입 (Setter Injection):**
  * `<property name="..." ref="...">`를 사용하여 `setter` 메서드를 통한 의존성 주입. (ex. `setEmployee()`)

### 3. AOP (Aspect-Oriented Programming) (`memo.xml`)
* 주 업무(Core Concern)와 보조 업무(Cross-cutting Concern, 예: 로그 출력)를 분리하여 모듈화.
* **주요 구성 요소:**
  * **Target:** 주 업무 객체 (`MemoImpl`)
  * **Aspect:** 보조 업무 객체 (`Logger`)
  * **Pointcut:** 보조 업무가 개입할 타겟의 특정 메서드를 지정. AspectJ 표현식 사용 (`execution()`)
  * **Weaving:** 주 업무와 보조 업무가 결합되는 시점 정의 (`<aop:after>`)

---

## 🐛 Trouble Shooting (문제 해결 경험)

학습 과정에서 발생한 XML 및 AspectJ 문법 오류를 다음과 같이 디버깅하고 해결했습니다.

**1. Pointcut 클래스명 오타로 인한 빈 생성 에러**
* **증상:** `warning no match for this type name: com.test.java.aop.MemoInpl` 에러 발생.
* **원인 및 해결:** XML 설정 파일 내 AspectJ 표현식에서 클래스명을 `MemoImpl`이 아닌 `MemoInpl`로 오타를 낸 것을 확인 후 수정함. 문자열 기반의 XML 설정이 가진 단점을 체감함.

**2. AspectJ Pointcut 문법(띄어쓰기) 오류**
* **증상:** `Pointcut is not well-formed: expecting 'name pattern'` 에러 발생.
* **원인 및 해결:** `execution(*.addMemo(..))` 처럼 반환타입(`*`)과 클래스/메서드 지정 사이에 공백이 누락됨. `execution(* *.addMemo(..))`로 띄어쓰기를 추가하여 해결.

**3. 다중 Pointcut 지정 시 XML 속성 문법 오류**
* **증상:** `||` (OR) 연산자를 사용하여 여러 메서드에 AOP를 적용하려다 XML 파싱 에러 발생.
* **원인 및 해결:** * ❌ `expression="execution(...)" || "execution(...)"` (잘못된 XML 속성 작성)
  * ✅ `expression="execution(...) || execution(...)"` (하나의 문자열 안에 논리 연산자 포함)
  * XML 태그 속성 문법에 맞게 하나의 쌍따옴표 안에 전체 표현식을 올바르게 묶어 해결함.

---

## 🛠️ Tech Stack
* **Language:** Java
* **Framework:** Spring Framework (Legacy)
* **Configuration:** XML
* **IDE:** Eclipse / STS 3