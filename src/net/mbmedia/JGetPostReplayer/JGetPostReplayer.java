package net.mbmedia.JGetPostReplayer;

import java.io.IOException;
import java.util.ArrayList;

public class JGetPostReplayer {

    private String pathToFolder;
    private long SleepTimeBetweenRequests; //milliseconds
    private Protocol protocol;
    private String hostname;
    private int port;
    private int Timeout;

    public JGetPostReplayer(String pathToFolder, long sleepTimeBetweenRequests, int Timeout, Protocol protocol, String hostname, int port) {
        this.pathToFolder = pathToFolder;
        SleepTimeBetweenRequests = sleepTimeBetweenRequests;
        this.protocol = protocol;
        this.hostname = hostname;
        this.port = port;
        this.Timeout = Timeout;
    }

    public void start(){
        FileHelper fileHelper = new FileHelper(pathToFolder);
        fileHelper.read();
        final ArrayList<Request> requests = fileHelper.generateRequests(protocol, hostname, port);

        Connector connector = new Connector();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(Request r : requests){

                    try {
                        Thread.sleep(SleepTimeBetweenRequests);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        connector.sendRequest(r, Timeout);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }
}
