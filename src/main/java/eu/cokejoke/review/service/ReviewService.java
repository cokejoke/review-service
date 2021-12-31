package eu.cokejoke.review.service;

import eu.cokejoke.review.model.Review;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReviewService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createReview(String title, String content, String artist, String reviewer, String smartlink, String hashtags) {
        Review review = new Review();
        review.setDate(LocalDateTime.now());
        review.setTitle(title);
        review.setContent(content);
        review.setArtist(artist);
        review.setReviewer(reviewer);
        review.setSmartlink(smartlink);
        review.setHashtags(hashtags);
        entityManager.persist(review);
    }

    @Transactional
    public Review addReview(Review review) {
        review.setDate(LocalDateTime.now());
        entityManager.persist(review);
        return review;
    }

    public Review getReviewById(Long id) {
        return entityManager.find(Review.class, id);
    }

    public List<Review> getReviewsByPage(int pageIndex, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Review> criteria = builder.createQuery(Review.class);
        Root<Review> reviewRoot = criteria.from(Review.class);
        criteria.select(reviewRoot);
        TypedQuery<Review> query = entityManager.createQuery(criteria);
        query.setFirstResult(pageIndex);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
