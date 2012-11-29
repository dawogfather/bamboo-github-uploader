package au.com.atebyte.bamboo.plugins.tasks;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.plan.Plan;
import com.atlassian.bamboo.plan.PlanHelper;
import com.atlassian.bamboo.repository.RepositoryDefinition;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.google.common.collect.Maps;
import com.opensymphony.xwork.TextProvider;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Configurator class for the Github Uploader Task.
 *
 * @author Gavin Bunney
 */
public class GithubUploaderTaskConfigurator extends AbstractTaskConfigurator
{
    public static final String GITHUB_USERNAME = "githubusername";
    public static final String GITHUB_PASSWORD = "githubpassword";
    public static final String GITHUB_REPO     = "githubrepo";
    public static final String ARTIFACT_NAME   = "artifactname";
    public static final String FILE_NAME       = "filename";
    public static final String ARTIFACT_LIST   = "artifactslist";
    private TextProvider textProvider;

    @NotNull
    @Override
    public Map<String, String> generateTaskConfigMap(@NotNull final ActionParametersMap params, @Nullable final TaskDefinition previousTaskDefinition)
    {
        final Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);

        config.put(GITHUB_USERNAME, params.getString(GITHUB_USERNAME));
        config.put(GITHUB_PASSWORD, params.getString(GITHUB_PASSWORD));
        config.put(GITHUB_REPO,     params.getString(GITHUB_REPO));
        config.put(ARTIFACT_NAME,   params.getString(ARTIFACT_NAME));
        config.put(FILE_NAME,       params.getString(FILE_NAME));
        config.put(ARTIFACT_LIST,   params.getString(ARTIFACT_LIST));

        return config;
    }

    @Override
    public void populateContextForCreate(@NotNull final Map<String, Object> context)
    {
        super.populateContextForCreate(context);
        context.put(GITHUB_USERNAME,   "example: dawogfather");
        context.put(GITHUB_PASSWORD,   "example: bob123");
        context.put(GITHUB_REPO,       "example: bamboo-github-uploader");
        //context.put(ARTIFACT_NAME, artifactList);
        context.put(FILE_NAME,         "johnny.jpg");
        populateRepositoryList(context);
    }

    private void populateRepositoryList(@NotNull Map<String, Object> context) {

        // Get Plan definition
        final Plan plan = (Plan) context.get("plan");
        Collection<RepositoryDefinition> repositoryDefinitions = PlanHelper.getRepositoryDefinitions(plan);

        // Key definitions by ID/Name
        Map<String, String> repositoryMap = Maps.newLinkedHashMap();
        for (RepositoryDefinition repositoryDefinition : repositoryDefinitions) {
            String idString = Long.toString(repositoryDefinition.getId());
            repositoryMap.put(idString, repositoryDefinition.getName());
        }

        context.put(ARTIFACT_LIST, repositoryMap);
    }

    @Override
    public void populateContextForEdit(@NotNull final Map<String, Object> context, @NotNull final TaskDefinition taskDefinition)
    {
        super.populateContextForEdit(context, taskDefinition);

        context.put(GITHUB_USERNAME, taskDefinition.getConfiguration().get(GITHUB_USERNAME));
        context.put(GITHUB_PASSWORD, taskDefinition.getConfiguration().get(GITHUB_PASSWORD));
        context.put(GITHUB_REPO,     taskDefinition.getConfiguration().get(GITHUB_REPO));
        context.put(ARTIFACT_NAME,   taskDefinition.getConfiguration().get(ARTIFACT_NAME));
        context.put(FILE_NAME,       taskDefinition.getConfiguration().get(FILE_NAME));
        populateRepositoryList(context);
    }

    @Override
    public void populateContextForView(@NotNull final Map<String, Object> context, @NotNull final TaskDefinition taskDefinition)
    {
        super.populateContextForView(context, taskDefinition);
        context.put(GITHUB_USERNAME, taskDefinition.getConfiguration().get(GITHUB_USERNAME));
        context.put(GITHUB_PASSWORD, taskDefinition.getConfiguration().get(GITHUB_PASSWORD));
        context.put(GITHUB_REPO,     taskDefinition.getConfiguration().get(GITHUB_REPO));
        context.put(ARTIFACT_NAME,   taskDefinition.getConfiguration().get(ARTIFACT_NAME));
        context.put(FILE_NAME,       taskDefinition.getConfiguration().get(FILE_NAME));
    }

    @Override
    public void validate(@NotNull final ActionParametersMap params, @NotNull final ErrorCollection errorCollection)
    {
        super.validate(params, errorCollection);

        final String username = params.getString("githubusername");
        if (StringUtils.isEmpty(username)){
            errorCollection.addError("githubusername", textProvider.getText("au.com.atebyte.bamboo.plugins.github.username.error"));
        }

        final String password = params.getString("githubpassword");
        if (StringUtils.isEmpty(password)){
            errorCollection.addError("githubpassword", textProvider.getText("au.com.atebyte.bamboo.plugins.github.password.error"));
        }

        final String repo = params.getString("githubrepo");
        if (StringUtils.isEmpty(repo)){
            errorCollection.addError("githubrepo", textProvider.getText("au.com.atebyte.bamboo.plugins.github.repo.error"));
        }

//        final String artifact = params.getString("artifactname");
//        if (StringUtils.isEmpty(artifact)){
//            errorCollection.addError("artifactname", textProvider.getText("au.com.atebyte.bamboo.plugins.github.artifact.error"));
//        }

        final String filename = params.getString("filename");
        if (StringUtils.isEmpty(filename)){
            errorCollection.addError("filename", textProvider.getText("au.com.atebyte.bamboo.plugins.github.filename.error"));
        }
    }

    public void setTextProvider(final TextProvider textProvider)
    {
        this.textProvider = textProvider;
    }
}
