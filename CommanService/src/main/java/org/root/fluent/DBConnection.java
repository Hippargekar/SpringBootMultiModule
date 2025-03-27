package org.root.fluent;

public class DBConnection implements HostStep1, PortStep2, ConnectStep3{
    private String host;
    private Integer port;
    private Boolean sslEnabled;
    private Integer timeout;

    private DBConnection() {
        // private constructor to prevent direct instantiation
    }

    @Override
    public HostStep1 enableSsl(Boolean ssl) {
        this.sslEnabled = ssl;
        return this;
    }

    @Override
    public PortStep2 setHost(String host) {
        this.host = host;
        return this;
    }

    @Override
    public PortStep2 setTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override
    public ConnectStep3 setPort(Integer port) {
        this.port = port;
        return this;
    }

    @Override
    public DBConnection connect() {
        return this;
    }

    public static HostStep1 build() {
        return new DBConnection();
    }
}
