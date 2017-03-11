package com.dm.magnitude;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.dm.magnitude.exceptions.CircularDependencyException;

public class Task {

	private List<Task> dependencies;
	private String name;

	public Task() {
	}

	public Task(String name) {
		this.name = name;
		this.dependencies = new ArrayList<Task>();
	}

	public void addDependency(Task task) {
		if (task.getDependencies().contains(this)) {
			throw new CircularDependencyException("Circular Dependency found.");
		} else {
			this.dependencies.add(task);
		}
	}

	public Set<Task> getDependencies() {
		Set<Task> retSet = new LinkedHashSet<Task>();
		processDependencies(retSet);
		return retSet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private void processDependencies(Set<Task> set) {
		Iterator<Task> it = dependencies.iterator();
		while (it.hasNext()) {
			Task temp = it.next();
			if (!set.contains(temp)) {
				set.add(temp);
				temp.processDependencies(set);
			}
		}
	}

	public Set<Task> processOrderBackward() {
		Set<Task> orderSet = null;
		if (this.getDependencies() != null) {
			orderSet = new LinkedHashSet<Task>();
			Set<Task> dependencySet = this.getDependencies();
			while (orderSet.size() != dependencySet.size()) {
				Iterator<Task> it = dependencySet.iterator();
				while (it.hasNext()) {
					Task temp = it.next();
					if (!orderSet.contains(temp)) {
						if (0 == temp.getDependencies().size())
							orderSet.add(temp);
						else if (orderSet.containsAll(temp.getDependencies())) {
							orderSet.add(temp);
						}
					}
				}
			}
		}
		return orderSet;
	}

	// Generating HashCode and Equals on the basis of 'name' field
	// Assuming two tasks to be same if they have same name

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
