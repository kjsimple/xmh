package com.gydoc.xmh.datasource;

/**
 *
 */
public interface DataServer {

    void start() throws Exception;
    void stop() throws Exception;
    boolean isRunning();
}
