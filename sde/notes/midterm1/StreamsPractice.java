public class StreamsPractice {
    private List<State> stateList;

    public int getTotalPopulation(List<State> stateList) {
        return stateList.stream()
            .map(state -> state.getPopulation())
            .reduce(0, (counter, statePop) -> counter + statePop );
    }

}

public class State {
    private String name;
    private int population;

    // normal constructor, getters, setters
}



public class UVAStudentManager {
    
    public void getStudentsSortedById(List<UVAStudent> studentList) {

        // sort takes in a Comparator object that defines how to compare two objects
        studentList.sort(new Comparator<UVAStudent>() {
            @Override
            public int compare(UVAStudent o1, UVAStudent o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
    }

    public void shorterStudentsSortedById(List<UVAStudent> studentList) {
        studentList.sort((s1, s2) -> Integer.compare(s1.getId(), s2.getId()));
    }

    // using static methods instead of inline comparators for .sort()
    public static int compareLastNameThenFirstName(Student s1, Student s2) {
        if (s1.getLastName().equals(s2.getLastName())) {
            return s1.getFirstName().compareTo(s2.getFirstName());
        } else {
            return s1.getLastName().compareTo(s2.getLastName());
        } 
    }

    public void sortStuddentsLastNameThenFirstName(List<UVAStudent> studentList) {
        studentList.sort(this::compareLastNameThenFirstName);
    }

}

