package test;

import java.util.Iterator;

public class UseStudent {
	public static void main(String[] args) {
		Student st = new Student();
		Bell bell = new Bell();
		String[] subjectArr= {"����", "����", "����", "����"};
		
		for (int i = 0; i < subjectArr.length; i++) {
			st.study1();			
			st.study2();			
			st.study3();			
			st.study4();			
		}

	}
}
