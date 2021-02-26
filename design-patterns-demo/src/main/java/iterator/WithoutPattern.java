package iterator;

import java.util.HashMap;
import java.util.Map;

/**
 * 需求：现在假设，有一个教室类，里面包含了一堆学生，我现在要遍历教室里的学生，怎么来玩儿？
 *
 * 如果不用任何涉及模式，直接取遍历一个类中的集合
 * 一旦这个类中对集合的使用改版了，比如从数组->map，......
 * 则，你迭代的这块代码就要改动。
 * 假如ClassRoom类的代码修改了，下游调用方的代码也需求进行修改。
 *
 * @author Liuyongfei
 * @date 2021/2/26 11:30
 */
public class WithoutPattern {
    public static void main(String[] args) {
        Student student1 = new Student("小明");
        Student student2 = new Student("小张");

//        上游修改代码，则下游需要跟着修改代码
//        Student[] students = new Student[2];
//        students[0] =student1;
//        students[1] = student2;
//        ClassRoom classRoom = new ClassRoom(students);
//        classRoom.setStudents(students);
//        Student[] resultStudents = classRoom.getStudents();
//        for (Student student : resultStudents) {
//            System.out.println(student.getName());
//        }

        Map<String,Student> students = new HashMap<>();
        students.put(student1.getName(),student1);
        students.put(student2.getName(),student2);
        ClassRoom classRoom = new ClassRoom(students);
        classRoom.setStudents(students);
        Map<String,Student> resultStudents = classRoom.getStudents();
        for (Student student : resultStudents.values()) {
            System.out.println(student.getName());
        }
    }
    public static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

//    public static class ClassRoom {
//        private Student[] students;
//        public Student[] getStudents() {
//            return students;
//        }
//
//        public void setStudents(Student[] students) {
//            this.students = students;
//        }
//    }

    /**
     * 比如上游修改了ClassRoom类的相关代码
     *
     * @author Liuyongfei
     * @date 2021/2/26 下午12:40
     */
    public static class ClassRoom {
        private Map<String,Student> students;

        public ClassRoom(Map<String, Student> students) {
            this.students = students;
        }

        public Map<String, Student> getStudents() {
            return students;
        }

        public void setStudents(Map<String, Student> students) {
            this.students = students;
        }
    }
}
