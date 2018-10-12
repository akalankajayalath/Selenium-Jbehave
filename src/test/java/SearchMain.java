/*
 * Sashika Akalanka jayaalth
 * 2018/10/09
 * */

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

//There are 3 different classes named junitstory,junitstories and junitstorymaps
public class SearchMain extends JUnitStories {
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
            new SearchStep()) //can put in a comma separated list of Step implementers here
            .createCandidateSteps();
    }
     
    @Override
    protected List<String> storyPaths() {
    	//this overridden method returns Stories list that needed to be executed in the tests
        List<String> storiesToRun = new ArrayList<String>();
        String storyProperty = System.getProperty("story");

        if (storyProperty == null || storyProperty.isEmpty()) {
           throw new RuntimeException("Please specify which stories to run");
        }

        String[] storyNames = storyProperty.split(",");
        StoryFinder sf = new StoryFinder();
        
        //three code locating methods location from class , path , URL
        URL baseUrl = CodeLocations.codeLocationFromClass(this.getClass());
        
        for (String storyName : storyNames) {
        	//findpaths method has (location to get stories,include stories array,exclude stories array)
           storiesToRun.addAll(sf.findPaths(baseUrl, storyName, ""));
        }

        return storiesToRun;
    }
 

}
