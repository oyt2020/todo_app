# 1. 자바 23 버전이 포함된 안정적인 이미지를 사용합니다.
FROM eclipse-temurin:23-jdk

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 jar 파일을 복사 (경로 주의!)
COPY build/libs/*.jar app.jar

# 4. 앱 실행
ENTRYPOINT ["java", "-jar", "app.jar"]