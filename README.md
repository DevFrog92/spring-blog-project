# Blog project

# Abstract

Spring MVC 에 대한 강의를 수강 후, 배운내용을 체득하기 위한 미니 프로젝트입니다. 
Spring Framework 와 Spring Boot를 사용할 것이고, 프론트는 Thymeleaf를 사용할 것입니다.

# Quickstart guide

## Requirements
- Java 17
- JDK corretto-17
- Spring Boot 3.1.0


## Installation
```shell
$ git clone https://github.com/DevFrog92/spring-blog-project.git
$ cd spring-blog-project
```

# Stack

## Environment
- Intellij Ultimate
- Git
- GitHub

## Development
- Java
- Gradle
- JPA
- H2
- Thymeleaf
- Lombok


# Feature

- 블로그 아티클 관련 기능
    - 블로그 아티클 생성 기능
    - 블로그 아티클 수정 기능
    - 블로그 아티클 조회 기능
    - 글로그 아티클 삭제 기능

- 회원 관련 기능 (TBD)


# Architecture

```
blog
├─ .gitignore
├─ README.md
├─ gradle
└─ src
   ├─ main
   │  ├─ generated
   │  ├─ java
   │  │  └─ com
   │  │     └─ blog
   │  │        ├─ BlogApplication.java
   │  │        ├─ controller
   │  │        │  ├─ BlogApiController.java
   │  │        │  └─ BlogViewController.java
   │  │        ├─ domain
   │  │        │  └─ Article.java
   │  │        ├─ dto
   │  │        │  ├─ AddArticleRequest.java
   │  │        │  ├─ ArticleListViewResponse.java
   │  │        │  ├─ ArticleResponse.java
   │  │        │  ├─ ArticleViewResponse.java
   │  │        │  └─ UpdateArticleRequest.java
   │  │        ├─ repository
   │  │        │  └─ BlogRepository.java
   │  │        └─ service
   │  │           └─ BlogService.java
   │  └─ resources
   │     ├─ application.properties
   │     ├─ data.sql
   │     ├─ static
   │     │  └─ js
   │     │     ├─ article.js
   │     │     ├─ articleCreateForm.js
   │     │     └─ articleUpdateForm.js
   │     └─ templates
   │        ├─ article.html
   │        ├─ articleList.html
   │        ├─ editArticleForm.html
   │        └─ newArticleForm.html
   └─ test
      └─ java
         └─ com
            └─ blog
               ├─ BlogApplicationTests.java
               └─ controller
                  └─ BlogApiControllerTest.java
```
