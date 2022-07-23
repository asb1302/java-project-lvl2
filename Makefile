.DEFAULT_GOAL := build-run

setup:
	gradle wrapper --gradle-version 7.3.3

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./app/build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

update-deps:
	./gradlew useLatestVersions

lint:
	./gradlew checkstyleMain

build-run: build run

run:
	./gradlew run --console=plain
