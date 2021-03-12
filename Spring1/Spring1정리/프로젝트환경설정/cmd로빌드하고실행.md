# 지금까지 intellij로 빌드했었는데 이번엔 cmd로 빌드

캐쉬움

1. spring프로젝트 있는 경로찾아가
2. ./gradlew build -> 하면 build폴더생성
3. cd ./build/libs
4. java -jar ~ (거기있는 jar파일 실행)
   그러면 빌드되어 실행됨

나중에 서버에서 배포할때 이 jar파일만 java -jar로 실행하면 된다. 완전간단함!
전에는 톰캣 설정하고.. 마이복잡했음
