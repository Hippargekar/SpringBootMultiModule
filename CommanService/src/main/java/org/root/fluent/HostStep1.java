package org.root.fluent;

public interface HostStep1 {
    HostStep1 enableSsl(Boolean ssl); // Optional method
    PortStep2 setHost(String host);
}
