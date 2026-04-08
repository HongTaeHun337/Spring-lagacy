# 🍃 Spring Framework & MyBatis 총정리 학습 일지

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

#### 1. Spring Bean과 IoC 컨테이너
* **IoC (제어의 역전):** `new` 키워드를 통한 직접 객체 생성을 지양하고, 스프링 컨테이너에 객체 생성 및 관리를 위임.
* **XML 설정:** `<bean class="..." id="...">` 태그를 사용하여 빈(Bean) 정의 및 선언.
* **흐름:** XML 빈 정의 ➔ 스프링 컨테이너 생성 ➔ 빈 생성 및 반환 (`context.getBean()`).

#### 2. DI (의존성 주입)
* 객체 간 결합도를 낮추기 위한 스프링 컨테이너의 의존성 주입 처리.
* **생성자 주입 (Constructor Injection):** `<constructor-arg ref="id">`를 사용하여 객체 생성 시점에 의존성 주입.
* **수정자 주입 (Setter Injection):** `<property name="..." ref="...">`를 사용하여 `setter` 메서드를 통해 의존성 주입.

#### 3. AOP (관점 지향 프로그래밍)
* 주 업무(Core Concern)와 보조 업무(Cross-cutting Concern, 예: 로그 출력)의 모듈화 분리.
* **주요 구성 요소:** Target, Aspect, Pointcut, Weaving.

---

## 🗓️ 2026-04-03: Spring MVC 문법 및 Annotation 기반 DI

### 📝 개요
기존 Servlet/JSP 기반의 웹 코드를 Spring MVC의 **어노테이션(Annotation)** 기반 코드로 전환하는 과정을 실습했습니다. 

### 📚 주요 학습 내용

#### 1. 어노테이션 기반 DI 및 계층 구조
* **역할별 빈(Bean) 등록:** `@Controller`, `@Service`, `@Repository` 어노테이션을 사용하여 역할을 부여하고 스프링 빈으로 등록했습니다.
* **`@Autowired` 활용:** 생성자 주입 등을 통해 스프링 컨테이너가 만들어둔 빈을 자동 주입하여 결합도를 최소화했습니다.

#### 2. 강력한 파라미터 수집 (Parameter Binding)
* **`@RequestParam`:** 파라미터를 변수에 1:1 매핑하며, `defaultValue` 속성으로 초깃값 설정이 가능합니다.
* **배열 및 컬렉션 처리:** 다중 체크박스 등의 값을 배열이나 리스트 형태로 일괄 수집합니다.
* **`@ModelAttribute`:** 파라미터 수집과 동시에 `Model` 객체에 데이터를 담아 JSP로 전달합니다.

#### 3. 컨트롤러 반환 타입 (Return Types)
* **`String` / `void`:** 반환된 문자열로 JSP를 포워딩하거나, 반환값이 없을 경우 URL 주소와 동일한 JSP를 호출합니다.
* **`redirect:` / `forward:`:** 명시적인 리다이렉트 및 포워딩 처리를 지원합니다.
* **`@ResponseBody`:** ViewResolver를 거치지 않고 객체를 JSON 형식으로 직접 응답합니다.

---

## 🗓️ 2026-04-06: MyBatis 연동 및 JUnit 단위 테스트

### 📝 개요
**MyBatis 프레임워크**를 연동하여 JDBC 코드를 간소화하고, **JUnit 4**를 활용해 서버 실행 없이 DB 로직을 검증하는 환경을 구축했습니다.

### 📚 주요 학습 내용

#### 1. MyBatis 프레임워크 연동 및 Mapper XML
* **`SqlSessionTemplate`:** JDBC의 복잡한 처리 과정을 대체하는 핵심 Bean을 사용했습니다.
* **XML Mapper 분리:** `<insert>`, `<update>`, `<select>` 태그를 이용해 SQL 쿼리문을 XML 파일로 분리했습니다.
* **데이터 매핑 및 별칭:** `parameterType`과 `resultType`으로 데이터를 매핑하고, `mybatis-config.xml`에서 `typeAlias`를 지정해 가독성을 높였습니다.

#### 2. SQL 실행 결과에 따른 템플릿 메서드
* `template.selectOne()` (단일 데이터), `template.selectList()` (다중 데이터), `template.insert/update/delete()` 등 목적에 맞는 메서드를 사용했습니다.

#### 3. JUnit 4를 활용한 Spring 테스트
* `@RunWith`와 `@ContextConfiguration`을 사용해 스프링 컨테이너를 구동하고, `assertNotNull()`, `assertEquals()` 등의 단언문으로 단위 테스트를 수행했습니다.

---

## 🗓️ 2026-04-07: 파일 업로드 및 MyBatis 고급 매핑 (동적 SQL & JOIN)

### 📝 개요
스프링 기반의 파일 업로드/다운로드 로직을 구현하고, MyBatis의 동적 쿼리 생성 기법과 1:1, 1:N 조인(JOIN) 데이터를 자바 객체에 매핑하는 고급 기술을 학습했습니다.

### 📚 주요 학습 내용

#### 1. 첨부파일 업로드 및 다운로드
* **단일 파일 업로드:** 폼 속성을 `enctype="multipart/form-data"`로 지정하고, 컨트롤러에서 `MultipartFile`로 수신해 `transferTo()` 메서드로 지정된 경로에 저장했습니다.
* **고유 파일명 생성:** `UUID.randomUUID()` 또는 `System.nanoTime()`을 활용해 파일명 중복 덮어쓰기를 방지했습니다.
* **파일 다운로드:** `ResponseEntity<Resource>`를 사용하여 클라이언트 브라우저(Trident, Edge 등) 환경에 맞춰 한글 파일명 인코딩을 처리하고 파일을 다운로드하도록 구현했습니다.

#### 2. MyBatis 동적 SQL (Dynamic SQL)
* **조건문 처리:** `<choose>`, `<when>`, `<otherwise>` 태그를 통해 파라미터 값(예: 성별)에 따라 다른 쿼리가 실행되도록 분기했습니다.
* **`<where>` 태그 활용:** 다중 검색 조건 조합 시 맨 앞의 불필요한 `AND` 키워드를 자동으로 제거해 문법 오류를 방지했습니다.
* **`<foreach>` 태그 활용:** 자바 컬렉션(List 등) 데이터를 순회하며 쉼표(`,`) 구분자와 함께 `IN` 연산자 구문을 동적으로 생성했습니다.
* **자동 키 생성 (`selectKey`):** 데이터 `INSERT` 시 시퀀스의 최댓값을 먼저 조회하여, 부모-자식 테이블 간 무결성을 유지하며 데이터를 추가했습니다.

#### 3. MyBatis 고급 조인(JOIN) 매핑 (`ResultMap`)
* **1:1 관계 매핑:** `tblAddress`와 `tblPoint` 조인 결과를 매핑할 때, `<resultMap>` 내부에 `<association>` 태그를 사용하여 메인 DTO 안에 서브 DTO(`PointDto`)를 포함시켰습니다.
* **1:N 관계 매핑:** 한 명의 직원이 참여한 여러 프로젝트 목록을 담기 위해 `InsaDto` 내부에 선언된 `List<ProjectDto>`를 `<collection>` 태그와 연결했습니다.
* **컬럼 별칭(Alias) 해결:** 조인 시 발생하는 컬럼명 중복 문제를 SQL의 `AS` 구문으로 구분하고, 이를 `resultMap`의 `column` 속성과 일치시켜 정확히 매핑했습니다.


## 🗓️ 2026-04-08: 고급 파일 처리 및 Open API 연동 (Naver, Kakao)

### 📝 개요
다중 파일 업로드 및 Drag & Drop 기반의 파일 드롭 UI를 구현했습니다. 또한 Naver 도서 검색 API로 JSON 데이터를 파싱하고, Kakao Map API를 연동하여 지도 제어 및 마커 데이터를 DB에 저장/불러오는 위치 기반 서비스를 실습했습니다.

### 📚 주요 학습 내용

#### 1. 고급 파일 업로드 (File Drop & 다중 파일)
* **다중 파일 처리:** `MultipartFile[]` 배열을 통해 여러 개의 파일을 동시에 수신하고 저장하는 로직 구현.
* **Drag & Drop UI:** jQuery의 `dragenter`, `dragover`, `drop` 이벤트를 제어하여 브라우저 기본 동작(`e.preventDefault()`)을 막고, 드래그한 파일을 `dataTransfer.files`로 가로채어 업로드하는 UI 구현.
* **파일명 중복 방지:** `UUID.randomUUID()`와 `System.nanoTime()`을 활용하여 서버 내 첨부파일 덮어쓰기를 방지하는 절대 고유 파일명 생성 기법 적용.
* **안전한 다운로드:** 브라우저(Trident, Edge 등) 별로 `User-Agent` 헤더를 분석하여 한글 파일명 깨짐 현상을 방지하는 `ResponseEntity` 기반 다운로드 구현.

#### 2. Naver Open API 연동 및 JSON 파싱
* **REST API 호출:** `HttpURLConnection`을 이용해 네이버 개발자 센터에서 발급받은 Client ID와 Secret을 HTTP 헤더에 담아 GET 요청 전송.
* **JSON 파싱 (`json-simple`):** 응답받은 JSON 문자열을 `JSONParser`를 이용해 `JSONObject`와 `JSONArray`로 구문 분석하고, 이를 반복문을 통해 자바 `BookDto` 객체 리스트로 매핑.
* **페이징(Paging) 처리:** 검색 결과의 `start` 파라미터를 자바스크립트로 동적 계산(이전/다음 버튼)하여 서버로 다시 전송(`submit()`)하는 도서 검색 페이징 로직 구현.

#### 3. Kakao Map API 제어 및 DB 연동
* **지도 기본 제어:** 발급받은 App Key를 이용해 지도를 렌더링하고, `kakao.maps.LatLng` 좌표계를 이용해 중심점 이동(`panTo`), 확대/축소(`setLevel`) 등 컨트롤 구현.
* **마커(Marker) 및 이벤트:** 지도를 `click` 했을 때 사용자 정의 이미지(`MarkerImage`)를 씌운 마커와 `InfoWindow`(말풍선)를 동적으로 생성하는 이벤트 리스너 실습.
* **마커 좌표 DB 연동 (Save & Load):**
  * **저장:** 지도 클릭 시 생성된 위도(`lat`), 경도(`lng`)를 히든 폼에 담아 POST 전송 후 오라클 DB(`tblMarker`)에 `INSERT`.
  * **조회:** DB에 저장된 모든 좌표를 `MapDto` 리스트로 불러와 `forEach` 문으로 지도 위에 다중 마커로 렌더링.
* **보이는 영역 동적 계산:** `dragend`, `zoom_changed` 이벤트 발생 시 `map.getBounds()`의 남서/북동 좌표를 추출하여, 현재 모니터 화면(지도 영역) 안에 들어온 마커의 개수만 실시간으로 필터링 및 카운트하는 알고리즘 구현.