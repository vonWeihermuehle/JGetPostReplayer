# JGetPostReplayer

With this tool i wanted to recreate the Users Requests to my Tomcat-Server, like a "Replay-Button".


##Usage:

```
JGetPostReplayer j = new JGetPostReplayer("<path to log files>", //folder with 1 or more log-files
				500, //sleep time between requests in millis
				500, //timeout of the requests in millies
				Protocol.HTTPS, //protocoll from a enum
				"<hostname>", //hostname of the own tomcat server
				<port>); //port

    	j.start();
      
```

the LogFiles i had for input are in the following structure.

```
0.0.0.0 - - [14/Feb/2021:00:45:55 +0200] "GET /images HTTP/1.1" 200 256820
0.0.0.0 - - [14/Feb/2021:00:52:29 +0200] "GET /robots.txt HTTP/1.1" 404 761
0.0.0.0 - - [14/Feb/2021:00:52:30 +0200] "GET /shop HTTP/1.1" 500 1177
0.0.0.0 - - [14/Feb/2021:01:05:07 +0200] "GET /robots.txt HTTP/1.1" 404 772
```
