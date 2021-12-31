package eu.cokejoke.review;

import eu.cokejoke.review.model.Review;
import eu.cokejoke.review.service.ReviewService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/review")
public class ReviewResource {

    @Inject
    ReviewService reviewService;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Review get(@PathParam("id") Long id) {
        Review review = reviewService.getReviewById(id);
        if (review == null) {
            throw new WebApplicationException(404);
        }
        return review;
    }

    @GET
    @Produces("application/json")
    public Response list(
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("5") int pageSize) {
        List<Review> reviews = reviewService.getReviewsByPage(pageIndex, pageSize);
        if (reviews.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(reviews).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Review review) {
        Review createdReview = reviewService.addReview(review);
        return Response.status(Response.Status.CREATED).entity(createdReview).build();
    }

}
