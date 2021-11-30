package com.fullstackboy.mybatis.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用迭代器设计模式的demo
 *
 * 面向Interator接口编程，无论底层的数据结构和迭代算法如何变化，调用者都不用修改代码。
 *
 *
 * @author Liuyongfei
 * @date 2021/2/26 12:50
 */
public class InteratorPatternDemo {

    public static void main(String[] args) {
        Student student1 = new Student("小明");
        Student student2 = new Student("小张");
        ClassRoom classRoom = new ClassRoom(2);
        classRoom.addStudents(student1);
        classRoom.addStudents(student2);

        Interator interator = classRoom.interator();
        while (interator.hasNext()) {
            Student student = (Student) interator.next();
            System.out.println(student.getStudent());
        }
    }


    public  interface Interator {
        public abstract boolean hasNext();
        public abstract Object next();
    }

    /**
     * 一个集合
     * @author Liuyongfei
     * @date 2021/2/26 下午12:53
     */
    public interface Aggregate{
        // 集合对应的迭代器
        public abstract Interator interator();
    }

    /**
     * 学生类
     * @author Liuyongfei
     * @date 2021/2/26 下午12:55
     */
    public static class Student {
        private String student;

        public Student(String student) {
            this.student = student;
        }

        public String getStudent() {
            return student;
        }

        public void setStudent(String student) {
            this.student = student;
        }
    }

    /**
     * 定义一个教室类
     *
     * @author Liuyongfei
     * @date 2021/2/26 下午2:03
     */
    public static class ClassRoom implements Aggregate {
        // 假如：这里改代码，把数组改为List
        // private Student[] students;
        private List<Student> students;

        // 相当于数组的长度
        private int last;

        public ClassRoom(int size) {
//            this.students = new Student[size];
            this.students = new ArrayList<>(2);
        }


        public int getLast() {
            return last;
        }

        public void setLast(int last) {
            this.last = last;
        }

        public void addStudents(Student student) {
//            this.students[last] = student;
            this.students.add(student);
            last++;
        }

        public Student getStudents(int index) {
//            return students[index];
            return students.get(index);
        }

        public int getLength() {
            return last;
        }

        @Override
        public Interator interator() {
            return new ClassRoomInterator(this);
        }
    }

    /**
     * 定义一个迭代器类，设置hasNext()和next()方法
     *
     * @author Liuyongfei
     * @date 2021/2/26 下午2:00
     */
    public static class ClassRoomInterator implements Interator {
        private ClassRoom classRoom;
        private int index;

        public ClassRoomInterator(ClassRoom classRoom) {
            this.classRoom = classRoom;
            this.index = 0;
        }
        @Override
        public boolean hasNext() {
            if (index < classRoom.getLength()) {
                return true;
            } else {
                return false;
            }
        }
        @Override
        public Object next() {
            Student student = classRoom.getStudents(index);
            index++;
            return student;
        }
    }
}


