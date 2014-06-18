# README # (Documentation is a WIP please come back soon)

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

This repo contains the Java component of the afterburner-client. You can run this on either Linux/Windows and it connects to both MSI Afterburner's Remote Server PC application and an Arduino device over serial. 

### How does it work? ###

There are three parts to this project :

1. An Arduino device with a 16x2 display (but not limited to)
2. My afterburner-client which is installed on your Windows PC to be monitored (this project code)
3. MSI Afterburner & MSI Afterburner Remote Server installed on your Windows PC to be monitored

It sends serial messages over a configured comm port and these messages can be used however the ardiuno device likes. The MSI Afterburner Remove Server application is available on the MSI Afterburner website. The application was originally intended for use with it's Android Client but can also be interfaced using this code.

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* How to run tests

### Who do I talk to? ###

* Stephen O'Hair - stephen.ohair@gmail.com