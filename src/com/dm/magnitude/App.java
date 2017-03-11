package com.dm.magnitude;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class App {

	public static void main(String[] args) {
		Task A = new Task("A");
		Task[] dependencies = prepareDependencies();
		for (Task dependency : dependencies)
			A.addDependency(dependency);

		System.out.println("**************DEPENDENCIES***************");
		Set<Task> set = A.getDependencies();
		Iterator<Task> it = set.iterator();
		while (it.hasNext()) {
			Task temp = it.next();
			System.out.println(temp.getName());
		}

		System.out.println("***********ORDER_OF_EXECUTION************");

		Set<Task> list = A.processOrderBackward();
		Iterator<Task> iterator = list.iterator();
		while (iterator.hasNext()) {
			Task temp = iterator.next();
			System.out.println(temp.getName());
		}
	}

	public static Task[] prepareDependencies() {

		Task B = new Task("B");
		Task C = new Task("C");
		Task D = new Task("D");
		B.addDependency(C);
		B.addDependency(D);
		//D.addDependency(B);

		Task E = new Task("E");
		Task F = new Task("F");
		Task G = new Task("G");
		Task H = new Task("H");
		E.addDependency(F);
		E.addDependency(G);

		F.addDependency(G);
		G.addDependency(H);

		Task[] taskArray = new Task[] { B, E, F, G };

		return taskArray;
	}

}
