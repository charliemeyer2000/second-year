import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VoteTallyTest {
    @Test
    void constructor_Initial() {
        var testVoteTally = new VoteTally();

        assertEquals(0, testVoteTally.getNumCandidates());
        assertTrue(testVoteTally.getCandidates().isEmpty());
    }

    @Test
    void addVotes_oneCandidate() {
        var testVoteTally = new VoteTally();

        testVoteTally.addVotes(Map.of("John Smith", 10));

        assertEquals(1, testVoteTally.getNumCandidates());
        assertTrue(testVoteTally.getCandidates().contains("John Smith"));
        assertEquals(10, testVoteTally.getVotesForCandidate("John Smith"));
    }

    @Test
    void addVotes_twoCandidates() {
        var testVoteTally = new VoteTally();

        testVoteTally.addVotes(Map.of("John Smith", 10, "Jane Doe", 15));

        assertEquals(2, testVoteTally.getNumCandidates());
        assertTrue(testVoteTally.getCandidates().contains("John Smith"));
        assertTrue(testVoteTally.getCandidates().contains("Jane Doe"));
        assertEquals(10, testVoteTally.getVotesForCandidate("John Smith"));
        assertEquals(15, testVoteTally.getVotesForCandidate("Jane Doe"));
    }

    @Test
    void addVotes_existingCandidates() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 20, "Votey McVoteface", 10)));

        testVoteTally.addVotes(Map.of("John Smith", 10, "Jane Doe", 15));

        assertEquals(3, testVoteTally.getNumCandidates());
        assertTrue(testVoteTally.getCandidates().contains("John Smith"));
        assertTrue(testVoteTally.getCandidates().contains("Jane Doe"));
        assertTrue(testVoteTally.getCandidates().contains("Votey McVoteface"));
        assertEquals(30, testVoteTally.getVotesForCandidate("John Smith"));
        assertEquals(15, testVoteTally.getVotesForCandidate("Jane Doe"));
        assertEquals(10, testVoteTally.getVotesForCandidate("Votey McVoteface"));

    }

    @Test
    void getWinner_oneCandidate_withVotes() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10)));

        var optionalWinner = testVoteTally.getWinner();
        assertTrue(optionalWinner.isPresent());
        assertEquals("John Smith", optionalWinner.get());
    }

    @Test
    void getWinner_twoCandidates_noTie() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10, "Jane Doe", 15)));

        var optionalWinner = testVoteTally.getWinner();
        assertTrue(optionalWinner.isPresent());
        assertEquals("Jane Doe", optionalWinner.get());
    }

    @Test
    void getVotesForCandidate_present() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10, "Jane Doe", 15)));

        assertEquals(10, testVoteTally.getVotesForCandidate("John Smith"));
    }

    @Test
    void getVotesForCandidate_notPresent() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10, "Jane Doe", 15)));

        assertEquals(0, testVoteTally.getVotesForCandidate("Votey McVoteface"));
    }

    @Test
    void getVotesForCandidate_empty() {
        var testVoteTally = new VoteTally();

        assertEquals(0, testVoteTally.getVotesForCandidate("Votey McVoteface"));
    }

    @Test
    void getTotalVotes_twoCandidates() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10, "Jane Doe", 15)));

        assertEquals(25, testVoteTally.getTotalVotes());
    }

    @Test
    void getTotalVotes_oneCandidate() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10)));

        assertEquals(10, testVoteTally.getTotalVotes());
    }

    @Test
    void getTotalVotes_empty() {
        var testVoteTally = new VoteTally();

        assertEquals(0, testVoteTally.getTotalVotes());
    }

    @Test
    void getWinner_twoCandidates_tie() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 10, "Jane Doe", 10)));

        var optionalWinner = testVoteTally.getWinner();
        assertFalse(optionalWinner.isPresent());
        assertEquals(Optional.empty(), optionalWinner);
    }

    @Test
    void getWinner_noCandidates() {
        var testVoteTally = new VoteTally();

        var optionalWinner = testVoteTally.getWinner();
        assertFalse(optionalWinner.isPresent());
        assertEquals(Optional.empty(), optionalWinner);
    }

    @Test
    void getWinner_oneCandidate_noVotes() {
        var testVoteTally = new VoteTally(new HashMap<>(
                Map.of("John Smith", 0)));

        var optionalWinner = testVoteTally.getWinner();
        assertFalse(optionalWinner.isPresent());
        assertEquals(Optional.empty(), optionalWinner);
    }

}