package dml.maven;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import pt.ist.fenixframework.artifact.FenixFrameworkArtifact;

public class DmlMojoUtils {

    public static List<String> readDmlFilePathsFromArtifact(Log log, Set<Artifact> artifactSet) {
        List<String> dmlFilePaths = new ArrayList<String>();
        for (Artifact artifact : artifactSet) {
            if (artifact.getType().equals("pom") || (artifact.getGroupId().equals("pt.ist") && artifact.getArtifactId().equals("fenix-framework-core"))) {
                continue; //ignoring pom projects and the fenix-framework-core project
            }
            String absolutePath = artifact.getFile().getAbsolutePath();
            try {
                JarFile jarFile = new JarFile(absolutePath);
                for (Enumeration<JarEntry> enumeration = jarFile.entries(); enumeration.hasMoreElements();) {
                    JarEntry jarEntry = enumeration.nextElement();
                    if (jarEntry.getName().endsWith(".dml")) {
                        dmlFilePaths.add("jar:file:" + absolutePath + "!/" + jarEntry.getName());
                    }
                }
            } catch (IOException e) {
                log.error(e);
            }
        }
        Collections.reverse(dmlFilePaths);
        return dmlFilePaths;
    }

    public static List<String> getDependencyArtifacts(MavenProject mavenProject, Log log) {
        List<String> dependencyArtifacts = new ArrayList<String>();
        for(Artifact artifact : mavenProject.getDependencyArtifacts()) {
           if(artifact.getType().equals("pom") || (artifact.getGroupId().equals("pt.ist") && artifact.getArtifactId().equals("fenix-framework-core"))) {
                continue; //ignoring pom projects and the fenix-framework-core project
            }
            String absolutePath = artifact.getFile().getAbsolutePath();
            try {
               JarFile jarFile = new JarFile(absolutePath);
               for(Enumeration<JarEntry> enumeration = jarFile.entries(); enumeration.hasMoreElements();) {
                   JarEntry jarEntry = enumeration.nextElement();
                   if(jarEntry.getName().endsWith(".dml")) {
                       dependencyArtifacts.add(artifact.getArtifactId());
                   }
               }
            } catch(IOException e) {
                log.error(e);
            }
        }
        Collections.reverse(dependencyArtifacts);
        return dependencyArtifacts;
    }
}