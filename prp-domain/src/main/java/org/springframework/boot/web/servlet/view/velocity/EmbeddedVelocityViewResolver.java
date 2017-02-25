package org.springframework.boot.web.servlet.view.velocity;

import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 * Extended version of {@link VelocityViewResolver} that uses
 * {@link EmbeddedVelocityToolboxView} when the {@link #setToolboxConfigLocation(String)
 * toolboxConfigLocation} is set.
 *
 * @author Phillip Webb
 * @since 1.2.5
 */
public class EmbeddedVelocityViewResolver extends VelocityViewResolver {

    private String toolboxConfigLocation;

    @Override
    protected void initApplicationContext() {
        if (this.toolboxConfigLocation != null) {
            if (VelocityView.class.equals(getViewClass())) {
                this.logger.info("Using EmbeddedVelocityToolboxView instead of "
                        + "default VelocityView due to specified toolboxConfigLocation");
                setViewClass(EmbeddedVelocityToolboxView.class);
            }
        }
        super.initApplicationContext();
    }

    @Override
    public void setToolboxConfigLocation(String toolboxConfigLocation) {
        super.setToolboxConfigLocation(toolboxConfigLocation);
        this.toolboxConfigLocation = toolboxConfigLocation;
    }

}