plugins {
    kotlin("jvm") version "1.3.21"
    java
}

repositories {
    jcenter()
}

dependencies {
    compile( kotlin("stdlib") )
    compile("com.google.guava:guava:18.0")
    compile( "org.apache.commons:commons-lang3:3.3.2")
    compile( "com.h2database:h2")
    compile( "org.hibernate.javax.persistence:hibernate-jpa-2.0-api:1.0.1.Final")
    compile( "org.springframework.boot:spring-boot-starter-data-jpa")
    compile( "mysql:mysql-connector-java")
    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-commons
//    compile group: 'org.springframework.data', name: 'spring-data-commons', version: '2.1.9.RELEASE'
    compile( "org.springframework.data:spring-data-commons:2.1.9.RELEASE")
    // https://mvnrepository.com/artifact/org.springframework/spring-context
//    compile group: 'org.springframework', name: 'spring-context', version: '5.1.8.RELEASE'
    compile( "org.springframework.boot:spring-boot-starter-test")
//    compile( "org.mockito:mockito-core:test")
//    compile( "org.springframework:spring-context:5.1.8.RELEASE")
    compile ("com.h2database:h2:1.4.199")
    compile ("com.github.spt-oss:spring-boot-starter-test-plus:0.5.0")
    testCompile("junit:junit:4.+")
}

tasks.test {
    testLogging {
        events("PASSED", "FAILED", "SKIPPED", "STANDARD_ERROR", "STANDARD_OUT")
    }
}