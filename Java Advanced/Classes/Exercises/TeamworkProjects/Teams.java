package Classes.TeamworkProjects;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Teams {
    private String creatorName;
    private String teamName;
    private TreeSet<String> members;

    public Teams(String creatorName, String teamName) {
        this.creatorName = creatorName;
        this.teamName = teamName;
        this.members = new TreeSet<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public TreeSet<String> getMembers() {
        return this.members;
    }
}
