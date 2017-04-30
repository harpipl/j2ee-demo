FROM jboss/wildfly:latest

RUN /opt/jboss/wildfly/bin/add-user.sh admin qwer1234 --silent

EXPOSE 8080 8787 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "--debug", "8787", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]