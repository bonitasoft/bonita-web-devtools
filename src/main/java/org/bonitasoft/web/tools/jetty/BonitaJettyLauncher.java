/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.web.tools.jetty;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;

import com.google.gwt.core.ext.TreeLogger;

/**
 * Custom jetty launcher for Bonita GWT debug mode
 * 
 * - Enable JNDI ENC
 * - Load jetty.xml
 * 
 * @author Colin PUY
 */
public class BonitaJettyLauncher extends JettyLauncher {

    /** get the JNDI ENC (ie: JNDI URLs starting with java:comp/) configured in Jetty */
    private final static String[] WEBAPPCONTEXT_CONFIGURATION_CLASSES = { "org.eclipse.jetty.webapp.WebInfConfiguration",
            "org.eclipse.jetty.webapp.WebXmlConfiguration", "org.eclipse.jetty.webapp.MetaInfConfiguration", "org.eclipse.jetty.webapp.FragmentConfiguration",
            "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration",
            "org.eclipse.jetty.annotations.AnnotationConfiguration", "org.eclipse.jetty.webapp.JettyWebXmlConfiguration", };

    @Override
    protected WebAppContext createWebAppContext(final TreeLogger logger, final File appRootDir) {
        final WebAppContext webAppContext = super.createWebAppContext(logger, appRootDir);
        webAppContext.setConfigurationClasses(WEBAPPCONTEXT_CONFIGURATION_CLASSES);
        return webAppContext;
    }

    /**
     * Load jetty.xml to configure server
     */
    @Override
    protected void configureServer(final Server server, final File appRootDir) throws Exception {
        final File jettyXml = new File(appRootDir, "WEB-INF/jetty.xml");
        if (jettyXml.exists()) {
            System.out.println("loading " + jettyXml.getAbsolutePath());
            final XmlConfiguration configuration = new XmlConfiguration(jettyXml.toURI().toURL());
            configuration.configure(server);
        }
    }
}
