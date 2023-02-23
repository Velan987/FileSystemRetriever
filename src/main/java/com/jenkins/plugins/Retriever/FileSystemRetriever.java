package com.jenkins.plugins.Retriever;

import java.io.File;

import org.jenkinsci.Symbol;
import org.jenkinsci.plugins.workflow.libs.LibraryRetriever;
import org.jenkinsci.plugins.workflow.libs.LibraryRetrieverDescriptor;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.DataBoundConstructor;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.Run;
import hudson.model.TaskListener;
import jenkins.model.Jenkins;

public class FileSystemRetriever extends LibraryRetriever{

	//upto library project root folder
	private String libraryFilePath;
	
	@DataBoundConstructor
	public FileSystemRetriever(String libraryPath){
		libraryFilePath = libraryPath;
	}
	
	Jenkins getJenkins() {
		return Jenkins.getInstance();
	}
	
	@Override
	public void retrieve(String name, String version, FilePath target, Run<?, ?> run, TaskListener listener)
			throws Exception {
		retrieve(name, version, true, target, run, listener);
		
	}
	
	@Override
	public void retrieve(String name, String version, boolean changelog, FilePath target, Run<?, ?> run,
			TaskListener listener) throws Exception {
		File file = new File(libraryFilePath);
		FilePath filePath = new FilePath(file);
		filePath.copyRecursiveTo(target);
	}


	@Override
	public LibraryRetrieverDescriptor getDescriptor(){
		return super.getDescriptor();
	}
	
	@Symbol("fileSystemRetriever")
	@Extension
	@Restricted(NoExternalUse.class)
	public static class DescriptorImpl extends LibraryRetrieverDescriptor {

		@Override
		public @NonNull String getDisplayName() {
			return "FileSystemRetriever";
		}
	}
}
