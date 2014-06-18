# Afterburner-Client 

### What is this repository for? ###
This repo contains the Java component of the afterburner-client. You can run this on either Linux/Windows and it connects to both MSI Afterburner's Remote Server PC application and an Arduino device over serial. 

### What does it do? ###
It sends MSI Afterburner monitoring statistics to an Ardiuno based device over USB serial.


### How does it work? ###
There are three parts to this project :

1. An Arduino device with a 16x2 display (but not limited to)
2. My afterburner-client which is installed on the Windows PC to be monitored (this project code)
3. MSI Afterburner GUI & MSI Afterburner Remote Server installed on the Windows PC to be monitored

It sends serial messages over a configured comms port and these messages can be used however the ardiuno device likes i.e. LCD display, LEDs, speaker alerts, fan power control module, your imagination. The ardiuno coding part is for you to write depending on what stats it receives. I've just used the simple LCD serial example provided by Ardiuno. This was perfect for my device where I just wanted to display any receved serial messages straight to my LCD 16x2 character display.

The MSI Afterburner GUI & Remote Server application are available on the [MSI Afterburner website](http://event.msi.com/vga/afterburner/overview.htm). This code is currently capable of interfacing with MSI Afterburner versions : 

* MSI Afterburner GUI Version 3.0.1
* MSI Afterburner Remote Server v2.0.50727

### How do I get set up? ###

Install JDK 8.
Install maven 3.0
Install the appropriate Arduino IDE and drivers for your device
Install MSI Afterburner & MSI Afterburner Remote Server

* Configuration
* Dependencies

### Configuration ###
Modify the configuration file /src/main/resources/configuration.properties and set the following : 

* **IP address and port** - that the MSI Afterburner Remote Server is listening on. 
* **Password** - I've included the default password that is provided by the Remote Server but you can modify this in the app and adjust it here also.
* **Comms Port** - this is the port that the Arduino devcie is listening on. For windows it would be something like "COMM1" on linux it'd be something like /dev/XYZBLAH.
* **Maxmium characters for a serial message** : by default I've left it at 32 since I used a 16x2 character display. 

### Compiling ###
To compile run : 

```
#!java

mvn clean install
```

This will create an executable JAR which you can copy to the Windows PC running MSI Afterburner & Remote Server.

### Running the Application ###

Start up both the MSI Afterburner & Remote server applications.

Open up a Windows command prompt and run the jar.


```
#!java

java.exe -jar afterburner-client-one-jar.jar
```

Observe the terminal output and ensure that you not only see key/value pair listings of Afterburner stats but that it's able to access and publish to the configured comms port.

### LCD Displays & Serial Messages ###

The MessasgeWriter class is specifically coded to display 4 items on a screen each 8 characters long and padded with whitespace. If you want to display stats to a larger screen 40x4 etc. you'd need to set the max character property in the config file and modify the MessageWriter to display the stats you're interested in and also in the format you'd wish them to be viewed.

The default message the MessageWriter sends will contain stats for :

* FPS
* GPU1
* CPU
* GPU2

It looks like this :


```
#!java

FPS:60  GPU1:88
CPU:99  GPU2:79
```

**Note 1** : If you haven't configured MSI Afterburner to display framerate a TEMP stat will be displayed instead. 

```
#!java

TMP:47  GPU1:88
CPU:99  GPU2:79
```

**Note 2** : If you have a single gfx card then it will display RAM usage instead.

```
#!java

FPS:60  GPU1:88
CPU:99  RAM:2305
```

### TODO ###
* Make the MessageWriter configurable for multiple display sizes
* Use maven-assembly-plugin for jar with dependency usage
* Add configurable log level
* Make conifguration use an external configuration and configure a default config

### Who do I talk to? ###

* Stephen O'Hair - stephen.ohair@gmail.com