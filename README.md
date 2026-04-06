# 🍃 Spring Framework & MVC 학습 일지

## 🛠️ Tech Stack
* **Language:** Java
* **Framework:** Spring Framework (Legacy), MyBatis
* **Test:** JUnit 4
* **Configuration:** XML, Annotation
* **IDE:** Eclipse / STS 3

---

## 🗓️ 2026-04-02: Spring Core 핵심 (XML 기반 DI & AOP)

### 📝 개요
Spring 프레임워크의 핵심인 **DI(의존성 주입)**와 **AOP(관점 지향 프로그래밍)**의 동작 원리를 학습했습니다. 최신 어노테이션 방식 이전의 기초적인 **XML 기반(Legacy Spring)** 설정 방식을 통해 스프링 컨테이너의 객체(Bean) 관리 메커니즘을 이해하는 데 중점을 두었습니다.

### 📚 주요 학습 내용

#### 1. Spring Bean과 IoC 컨테이너 (`di03.xml`)
* **IoC (제어의 역전):** `new` 키워드를 통한 직접 객체 생성을 지양하고, 스프링 컨테이너에 객체 생성 및 관리를 위임.
* **XML 설정:** `<bean class="..." id="...">` 태그를 사용하여 빈(Bean) 정의 및 선언.
* **흐름:** XML 빈 정의 ➔ 스프링 컨테이너 생성 ➔ 빈 생성 및 반환 (`context.getBean()`).

#### 2. DI (의존성 주입) (`di04.xml`)
* 객체 간 결합도를 낮추기 위한 스프링 컨테이너의 의존성 주입 처리.
* **생성자 주입 (Constructor Injection):** `<constructor-arg ref="id">`를 사용하여 객체 생성 시점에 의존성 주입.
* **수정자 주입 (Setter Injection):** `<property name="..." ref="...">`를 사용하여 `setter` 메서드를 통해 의존성 주입.

#### 3. AOP (관점 지향 프로그래밍) (`memo.xml`)
* 주 업무(Core Concern)와 보조 업무(Cross-cutting Concern, 예: 로그 출력)의 모듈화 분리.
* **주요 구성 요소:**
  * **Target:** 주 업무를 수행하는 객체 (`MemoImpl`)
  * **Aspect:** 보조 업무를 수행하는 객체 (`Logger`)
  * **Pointcut:** 보조 업무가 개입할 타겟 메서드 지정 (AspectJ 표현식 `execution()` 사용)
  * **Weaving:** 주 업무와 보조 업무가 결합되는 시점 정의 (`<aop:after>` 등)

---

## 🗓️ 2026-04-03: Spring MVC 문법 및 Annotation 기반 DI

### 📝 개요
기존 Servlet/JSP 기반의 웹 코드를 Spring MVC의 **어노테이션(Annotation)** 기반 코드로 전환하는 과정을 실습했습니다. 클라이언트 요청의 효율적인 수집, 컨트롤러의 다양한 응답 처리, 그리고 어노테이션을 활용한 계층 간 자동 의존성 주입(DI) 원리를 집중적으로 다루었습니다.

### 📚 주요 학습 내용

#### 1. 어노테이션 기반 DI 및 계층 구조
기존 XML 설정에서 벗어나 Java 클래스에 어노테이션을 부여하여 스프링 빈으로 등록하고 객체를 조립하는 현대적인 방법.
* **역할별 빈(Bean) 등록 (Component Scan):**
  * `@Controller`: 웹 브라우저의 요청 처리 담당
  * `@Service`: 핵심 비즈니스 로직 담당
  * `@Repository`: DB 연결 및 접근(DAO) 담당
* **`@Autowired` 활용:** 객체 내부에서 필요한 외부 객체를 직접 생성하지 않고, 생성자 주입 등을 통해 스프링 컨테이너가 만들어둔 빈을 자동 주입하여 결합도 최소화.
* **데이터 흐름 아키텍처:** 클라이언트 요청 ➔ `Controller` ➔ `Service` ➔ `DAO` 순으로 안전하게 의존성이 연결되는 MVC 표준 계층 구조 구현.

#### 2. 강력한 파라미터 수집 (Parameter Binding)
과거 `request.getParameter()` 반복 사용을 대체하는 스프링의 데이터 자동 수집 및 형변환 기능.
* **`@RequestParam`:** 파라미터를 변수에 1:1 매핑. 자동 형변환 지원 및 `defaultValue` 속성으로 초깃값 설정 가능.
* **DTO 자동 수집 (Command Object):** 매개변수로 DTO 객체를 선언하면, HTML Form의 `name` 속성과 DTO의 필드명을 매칭하여 `Setter`를 통해 데이터를 자동 조립.
* **배열 및 컬렉션 처리:** 동일한 이름의 여러 값(다중 체크박스 등)을 `String[]` 배열이나 `@RequestParam List<String>` 형태로 일괄 수집.
* **`@ModelAttribute`:** 파라미터 수집과 동시에 `Model` 객체에 데이터를 담아 JSP로 전달하는 과정을 단일화.

#### 3. 컨트롤러 반환 타입 (Return Types)
비즈니스 로직 처리 후 화면이나 데이터를 클라이언트에게 반환하는 방식.
* **`String`:** 반환된 문자열을 ViewResolver가 해석하여 해당 JSP 파일로 포워딩.
* **`void`:** 반환값이 없을 경우, 요청된 URL 주소와 동일한 이름의 JSP 파일 자동 호출 (Spring 4.3+).
* **`redirect:` / `forward:`:** 반환 문자열 앞 접두어를 통해 리다이렉트 및 포워딩 명시적 처리.
* **`RedirectAttributes`:** 리다이렉트 시 쿼리스트링 하드코딩을 방지하고 객체를 통해 안전하게 파라미터 전달.
* **`@ResponseBody` (JSON 반환):** ViewResolver를 거치지 않고, 반환하는 DTO 객체를 JSON 형식으로 변환하여 클라이언트에게 직접 응답.

---

## 🗓️ 2026-04-06: MyBatis 연동 및 JUnit 단위 테스트

### 📝 개요
반복적이고 복잡한 순수 JDBC 코드를 혁신적으로 줄여주는 **MyBatis 프레임워크**를 스프링에 연동하는 방법을 학습했습니다. 또한, 서버(Tomcat)를 실행하지 않고도 백엔드 로직과 DB 쿼리를 독립적으로 검증할 수 있는 **JUnit 4** 기반의 단위 테스트(Unit Test) 환경 구축을 실습했습니다.

### 📚 주요 학습 내용

#### 1. MyBatis 프레임워크 연동 및 Mapper XML
Java 코드 내부에 하드코딩되던 SQL을 분리하여 유지보수성을 극대화하는 매핑 기법을 학습했습니다.
* **`SqlSessionTemplate`:** 기존 JDBC의 `Connection`, `PreparedStatement`, `ResultSet` 처리 및 예외 처리 구문을 완전히 대체하는 핵심 Bean 사용.
* **XML Mapper 분리 (`address.xml`):** `<insert>`, `<update>`, `<select>` 태그를 이용하여 쿼리문만 별도의 XML 파일로 분리.
* **데이터 입출력 매핑:**
  * `parameterType`: Java에서 SQL로 데이터(String, HashMap, DTO 등)를 넘겨줄 때의 타입 지정 및 `#{변수명}` 문법 활용.
  * `resultType`: SQL 실행 결과를 반환받을 Java 객체 지정 (MyBatis가 자동으로 DTO에 매핑).
* **Type Alias (`mybatis-config.xml`):** 자주 사용하는 DTO의 긴 패키지 경로를 짧은 별칭(예: `adto`)으로 등록하여 XML 가독성 향상.

#### 2. SQL 실행 결과에 따른 반환 타입 분기 (`MyBatisDao.java`)
쿼리의 성격과 결과셋(ResultSet)의 형태에 따라 알맞은 템플릿 메서드를 선택하는 실습을 진행했습니다.
* **단일 데이터 반환:** `template.selectOne()` (레코드 1개 또는 `count(*)` 같은 원자값)
* **다중 데이터 반환:** `template.selectList()` (여러 레코드를 자동으로 `List<DTO>` 형태로 묶어서 반환)
* **DML 실행:** `template.insert()`, `update()`, `delete()` (실행 결과로 영향을 받은 행의 개수 반환)

#### 3. JUnit 4를 활용한 Spring 테스트 환경 구축
웹 브라우저와 컨트롤러를 거치지 않고 DAO 객체와 DB 연결만 타겟팅하여 테스트하는 기법을 익혔습니다.
* **Spring-JUnit 연동:** `@RunWith(SpringJUnit4ClassRunner.class)`와 `@ContextConfiguration`을 사용하여 테스트 클래스 실행 시 스프링 컨테이너를 구동하도록 설정.
* **단위 테스트 어노테이션:** `@Test`로 개별 테스트 메서드를 지정하고, `@Ignore`로 특정 테스트를 일시적으로 제외.
* **단언문(Assertion) 활용:** * `assertNotNull()`: DataSource나 템플릿 등 의존성이 정상적으로 주입되었는지(null이 아닌지) 검증.
  * `assertEquals(expected, actual)`: DB 쿼리 실행 결과값(예: insert 성공 시 1 반환, 데이터 조회 시 일치 여부)이 예상과 맞는지 검증.