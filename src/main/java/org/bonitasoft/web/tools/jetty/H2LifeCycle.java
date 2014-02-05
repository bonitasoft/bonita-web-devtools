package org.bonitasoft.web.tools.jetty;

import java.util.ArrayList;
import java.util.List;

import org.h2.tools.Server;
import org.mortbay.component.AbstractLifeCycle;

/**
 * H2 jetty lifecycle to start/stop h2 database
 * 
 * @author arezi
 * https://github.com/arezi/jetty-h2/tree/master/com.arezi.jetty.h2
 */
public class H2LifeCycle extends AbstractLifeCycle {

    private Server h2;

    private String baseDir;

    private boolean ifExists;

    private boolean tcpAllowOthers;

    private Integer tcpPort;

    private boolean web;

    private Integer webPort;

    public H2LifeCycle() {
    }

    @Override
    protected void doStart() throws Exception {
        if (h2 != null) {
            return;
        }

        List<String> lstArgs = new ArrayList<String>();
        lstArgs.add("-tcp");

        if (tcpPort != null) {
            lstArgs.add("-tcpPort");
            lstArgs.add(tcpPort.toString());
        }

        if (tcpAllowOthers) {
            lstArgs.add("-tcpAllowOthers");
        }

        if (baseDir != null) {
            lstArgs.add("-baseDir");
            lstArgs.add(baseDir);
        }

        if (ifExists) {
            lstArgs.add("-ifExists");
        }

        if (web) {
            lstArgs.add("-web");
            if (webPort != null) {
                lstArgs.add("-webPort");
                lstArgs.add(webPort.toString());
            }
        }

        System.out.println("Starting H2 database.");

        h2 = new Server();

        h2.runTool(lstArgs.toArray(new String[lstArgs.size()]));

        System.out.println("H2 database started.");

    }

    @Override
    protected void doStop() throws Exception {
        System.out.println("Stopping H2 database.");
        h2.shutdown();
        System.out.println("H2 database stopped.");
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public boolean isIfExists() {
        return ifExists;
    }

    public void setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    public boolean isTcpAllowOthers() {
        return tcpAllowOthers;
    }

    public void setTcpAllowOthers(boolean tcpAllowOthers) {
        this.tcpAllowOthers = tcpAllowOthers;
    }

    public Integer getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(Integer tcpPort) {
        this.tcpPort = tcpPort;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public Integer getWebPort() {
        return webPort;
    }

    public void setWebPort(Integer webPort) {
        this.webPort = webPort;
    }

}
