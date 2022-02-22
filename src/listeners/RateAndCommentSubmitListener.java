package listeners;

public interface RateAndCommentSubmitListener {

    void submissionOccurred(int gradeId, String text, int customerId, int shoeId);

}
