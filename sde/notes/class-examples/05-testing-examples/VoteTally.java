import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class VoteTally {
    private final Map<String, Integer> candidateVotes;

    public VoteTally() {
        this(new HashMap<>());
    }

    protected VoteTally(Map<String, Integer> candidateVotes) {
        this.candidateVotes = candidateVotes;
    }

    public int getNumCandidates() {
        return candidateVotes.size();
    }

    public Set<String> getCandidates() {
        return candidateVotes.keySet();
    }

    public int getVotesForCandidate(String candidate) {
        if (!candidateVotes.containsKey(candidate)) {
            return 0;
        }
        return candidateVotes.get(candidate);
    }

    public int getTotalVotes() {
        var voteTotal = 0;
        for (int votes : candidateVotes.values()) {
            voteTotal += votes;
        }
        return voteTotal;
    }

    /**
     * Adds a number of votes for each candidate.
     * @param precinctResults - the results from a precinct
     */
    public void addVotes(Map<String, Integer> precinctResults) {
        for (var candidate: precinctResults.keySet()) {
            var newVotes = precinctResults.get(candidate);
            addVotesForCandidate(newVotes, candidate);
        }
    }

    /**
     * Add votes to a specific candidate.
     * @param newVotes - the number of votes received
     * @param candidate - the name of the candidate
     */
    private void addVotesForCandidate(int newVotes, String candidate) {
        if (newVotes < 0) {
            throw new IllegalArgumentException("Cannot add negative votes for a candidate");
        }
        if (candidateVotes.containsKey(candidate)) {
            var currentVotes = candidateVotes.get(candidate);
            candidateVotes.put(candidate, currentVotes + newVotes);
        } else {
            candidateVotes.put(candidate, newVotes);
        }
    }

    /**
     * Returns the winner of the election. That is, the name of the candidate with the most votes.
     * However, if there is a tie, or no candidate has received any votes this returns {@link Optional#empty()}
     */
    public Optional<String> getWinner() {
        if (candidateVotes.isEmpty()) {
            return Optional.empty();
        }
        var isTie = true;
        var winningCandidate = "";
        var maxVotes = 0;
        for (var candidate : candidateVotes.keySet()) {
            var votes = candidateVotes.get(candidate);
            if (votes > maxVotes) {
                maxVotes = votes;
                winningCandidate = candidate;
                isTie = false;
            } else if (votes == maxVotes) {
                isTie = true;
            }
        }
        if (isTie) {
            return Optional.empty();
        } else {
            return Optional.of(winningCandidate);
        }
    }

    /**
     * Returns a string containing each candidate and the number of votes they have received on a
     * new line. Example:<br><tt>
     * John Smith          |   789<br>
     * Jane Doe            |  1234<br>
     * Votey McVoteface    |  5678<br>
     * </tt>
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var candidate : candidateVotes.keySet()) {
            stringBuilder.append(String.format(
                    "%-20s | %6d", candidate, candidateVotes.get(candidate)
            ));
        }
        return stringBuilder.toString();
    }
}
