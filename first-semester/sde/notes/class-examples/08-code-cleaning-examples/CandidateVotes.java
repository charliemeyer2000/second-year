public class CandidateVotes {
    private String name;
    private int votes;

    public CandidateVotes(String name, int votes) {
        this.name = name;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidateVotes candidateVotes = (CandidateVotes) o;

        if (votes != candidateVotes.votes) return false;
        return name != null ? name.equals(candidateVotes.name) : candidateVotes.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + votes;
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", population=" + votes +
                '}';
    }
}