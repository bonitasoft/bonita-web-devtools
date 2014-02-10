bonita-web-devtools
===================

Internal development tools :

* **JDTCompiler** : specific compiler to compile gwt sources with target level to 1.6
* **BonitaJettyLauncher** : specific gwt app jetty launcher that enable JNDI ENC and load jetty.xml
* **H2LifeCycle** : jetty lifecycle that start/stop H2 database on jetty startup/shutdown
* **FullRewriteRegexRule** : jetty rewrite rule, same as RewriteRegexRule but takin query string in account in regexp rule
