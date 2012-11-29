package au.com.atebyte.bamboo.plugins.tasks;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.plan.artifact.ArtifactDefinition;
import com.atlassian.bamboo.plan.artifact.ArtifactDefinitionContext;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.v2.build.BuildContext;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * This class is the github uploader task
 *
 * @author tom.romano
 * @created 27/11/12 3:28 PM
 */
public class GithubUploaderTask implements TaskType
{

    private static final Logger log = Logger.getLogger(GithubUploaderTask.class);


    @NotNull
    @java.lang.Override
    public TaskResult execute(@NotNull final TaskContext taskContext) throws TaskException
    {
        final BuildLogger buildLogger = taskContext.getBuildLogger();

        final BuildContext buildContext = taskContext.getBuildContext();

        final TaskResultBuilder taskResultBuilder = TaskResultBuilder.create(taskContext);

        final ConfigurationMap configurationMap = taskContext.getConfigurationMap();

        final String username = configurationMap.get(GithubUploaderTaskConfigurator.GITHUB_USERNAME);
        final String password = configurationMap.get(GithubUploaderTaskConfigurator.GITHUB_PASSWORD);
        final String repo     = configurationMap.get(GithubUploaderTaskConfigurator.GITHUB_REPO);
        final String artifact = configurationMap.get(GithubUploaderTaskConfigurator.ARTIFACT_NAME);
        final String filename = configurationMap.get(GithubUploaderTaskConfigurator.FILE_NAME);

//        final Iterator<ArtifactDefinitionContext> artifactDefinitions = buildContext.getArtifactContext().getDefinitionContexts().iterator();
//
//        String artifactFileLocation = "";
//        //configurationMap.get("artifactname");
//
//        while(artifactDefinitions.hasNext()){
//            ArtifactDefinitionContext artifactDefinitionContext = artifactDefinitions.next();
//            if (artifactDefinitionContext.getName().equals("bob")){
//                artifactFileLocation = artifactDefinitionContext.getLocation();
//            };
//        }

        buildLogger.addBuildLogEntry("Retrieved context configuration");
        buildLogger.addBuildLogEntry("-------------------------------");
        buildLogger.addBuildLogEntry("Username: "+username);
        buildLogger.addBuildLogEntry("Password: "+password);
        buildLogger.addBuildLogEntry("Git Repo: "+repo);
        buildLogger.addBuildLogEntry("Artifact: "+artifact);
        buildLogger.addBuildLogEntry("Filename: "+filename);
        buildLogger.addBuildLogEntry("-------------------------------");

        buildLogger.addBuildLogEntry("About to attempt to call github rest API!");

        String repoUrl = "https://api.github.com/repos/"+username+"/"+repo+"/downloads";

        URL url;
        HttpURLConnection yc = null;

        try {
            url = new URL(repoUrl);
            yc = (HttpURLConnection) url.openConnection();

            String usernamePassword = username+":"+password;
            if (usernamePassword != null) {
                String encoded = new sun.misc.BASE64Encoder().encode(usernamePassword.getBytes());
                yc.setRequestProperty("Authorization", "Basic " + encoded);
            }

            yc.setRequestMethod("POST");  //DO A GET TO GET THE FILE LIST
            yc.setRequestProperty("Content-Type","application/json");
            yc.setConnectTimeout(5 * 1000);
            yc.setReadTimeout(10 * 1000);
            yc.setDoInput(true);
            yc.setUseCaches(false);
            yc.setDoOutput(true);
            //yc.connect();

            DataOutputStream wr = new DataOutputStream (
                    yc.getOutputStream ());
            wr.writeBytes ("{\"name\":\"test_git_file.jpg\",\"size\": 12345,\"description\": \"Plugin test release\",\"content_type\": \"image/jpeg\"}");
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = yc.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            buildLogger.addBuildLogEntry("Response from API Call to "+repoUrl+" --> \n"+response.toString());
            log.info("response from call " + response.toString());

            int response1Code = yc.getResponseCode();
            buildLogger.addBuildLogEntry("Response Code from API Call to "+repoUrl+" --> "+response1Code);
            log.info("response code from call "+response1Code);

        }catch(Exception e ){
            buildLogger.addBuildLogEntry("ERROR Occurred "+e.getMessage());
            log.info("Error caught in URL connection", e);
            return  taskResultBuilder.failed().build();
        } finally {

            if(yc != null) {
                yc.disconnect();
            }
        }

        return taskResultBuilder.success().build();
    }
}