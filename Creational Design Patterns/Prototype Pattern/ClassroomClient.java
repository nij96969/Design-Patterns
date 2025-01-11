interface Classroom {
    Classroom clone();
    void displayDetails();
}

class EnglishClassroom implements Classroom {
    private int totalStudents;

    public EnglishClassroom(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    @Override
    public Classroom clone() {
        return new EnglishClassroom(this.totalStudents);
    }

    @Override
    public void displayDetails() {
        System.out.println("Total Students in English Classroom: " + totalStudents);
    }
}

class ClassroomFactory {
    private Classroom prototype;

    ClassroomFactory(Classroom prototype) {
        this.prototype = prototype;
    }

    public Classroom createClassroom() {
        return prototype.clone();
    }
}

class ClassroomClient {
    public static void main(String[] args) {
        Classroom englishClassroom = new EnglishClassroom(56);

        ClassroomFactory classroomFactory = new ClassroomFactory(englishClassroom);

        Classroom clonedClassroom = classroomFactory.createClassroom();

        clonedClassroom.displayDetails();
    }    
}
