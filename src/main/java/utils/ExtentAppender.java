package utils;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "ExtentAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class ExtentAppender extends AbstractAppender {

    protected ExtentAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @PluginFactory
    public static ExtentAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Filter") Filter filter,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) boolean ignoreExceptions) {

        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new ExtentAppender(name, filter, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent event) {
        // This is called whenever a log.info / log.error etc. happens
        if (ReportManager.getTest() != null) {
            String message = new String(getLayout().toByteArray(event));
            switch (event.getLevel().getStandardLevel()) {
                case INFO:
                    ReportManager.getTest().log(Status.INFO, message);
                    break;
                case WARN:
                    ReportManager.getTest().log(Status.WARNING, message);
                    break;
                case ERROR:
                case FATAL:
                    ReportManager.getTest().log(Status.FAIL, message);
                    break;
                default:
                    //ReportManager.getTest().log(Status.DEBUG, message);
                    break;
            }
        }
    }
}