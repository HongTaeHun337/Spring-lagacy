# 🍃 Spring Framework & MyBatis 총정리 학습 일지

## 🛠️ Tech Stack
* **Language:** Java, JavaScript (ES6), jQuery
* **Framework:** Spring Framework (Legacy), MyBatis
* **Open API:** Naver Search API, Kakao Map API
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

---

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

---

## 🗓️ 2026-04-09: 비동기 통신 Ajax와 백엔드 연동

### 📝 개요
웹 페이지 전체를 새로고침하지 않고 필요한 데이터만 부분적으로 업데이트하는 **Ajax(Asynchronous JavaScript and XML)** 기술을 학습했습니다. 순수 자바스크립트(`XMLHttpRequest`) 방식부터 jQuery를 활용한 생산성 높은 구현, 그리고 스프링 백엔드에서 JSON 데이터를 응답하는 과정을 실습했습니다.

### 📚 주요 학습 내용

#### 1. Ajax의 핵심 원리와 비동기(Asynchronous) 방식
* **동기(Sync) vs 비동기(Async):** 요청 후 응답이 올 때까지 기다리는 동기 방식과 달리, 비동기 방식은 배경(Background)에서 통신을 진행하여 사용자 경험을 끊김 없이 유지합니다.
* **XMLHttpRequest 객체:** Ajax 통신의 핵심 객체로, `readyState`(통신 상태)와 `status`(HTTP 상태 코드)를 통해 데이터 수신 완료 여부를 판단합니다.

#### 2. 스프링 백엔드 응답 처리 (`@ResponseBody`)
* **데이터 직접 반환:** 컨트롤러 메서드에 `@ResponseBody`를 붙여 뷰(JSP) 경로가 아닌 **실제 데이터(문자열, 객체 등)**를 응답 본문에 직접 담아 반환합니다.
* **JSON 자동 변환:** 리턴 타입을 `List<DTO>`와 같이 객체 형태로 지정하고 `produces` 속성을 설정하면, 스프링(Jackson 라이브러리 연동)이 이를 JSON 형식으로 자동 변환하여 클라이언트에게 전달합니다.
* **한글 깨짐 방지:** 응답 시 `produces = "text/plain;charset=UTF-8"` 설정을 통해 텍스트 데이터의 한글 깨짐을 방지합니다.

#### 3. 실전 Ajax 구현 (jQuery & MyBatis)
* **jQuery `$.ajax()`:** 복잡한 순수 JS 코드를 단순화하여 `type`, `url`, `data`, `success` 등의 속성으로 가독성 있게 비동기 통신을 구현했습니다.
* **아이디 중복 검사:** 사용자가 입력한 ID를 Ajax로 서버에 보내고, `AjaxDao`를 통해 DB 존재 여부를 확인한 뒤 결과를 화면에 즉각 출력하는 실무 로직을 작성했습니다.
<<<<<<< HEAD
* **다중 데이터(JSON) 처리:** 서버에서 반환된 JSON 리스트를 자바스크립트의 `forEach` 문과 Template Literal(백틱)을 사용하여 동적으로 HTML 요소를 생성하고 화면에 렌더링했습니다.


## 🗓️ 2026-04-10: RESTful API 구축, Swagger 명세서 및 심화 Ajax

### 📝 개요
웹 애플리케이션의 클라이언트(View)와 서버(API) 역할을 완벽하게 분리하는 **RESTful 아키텍처**를 실습했습니다. `@RestController`를 도입하여 CRUD API를 설계하고, 협업에 필수적인 **Swagger UI**를 연동하여 API 명세서를 자동화했습니다. 또한 다중 데이터(배열)를 Ajax로 전송하고 처리하는 심화 비동기 로직을 구현했습니다.

### 📚 주요 학습 내용

#### 1. RESTful API 컨트롤러 설계 (`@RestController`)
* **데이터 전용 컨트롤러:** 기존 `@Controller` + `@ResponseBody` 조합을 대체하는 `@RestController`를 사용하여 모든 메서드가 JSON/Text 데이터를 직접 반환하도록 구현.
* **HTTP Method 매핑:** REST 규약에 맞게 CRUD 작업을 명확히 분리.
  * `C` (Create): `@PostMapping` (추가)
  * `R` (Read): `@GetMapping` (조회)
  * `U` (Update): `@PutMapping` (전체 수정)
  * `D` (Delete): `@DeleteMapping` (삭제)
* **경로 변수 (`@PathVariable`):** 쿼리스트링(`?seq=1`) 대신 URL 경로 자체를 데이터로 사용하는 `/address/{seq}` 방식을 적용하여 직관적인 URI 설계.

#### 2. Swagger UI를 이용한 API 문서 자동화
* **Springfox 의존성 추가:** Maven에 `springfox-swagger2` 및 `swagger-ui` 라이브러리를 추가하고, `SwaggerConfig` 클래스를 통해 빈(Bean) 등록.
* **리소스 정적 매핑 해결:** 톰캣 404 에러 방지를 위해 `servlet-context.xml`에 `<mvc:resources mapping="/swagger-ui.html" ... />` 경로를 수동 매핑하는 트러블슈팅 경험.
* **어노테이션 명세:** * `@Api`, `@ApiOperation`: 컨트롤러와 각 API 메서드의 용도 및 설명 작성.
  * `@ApiModel`, `@ApiModelProperty`: 요청/응답에 사용되는 DTO 객체의 스키마와 예시 데이터를 문서화.

#### 3. 심화 Ajax 통신 및 데이터 바인딩
* **JSON 데이터 수신 (`@RequestBody`):** 클라이언트가 Ajax를 통해 `application/json` 포맷으로 보낸 데이터를 자바 객체(`AddressDto`)로 자동 바인딩.
* **다중(배열) 데이터 처리:** 체크박스 등을 통해 선택된 여러 개의 식별자 번호를 `SeqDto` (String[] 배열)로 받아 MyBatis 동적 쿼리로 일괄 삭제(Delete All) 처리.
* **클라이언트/서버 아키텍처 분리:** 화면(JSP)을 띄우는 `ClientController`와 순수하게 데이터(JSON)만 제공하는 `ServerController`를 물리적으로 분리하여 최신 프론트엔드(React, Vue 등)와의 협업 구조 모방 실습.
* **무한 스크롤(더보기) 원리:** `index`(begin, end) 값을 파라미터로 넘겨 페이징된 데이터를 Ajax로 추가 로드(`more`)하는 로직 구현.
=======
* **다중 데이터(JSON) 처리:** 서버에서 반환된 JSON 리스트를 자바스크립트의 `forEach` 문과 Template Literal(백틱)을 사용하여 동적으로 HTML 요소를 생성하고 화면에 렌더링했습니다.
>>>>>>> 676948dee94c7cb4a8ff132b5c435ad77678c6e7
