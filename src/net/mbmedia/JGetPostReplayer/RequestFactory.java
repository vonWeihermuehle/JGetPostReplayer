package net.mbmedia.JGetPostReplayer;

public class RequestFactory {

    private String hostname;
    private int port;
    private Protocol protocol;

    public RequestFactory(Protocol protocol, String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.protocol = protocol;
    }

    public Request generateRequest(String method, String path){
        return new Request.BUILDER()
                .withHostname(hostname)
                .withPort(port)
                .withMethod(method)
                .withPath(path)
                .withProtocol(protocol)
                .build();
    }
}
