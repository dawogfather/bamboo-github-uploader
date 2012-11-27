package au.com.atebyte.bamboo.plugins.tasks;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.opensymphony.xwork.TextProvider;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Configurator class for the Github Uploader Task.
 *
 * @author Gavin Bunney
 */
public class GithubUploaderTaskConfigurator extends AbstractTaskConfigurator
{
    private TextProvider textProvider;

    @NotNull
    @Override
    public Map<String, String> generateTaskConfigMap(@NotNull final ActionParametersMap params, @Nullable final TaskDefinition previousTaskDefinition)
    {
        return super.generateTaskConfigMap(params, previousTaskDefinition);
    }

    @Override
    public void populateContextForCreate(@NotNull final Map<String, Object> context)
    {
        super.populateContextForCreate(context);
        context.put("githubusername", "example: dawogfather");
        context.put("githubpassword", "example: bob123");
        context.put("githubrepo", "example: bamboo-github-uploader");
        context.put("artifactname", "a");
        context.put("filename", "johnny.jpg");
    }

    @Override
    public void populateContextForEdit(@NotNull final Map<String, Object> context, @NotNull final TaskDefinition taskDefinition)
    {
        super.populateContextForEdit(context, taskDefinition);

        context.put("githubusername", taskDefinition.getConfiguration().get("githubusername"));
        context.put("githubpassword", taskDefinition.getConfiguration().get("githubpassword"));
        context.put("githubrepo", taskDefinition.getConfiguration().get("githubrepo"));
        context.put("artifactname", taskDefinition.getConfiguration().get("artifactname"));
        context.put("filename", taskDefinition.getConfiguration().get("filename"));
    }

    @Override
    public void populateContextForView(@NotNull final Map<String, Object> context, @NotNull final TaskDefinition taskDefinition)
    {
        super.populateContextForView(context, taskDefinition);
        context.put("githubusername", taskDefinition.getConfiguration().get("githubusername"));
        context.put("githubpassword", taskDefinition.getConfiguration().get("githubpassword"));
        context.put("githubrepo", taskDefinition.getConfiguration().get("githubrepo"));
        context.put("artifactname", taskDefinition.getConfiguration().get("artifactname"));
        context.put("filename", taskDefinition.getConfiguration().get("filename"));
    }

    @Override
    public void validate(@NotNull final ActionParametersMap params, @NotNull final ErrorCollection errorCollection)
    {
        super.validate(params, errorCollection);

        final String username = params.getString("githubusername");
        if (StringUtils.isEmpty(username))
        {
            errorCollection.addError("githubusername", textProvider.getText("au.com.atebyte.bamboo.plugins.github.username.invalid"));
        }
    }

    public void setTextProvider(final TextProvider textProvider)
    {
        this.textProvider = textProvider;
    }
}
