package au.com.atebyte.bamboo.plugins;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.task.TaskContext;
import com.atlassian.bamboo.task.TaskException;
import com.atlassian.bamboo.task.TaskResult;
import com.atlassian.bamboo.task.TaskResultBuilder;
import com.atlassian.bamboo.task.TaskType;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class is the github uploader task
 *
 * @author tom.romano
 * @created 27/11/12 3:28 PM
 */
public class GithubUploaderTask implements TaskType
{

    @NotNull
    @java.lang.Override
    public TaskResult execute(@NotNull final TaskContext taskContext) throws TaskException
    {
        final BuildLogger buildLogger = taskContext.getBuildLogger();

        buildLogger.addBuildLogEntry("About to attempt to call github rest API!");

        try {

            URL url = new URL("https://bamboo.4impact.net.au/rest/api/latest/result/MOBSITE-BUILDANDDEV.json?os_authType=basic");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            buildLogger.addBuildLogEntry("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                buildLogger.addBuildLogEntry(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            buildLogger.addBuildLogEntry(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {

            buildLogger.addBuildLogEntry(e.getMessage());
            e.printStackTrace();

        }

        //final String say = taskContext.getConfigurationMap().get("say");

        //buildLogger.addBuildLogEntry(say);

        return TaskResultBuilder.create(taskContext).success().build();
    }
}