[한국어](./README_ko.md)
# freeNAMU

## Introduction
freeNAMU is an alternative wiki engine that supports [namuwiki](https://namu.wiki)'s [namumark](https://namu.wiki/w/나무마크). freeNAMU uses Spring Boot as a backend framework, Vue.js as a frontend framework, supports various RDBMSs, and can be easily installed on various OSs through docker.

## Build and Run
Execute the following command to download the source code, build and run the docker image.
```
git clone https://github.com/freeNAMU/freeNAMU.git
cd freeNAMU
docker build --tag freenamu .
docker run -d -p 80 freenamu
```
Alternatively, you can download the pre-built image from dockerhub and run it with the following command.
```
docker run -d -p 80 freenamu/freenamu
```

## License
freeNAMU is licensed under the AGPL 3.0 license. The terms of the license are detailed in [LICENSE](./LICENSE).

## See also
* [openNAMU](https://github.com/openNAMU/openNAMU) - An alternative wiki engine that supports namuwiki's namumark based on Python.
