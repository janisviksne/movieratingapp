# Welcome to the movie rating application!

This is a basic movie rating application where you can add movies, rate and delete them, as well as see the entire movie list (working CRUD).


Used technologies:
  1) Spring Boot
  2) HTML
  3) Thymeleaf
  4) Maven
  5) Jenkins
  6) SonarQube
  7) Mockito (for testing)

Entrypoint for using the website once launched locally:
 http://localhost:8080/movie-ratings/show-all-movies
 
 # Test coverage for MovieController which uses MovieService
 These tests cover the main functionality of the application, initially by reaching the endpoints to validate the availability of the application.
 Further you will find tests which mock the addition of a new movie within the site, as well as giving it a rating.
 
![test_coverage](https://user-images.githubusercontent.com/73493967/165553773-15b796d0-bcb2-44a5-92c3-90780f0ff22c.png)

# Movie rating application views:
1) Show all movies screen

![show_all_movie_screen](https://user-images.githubusercontent.com/73493967/165554068-542bb033-69b2-4d99-8b2f-ccd261f3b8c9.png)

2) Add new movie screen

![add-new-movie](https://user-images.githubusercontent.com/73493967/165554142-f8b81763-3645-43b0-9f66-24423e63569c.png)

3) Delete movie call

![delete-function](https://user-images.githubusercontent.com/73493967/165554188-c75a98a1-01dc-4c28-8898-ca8c12e84c33.png)

4) Rate movie screen

![rate-movie](https://user-images.githubusercontent.com/73493967/165554236-a2182e38-6cb3-46bf-aa88-bc5e806f6269.png)

# Deployment (Docker images, Jenkins & SonarQube)

Jenkins and SonarQube was launched locally with their respective docker images. 

![local_docker_images](https://user-images.githubusercontent.com/73493967/165555034-38d931fc-8646-4b75-b77e-0b1857d1ac6f.png)

For this application I used 2 Jenkins builds. One for test execution, second for SonarQube Scan.

![jenkins](https://user-images.githubusercontent.com/73493967/165555106-1a232d02-b622-4a65-9ae8-0a0b5a86c6a7.png)

And finally the executed SonarQube scan provides such results:
APOLOGIZE ABOUT THE QUALITY, THE SNIPPING TOOL DOES NOT SUPPORT HQ IMAGES WITHIN SONARQUBE. 
![sonar_scan](https://user-images.githubusercontent.com/73493967/165555717-b92bd68b-4c25-435f-adb1-d98c524a39b7.png)
![sonar_scan2](https://user-images.githubusercontent.com/73493967/165555722-233531d6-1db8-4216-95a8-d9f9dcfc9c7c.png)


