class Student extends Person {
    String id;
    
    Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    String getID() {
        return id;
    }

    void setID(String id) {
        this.id = id;
    }

    void introduce() {
        System.out.println("My name is " + this.name);
        System.out.println("My ID is " + this.id);
    }

}