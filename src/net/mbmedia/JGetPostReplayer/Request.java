package net.mbmedia.JGetPostReplayer;

public class Request{

    private String Method;
    private String path;
    private String hostname;
    private int port;
    private Protocol protocol;

    public Request(Protocol protocol, String method, String path, String hostname, int port) {
        this.Method = method;
        this.path = path;
        this.hostname = hostname;
        this.port = port;
        this.protocol = protocol;
    }

    public static class BUILDER{
        private String Method;
        private String path;
        private String hostname;
        private int port;
        private Protocol protocol;

        public BUILDER(){

        }

        public BUILDER withMethod(String method){
            this.Method = method;
            return this;
        }

        public BUILDER withPath(String path){
            this.path = path;
            return this;
        }

        public BUILDER withHostname(String hostname){
            this.hostname = hostname;
            return this;
        }

        public BUILDER withPort(int port){
            this.port = port;
            return this;
        }

        public BUILDER withProtocol(Protocol protocol){
            this.protocol = protocol;
            return this;
        }

        public Request build(){
            return new Request(this.protocol, this.Method, this.path, this.hostname, this.port);
        }
    }

    public String getMethod() {
        return Method;
    }

    public String getFullUrl(){
        return protocol.getValue().toLowerCase() + "://" + hostname + ":" + port + path;
    }

    public boolean isHTTPS(){
        if(protocol == Protocol.HTTPS){
            return true;
        }

        return false;
    }
}
