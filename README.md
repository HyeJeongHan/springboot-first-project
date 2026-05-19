# Spring Boot 도서 대여 프로젝트

Kotlin + Spring Boot로 구현한 도서 대여 관리 REST API 프로젝트입니다.

## 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Kotlin |
| Framework | Spring Boot 3.4.5 |
| Database | MariaDB |
| Cache | Redis |
| Security | Spring Security + JWT |
| ORM | Spring Data JPA / Hibernate |
| API 문서 | Swagger (SpringDoc OpenAPI) |
| 로깅 | Logback |

## 주요 기능

### 도서 (Book)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/books` | 도서 목록 조회 (페이징, 제목/저자 검색) |
| GET | `/books/{id}` | 도서 단건 조회 |
| POST | `/books` | 도서 등록 |
| PUT | `/books/{id}` | 도서 수정 |
| DELETE | `/books/{id}` | 도서 삭제 |

### 대여 (Rental)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/rental` | 대여 목록 조회 (페이징) |
| POST | `/rental` | 도서 대여 |
| PATCH | `/rental/{rentId}/return` | 도서 반납 |

### 인증 (Auth)
| Method | URL | 설명 |
|--------|-----|------|
| POST | `/api/authenticate` | 로그인 및 JWT 토큰 발급 |

## 구현 내용

### 페이징 & 검색
- `Pageable`을 이용한 페이징 처리
- 제목/저자 부분 일치 검색 (`ContainingIgnoreCase`)

### Redis 캐시
- 도서 목록 조회 결과 캐싱 (`@Cacheable`)
- 도서 수정/삭제 시 캐시 전체 무효화 (`@CacheEvict(allEntries = true)`)
- TTL 10초 설정

### N+1 문제 해결
- 대여 목록 조회 시 `@EntityGraph`로 `book`, `member` 즉시 로딩

### JWT 인증
- `JwtTokenProvider` — 토큰 생성/검증
- `JwtAuthenticationFilter` — 요청마다 토큰 검증
- `JwtAuthenticationEntryPoint` — 인증 실패 시 401 응답

### 예외 처리
- `GlobalExceptionHandler`로 통합 예외 처리
- `NotFoundException`, `AlreadyExistsException`, `InvalidRequestException` 커스텀 예외

### 유효성 검사
- `@Valid` + Bean Validation으로 요청 데이터 검증

## 로컬 실행 방법

### 사전 준비
- MariaDB 실행 및 데이터베이스 생성
- Redis 실행 (`localhost:6379`)

### 환경변수 설정

`src/main/resources/application-local.yml` 파일을 생성합니다.

```yaml
spring:
  datasource:
    url: jdbc:mariadb://localhost:3306/{데이터베이스명}
    username: {유저명}
    password: {비밀번호}
```

### 실행

IntelliJ `Active profiles`에 `local` 설정 후 실행하거나, 터미널에서:

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

### API 문서
실행 후 http://localhost:8080/swagger-ui.html 에서 확인
