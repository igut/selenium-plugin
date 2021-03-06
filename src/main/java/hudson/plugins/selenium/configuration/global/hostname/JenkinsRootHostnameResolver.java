/**
 *
 */
package hudson.plugins.selenium.configuration.global.hostname;

import hudson.Extension;
import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Richard Lavoie
 *
 */
public class JenkinsRootHostnameResolver extends HostnameResolver {

    /**
     *
     */
    private static final long serialVersionUID = 7865004453289102894L;

	@DataBoundConstructor
	public JenkinsRootHostnameResolver() {
	}


	public String retrieveHost() {
        String rootUrl = Jenkins.getInstance().getRootUrl();
        if (rootUrl == null)
            return "localhost";
        URL url;
        try {
            url = new URL(rootUrl);
        } catch (MalformedURLException e) {
            return null;
        }
        return url.getHost();
    }

    @Extension
    public static final class JenkinsHostnameRetrieverDescriptor extends HostnameResolverDescriptor {

        @Override
        public String getDisplayName() {
            return "Use jenkins URL, as defined in the global configuration";
        }

    }

}
