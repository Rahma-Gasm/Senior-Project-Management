
public class Student {

    private String studentID;
    private String studentName;
    private String research_intrest;
    private String Topic;
    private int[] course;
    private boolean approval;
    private String supervisorID;
    private Student next;

    public Student(String studentID, String studentName, String research_intrest,
            String Topic, int[] course, boolean approval, String supervisorID) {
        this(studentID, studentName, research_intrest, Topic, course, approval, supervisorID, null);
    }

    private Student(String studentID, String studentName, String research_intrest,
            String Topic, int[] course, boolean approval, String supervisorID, Student next) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.research_intrest = research_intrest;
        this.Topic = Topic;
        this.course = course;
        this.approval = approval;
        this.supervisorID = supervisorID;
        this.next = next;

    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getResearch_intrest() {
        return research_intrest;
    }

    public void setResearch_intrest(String research_intrest) {
        this.research_intrest = research_intrest;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
    }

    public int[] getCourse() {
        return course;
    }

    public int print_courses(int index) {
        return course[index];
    }

    public void setCourse(int[] course) {
        this.course = course;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public Student getNext() {
        return next;
    }

    public void setNext(Student next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", studentName=" + studentName + ", research_intrest=" + research_intrest + ", Topic=" + Topic + ", course=" + course + ", approval=" + approval + ", supervisorID=" + supervisorID + ", next=" + next + '}';
    }

}
