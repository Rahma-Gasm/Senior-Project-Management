
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StdMenue {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter student_output = new PrintWriter("Student_info.txt");//create object to write in file
        PrintWriter supervisor_output = new PrintWriter("Supervisor_info.txt");

        File file = new File("student.txt");//create object from file
        Scanner read = new Scanner(file);//create scanner object 

        Scanner input = new Scanner(System.in);

        //create object from SeniorProjectSystem to can invoke method from it
        SeniorProjectSystem senior_project = new SeniorProjectSystem();

        //used this to make program read until read has not next
        while (read.hasNext()) {

            senior_project.insert_student(read_student_info(read));
        }

        File file2 = new File("supervisor.txt");
        Scanner read2 = new Scanner(file2);//create object to read from file

        while (read2.hasNext()) {
            String[] line = read2.nextLine().split("#|,");
            senior_project.insert_supervisor(read_supervisor_info(line));

        }

        int choice;

        do {
            showMenu();//invoke method to show menu this methos exit below
            choice = input.nextInt();

            //if user entre one
            if (choice == 1) {
                System.out.println("Please enter the student information: ");
                System.out.println("");
                System.out.print("student ID: ");
                String id = input.next();
                //invoke method from SeniotProjectSystem this method check if the id is exit or not                
                if (senior_project.checked_id(id) == true) {
                    System.out.println("The student already exists! ");
                } else {//if id doen't exit
                    System.out.print("Student Name: ");
                    String name = input.next();
                    System.out.print("research interest: ");
                    String research = input.next();
                    System.out.print("courses:");
                    input.nextLine();
                    //because there are many course we create array to read many course
                    String[] course_name = input.nextLine().toUpperCase().split(", ");
                    int[] course_index = {0, 0, 0, 0, 0, 0};
                    //check if the courses that user enter match with the requairment courses if match put one in index else put 0
                    for (int i = 0; i < course_name.length; i++) {
                        if (course_name[i].trim().equals("CPCS223")) {
                            course_index[0] = 1;
                        } else if (course_name[i].trim().equals("CPIS334")) {
                            course_index[1] = 1;
                        } else if (course_name[i].trim().equals("CPCS351")) {
                            course_index[2] = 1;
                        } else if (course_name[i].trim().equals("CPCS241")) {
                            course_index[3] = 1;
                        } else if (course_name[i].trim().equals("CPCS361")) {
                            course_index[4] = 1;
                        } else if (course_name[i].trim().equals("CPCS331")) {
                            course_index[5] = 1;
                        }
                    }

                    System.out.println("");
                    Student new_student = new Student(id, name, research, "", course_index, false, "0");
                    //invoke method
                    senior_project.insert_student(new_student);
                    System.out.println("The student is added!");
                }

            } else if (choice == 2) {
                //invoke method to print student information 
                senior_project.print_supervisor();

            } else if (choice == 3) {
                //invoke method to print supervisor information 
                senior_project.print_student();

            } else if (choice == 4) {
                System.out.print("Please enter the student ID: ");
                String student_id = input.next();
                if (senior_project.checkApproval(student_id) == false) { //check from approval                                   
                    if (senior_project.check_Course() == false) {//check from courses
                        System.out.println("Sorry! this student doesn't complete all required subject");
                    } else {
                        System.out.println("The student studied all required subject, and please enter the topic: ");
                        String topic = input.nextLine();
                        senior_project.add_topic(student_id, topic);
                        senior_project.SpervisorStudentInterests(student_id);

                    }
                }

            } else if (choice == 5) {
                senior_project.print_student();
                System.out.println("Please select your option: ");
                System.out.println("1. Remove student by his/her ID ");
                System.out.println("2. Remove students who have not to complete the minimum requirement.");
                System.out.print(" > Please enter your choice: ");
                int option = input.nextInt();
                if (option == 1) {
                    System.out.print("Please enter the student ID that you would like to remove: ");
                    String id = input.next();
                    if (senior_project.checked_id(id) == false) {
                        System.out.println("Sorry! can't delete because this ID doesn't exit ");

                    } else {
                        senior_project.deleteStudent(id);
                    }
                } else if (option == 2) {

                }

            } else if (choice == 6) {

            } else {
                System.out.println("Good bye!");
                System.exit(0);
            }
        } while (choice != 7);

        //invoke method from SeniorProjectSystem that print all student information in file
        senior_project.print_file_student(student_output);
        senior_project.print_file_supervisor(supervisor_output);

        supervisor_output.close();
        student_output.close();
        read2.close();
        read.close();

    }

    public static void showMenu() {
        System.out.println("-----------------------------------------------------");
        System.out.println("--------    CS Project Management System     --------");
        System.out.println("-----------------------------------------------------");
        System.out.println("   1. Add a new student                              ");
        System.out.println("   2. Print supervisor list                          ");
        System.out.println("   3. Print student list                             ");
        System.out.println("   4. Add research topic                             ");
        System.out.println("   5. Remove student                                 ");
        System.out.println("   6. Print senior project list in ascending order   ");
        System.out.println("   7. Exit                                           ");
        System.out.println("-----------------------------------------------------");
        System.out.println("");
        System.out.print(" > Please enter your choice: ");
    }

    // this method read student information from file
    public static Student read_student_info(Scanner read) {
        String[] student = read.nextLine().split(", |,|#");
        String student_id = student[0];
        String student_name = student[1];
        String research_intrest = student[2];
        String project_topic = student[3];
        int[] course = {Integer.parseInt(student[4]), Integer.parseInt(student[5]), Integer.parseInt(student[6]), Integer.parseInt(student[7]),
            Integer.parseInt(student[8]), Integer.parseInt(student[9])};
        boolean approval = Boolean.parseBoolean(student[10]);
        String supervisor_id = student[11];

        Student stu_info = new Student(student_id, student_name, research_intrest, project_topic, course, approval, supervisor_id);

        return stu_info;
    }

    //this method read Supervisor information from file
    public static Supervisor read_supervisor_info(String[] line) {
        String id = line[0];
        String name = line[1];
        String[] array = new String[line.length - 2];
        for (int i = 0; i < array.length; i++) {
            array[i] = line[i + 2];

        }
        Supervisor sup = new Supervisor(id, name, array);
        return sup;

    }
}
