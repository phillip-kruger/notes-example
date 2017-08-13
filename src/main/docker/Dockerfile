FROM airhacks/wildfly
MAINTAINER Phillip Kruger, phillip-kruger.com
ENV DEPLOYMENT_DIR ${WILDFLY_HOME}/standalone/deployments/
RUN rm ${WILDFLY_HOME}/bin/standalone.conf
ADD standalone.conf ${WILDFLY_HOME}/bin/
ADD @project.build.finalName@.@project.packaging@ ${DEPLOYMENT_DIR}