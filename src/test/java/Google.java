import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.Embeddable;
import org.jbehave.core.embedder.*;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.junit.JUnitStoryMaps;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

public class Google extends JUnitStories {
	@Override 
    public Configuration configuration(){
        return new MostUsefulConfiguration().useStoryLoader(
            new LoadFromClasspath(this.getClass()))
            .useStoryReporterBuilder(
                new StoryReporterBuilder().withCodeLocation(
                    CodeLocations.codeLocationFromClass(this
                        .getClass())).withFormats(    
                    Format.CONSOLE, Format.TXT, Format.HTML, Format.STATS))
                    ;    
    }
    
    @Override 
    public List<CandidateSteps> candidateSteps(){
        return new InstanceStepsFactory(configuration(), 
            new GoogleStep()) //can put in a comma separated list of Step implementers here
            .createCandidateSteps();
    }
     
    @Override
    protected List<String> storyPaths() {
        List<String> storiesToRun = new ArrayList<String>();
        String storyProperty = System.getProperty("story");

        if (storyProperty == null || storyProperty.isEmpty()) {
           throw new RuntimeException("Please specify which stories to run");
        }

        String[] storyNames = storyProperty.split(",");
        StoryFinder sf = new StoryFinder();
        URL baseUrl = CodeLocations.codeLocationFromClass(this.getClass());

        for (String storyName : storyNames) {
           storiesToRun.addAll(sf.findPaths(baseUrl, storyName, ""));
        }

        return storiesToRun;
    }
 

}
