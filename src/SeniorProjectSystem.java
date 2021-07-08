
import java.io.PrintWriter;

public class SeniorProjectSystem {

    private Student student_head;
    private Supervisor supervisor_head;

    public SeniorProjectSystem() {
        this.student_head = null;
        this.supervisor_head = null;
    }

    public boolean checked_id(String id) {
        return checked_id(student_head, id);
    }

    public boolean checked_id(Student head_student, String id) {
        Student help_ptr = head_student;
        while (help_ptr != null) {
            if (help_ptr.getStudentID().equals(id)) {
                return true;
            }
            help_ptr = help_ptr.getNext();
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean checkApproval(String id) {
        Student help_ptr = student_head;
        boolean check = false;
        while (help_ptr != null) {
            if (help_ptr.getStudentID().equals(id)) {
                check = true;
                break;

            }
            help_ptr = help_ptr.getNext();
        }
        if (check) {
            if (help_ptr.isApproval()) {
                return true;
            }
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean check_Course() {
        Student help_ptr = student_head;
        boolean course = true;
        while (help_ptr != null) {
            for (int i = 0; i < help_ptr.getCourse().length; i++) {
                if (help_ptr.getCourse()[i] == 0) {
                    course = false;
                } else {
                    course = true;
                    help_ptr.setApproval(true);
                }
            }
        }
        return course;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    public void SpervisorStudentInterests(String id) {
        Supervisor help_ptr = supervisor_head;
        Student ptr = student_head;
        while (ptr != null) {
            if (ptr.getStudentID().equals(id)) {
                break;
            }
            ptr = ptr.getNext();
        }
        while (help_ptr != null) {
            for (int i = 0; i < help_ptr.getIntrest().length; i++) {
                if (ptr.getResearch_intrest().equals(help_ptr.getIntrest())) {
                    ptr.setSupervisorID(help_ptr.getSupervisorID());
                } else {
                    System.out.println("Sorry! there is no supevisor his/her interset match the student interest ");
                }
            }
        }
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("\n\t|%-13s|%-24s|%-77s\n", "Supervisor  ID", "name", "Research interests ");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        Supervisor helpPtr = supervisor_head;
        System.out.println("\t");
        while (helpPtr != null) {
            // Print the data value of the node
            System.out.printf("\t|%-13s|%-24s|", helpPtr.getSupervisorID(), helpPtr.getSupervisorName().trim());
            System.out.println("");
            helpPtr = helpPtr.getNext();
        }
        System.out.println();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void add_topic(String topic, String id) {
        Student help_ptr = student_head;
        while (help_ptr != null) {
            if (help_ptr.getStudentID().equals(id)) {
                help_ptr.setTopic(topic);
            }
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void deleteStudent(String id) {

        if (student_head.getStudentID().equals(id)) {
            student_head = student_head.getNext();
        } else {
            Student help_ptr = student_head;
            while (help_ptr.getNext() != null) {
                if (help_ptr.getNext().getStudentID().equals(id)) {
                    help_ptr.setNext(help_ptr.getNext().getNext());
                    break;
                }
                help_ptr = help_ptr.getNext();
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void printApprovedSeniorProjects() {

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void print_student() {
        print_student(student_head);
    }

    private void print_student(Student head) {
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-12s|%-24s|%-46s|%-47s|%-14s|%-14s\n", "Student ID", "Research interests", "Suggested topic ", "Courses", "Approval", "Supervisor ID");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-12s%-25s%-47s%47s%-15s%-15s|\n", "", "|", "|", "|CPCS223|CPIS334|CPCS351|CPCS241|CPCS361|CPCS331", "|", "|");
        Student helpPtr = head;
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Print the data value of the node
            System.out.printf("|%-12s|%-24s|%-46s|", helpPtr.getStudentID(), helpPtr.getResearch_intrest(), helpPtr.getTopic(), helpPtr.equals("") ? "" : helpPtr.getTopic());
            for (int i = 0; i < helpPtr.getCourse().length; i++) {
                System.out.printf("%-7d|", helpPtr.print_courses(i));
            }
            System.out.printf("%-14b|%-14s|\n", helpPtr.isApproval(), helpPtr.getSupervisorID());
            helpPtr = helpPtr.getNext();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void print_supervisor() {
        print_supervisor(supervisor_head);
    }

    private void print_supervisor(Supervisor head) {

        // Traverse to correct insertion point
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\t|%-13s|%-24s|%-77s\n", "Supervisor  ID", "name", "Research interests ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        Supervisor helpPtr = head;
        System.out.println("\t");
        while (helpPtr != null) {
            // Print the data value of the node
            System.out.printf("\t|%-13s|%-24s|", helpPtr.getSupervisorID(), helpPtr.getSupervisorName().trim());
            for (int i = 0; i < helpPtr.getIntrest().length; i++) {
                System.out.printf("%-25s|", helpPtr.getIntrest()[i]);
            }
            System.out.println("");
            helpPtr = helpPtr.getNext();
        }
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void insert_student(Student new_student) {
        student_head = insert_student(student_head, new_student);
    }

    private Student insert_student(Student head_student, Student new_student) {
        // IF there is no list, newNode will be the first node, so just return it
        if (head_student == null || head_student.getStudentID().compareTo(student_head.getStudentID()) > 0) {
            head_student = new_student;
            return head_student;
        } // ELSE, we have a list. Insert the new node at the correct location
        else {
            // We need to traverse to the correct insertion location...so we need a help ptr
            Student helpPtr = head_student;
            // Traverse to correct insertion point
            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getStudentID().compareTo(student_head.getStudentID()) > 0) {//?
                    break; // we found our spot and should break out of the while loop
                }
                helpPtr = helpPtr.getNext();
            }

            new_student.setNext(helpPtr.getNext());
            helpPtr.setNext(new_student);
        }
        // Return head
        return head_student;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void insert_supervisor(Supervisor sup) {
        supervisor_head = insert_supervisor(supervisor_head, sup);
    }

    private Supervisor insert_supervisor(Supervisor supervisor_head, Supervisor sup) {
        if (supervisor_head == null) {
            supervisor_head = sup;
            return supervisor_head;

        } else {
            Supervisor help_ptr = supervisor_head;
            while (help_ptr.getNext() != null) {
                help_ptr = help_ptr.getNext();
            }
            help_ptr.setNext(sup);
        }
        return supervisor_head;
    }

    public void print_file_student(PrintWriter output) {

        output.println("");
        output.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        output.printf("|%-12s|%-24s|%-46s|%-47s|%-14s|%-14s\n", "Student ID", "Research interests", "Suggested topic ", "Courses", "Approval", "Supervisor ID");
        output.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        output.printf("|%-12s%-25s%-47s%47s%-15s%-15s|\n", "", "|", "|", "|CPCS223|CPIS334|CPCS351|CPCS241|CPCS361|CPCS331", "|", "|");
        Student helpPtr = student_head;
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Print the data value of the node
            output.printf("|%-12s|%-24s|%-46s|", helpPtr.getStudentID(), helpPtr.getResearch_intrest(), helpPtr.getTopic(), helpPtr.equals("") ? "" : helpPtr.getTopic());
            for (int i = 0; i < helpPtr.getCourse().length; i++) {
                output.printf("%-7d|", helpPtr.print_courses(i));
            }
            output.printf("%-14b|%-14s|\n", helpPtr.isApproval(), helpPtr.getSupervisorID());
            helpPtr = helpPtr.getNext();
        }
        output.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        output.println();
    }

    public void print_file_supervisor(PrintWriter output) {
        output.println("");
        output.println("------------------------------------------------------------------------------------------------------------------------------");
        output.printf("\t|%-13s|%-24s|%-77s\n", "Supervisor  ID", "name", "Research interests ");
        output.println("------------------------------------------------------------------------------------------------------------------------------");
        Supervisor helpPtr = supervisor_head;
        output.println("\t");
        while (helpPtr != null) {
            // Print the data value of the node
            output.printf("\t|%-13s|%-24s|", helpPtr.getSupervisorID(), helpPtr.getSupervisorName().trim());
            for (int i = 0; i < helpPtr.getIntrest().length; i++) {
                output.printf("%-25s|", helpPtr.getIntrest()[i]);
            }
            System.out.println("");
            helpPtr = helpPtr.getNext();
        }
        output.println();
        output.println("------------------------------------------------------------------------------------------------------------------------------");

    }
}
