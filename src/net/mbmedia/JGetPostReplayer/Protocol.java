package net.mbmedia.JGetPostReplayer;

public enum Protocol {

    HTTP("http"),
    HTTPS("https");

    private String value;

    Protocol(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
