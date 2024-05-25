package at.fh.sfs.di;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;

@ApplicationScoped
public class LoggerProducer {
    @Produces
    @Dependent
    public org.apache.logging.log4j.Logger produceLogger(InjectionPoint ip) {
        return LogManager.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}
