[english](./README.md)
# freeNAMU

## 소개
freeNAMU는 [나무위키](https://namu.wiki)의 [나무마크](https://namu.wiki/w/나무마크)를 지원하는 대안 위키 엔진입니다. freeNAMU는 Spring Boot를 백엔드 프레임워크로, Vue.js를 프론트엔드 프레임워크로 사용하고, 다양한 RDBMS를 지원하며, docker를 통해 다양한 OS에 쉽게 설치할 수 있습니다.

## 빌드 및 실행
```
docker build --tag freenamu .
docker run -d -p 80 freenamu
```

## 라이센스
freeNAMU는 AGPL 3.0에 따라 라이센스가 부여됩니다. 라이센스 조건은 [LICENSE](./LICENSE)에 자세히 설명되어 있습니다.

## 관련 항목
* [openNAMU](https://github.com/openNAMU/openNAMU) - An alternative wiki engine that supports namuwiki's namumark based on Python.
