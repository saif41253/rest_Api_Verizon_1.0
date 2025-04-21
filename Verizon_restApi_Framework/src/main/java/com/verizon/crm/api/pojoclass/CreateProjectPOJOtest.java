package com.verizon.crm.api.pojoclass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProjectPojo{
	String projectName;
	String projectStatus;
	int teamSize;
	List<String> teammember;
	ProjectManager pobj;
	public ProjectPojo(String projectName, String projectStatus, int teamSize, List<String> teammember, ProjectManager pobj) {
		super();
		this.projectName = projectName;
		this.projectStatus = projectStatus;
		this.teamSize = teamSize;
		this.teammember = teammember;
		this.pobj = pobj;
	}
	
		public ProjectManager getPobj() {
		return pobj;
	}

	public void setPobj(ProjectManager pobj) {
		this.pobj = pobj;
	}

		public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public List<String> getTeammember() {
		return teammember;
	}
	public void setTeammember(List<String> teammember) {
		this.teammember = teammember;
	}
	
}
class ProjectManager{
	String name;
	String id;
	
	public ProjectManager(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

public class CreateProjectPOJOtest {
	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		List<String> lst = new ArrayList<String>();
		lst.add("Saif");
		lst.add("Simar");
		lst.add("Safina");
		
		ProjectManager pm = new ProjectManager("Sagar", "tp01");
		ProjectPojo projectObject = new ProjectPojo("mri", "Created", 10, lst, pm);
		System.out.println(projectObject.projectName);
		
		ObjectMapper obMap = new ObjectMapper();
		obMap.writeValue(new File("./proj1.json"), projectObject);
		
	}
}
