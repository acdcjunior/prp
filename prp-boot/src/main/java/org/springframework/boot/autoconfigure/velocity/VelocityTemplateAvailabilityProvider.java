package org.springframework.boot.autoconfigure.velocity;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

/**
 * {@link TemplateAvailabilityProvider} that provides availability information for
 * Velocity view templates.
 *
 * @author Andy Wilkinson
 * @since 1.1.0
 */
public class VelocityTemplateAvailabilityProvider implements TemplateAvailabilityProvider {

    @Override
    public boolean isTemplateAvailable(String view, Environment environment,
                                       ClassLoader classLoader, ResourceLoader resourceLoader) {
        if (ClassUtils.isPresent("org.apache.velocity.app.VelocityEngine", classLoader)) {
            PropertyResolver resolver = new RelaxedPropertyResolver(environment,
                    "spring.velocity.");
            String loaderPath = resolver.getProperty("resource-loader-path",
                    VelocityProperties.DEFAULT_RESOURCE_LOADER_PATH);
            String prefix = resolver.getProperty("prefix",
                    VelocityProperties.DEFAULT_PREFIX);
            String suffix = resolver.getProperty("suffix",
                    VelocityProperties.DEFAULT_SUFFIX);
            return resourceLoader.getResource(loaderPath + prefix + view + suffix)
                    .exists();
        }
        return false;
    }

}