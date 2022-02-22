package listeners;

import dbobjectmodel.BaseProduct;
import dbobjectmodel.Comment;
import dbobjectmodel.Rating;

import java.util.List;

public interface RateCommentListener {

    void rateAndComment(BaseProduct product, String average, List<Rating> ratings, List<Comment> comments);

}
