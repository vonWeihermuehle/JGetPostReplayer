package net.mbmedia.JGetPostReplayer;

import java.io.*;
import java.util.ArrayList;

public class FileHelper {

    private String pathToFolder;

    private ArrayList<String> paths = new ArrayList<>();

    public FileHelper(String pathToFolder){
        this.pathToFolder = pathToFolder;
    }

    public void read(){
        File folder = new File(pathToFolder);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                paths.add(file.getAbsolutePath());
            }
        }
    }

    public ArrayList<Request> generateRequests(Protocol protocol, String hostname, int port){
        ArrayList<Request> requests = new ArrayList<>();
        RequestFactory rf = new RequestFactory(protocol, hostname, port);

        for(String path : paths){
            requests.addAll(generateRequestForFile(rf, path));
        }

        return requests;
    }

    private ArrayList<Request> generateRequestForFile(RequestFactory rf, String path){
        ArrayList<Request> requests = new ArrayList<>();
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(path));
            String line = null;
            while((line = br.readLine()) != null){
                requests.add(getRequestFromLine(rf, line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requests;
    }

    private Request getRequestFromLine(RequestFactory rf, String line){
        if(!line.contains("POST") && !line.contains("GET")){
            return null;
        }

        String[] columns = line.split("\"");
        String[] loggedRequest = columns[1].split(" ");

        return rf.generateRequest(loggedRequest[0], loggedRequest[1]);
    }


}
