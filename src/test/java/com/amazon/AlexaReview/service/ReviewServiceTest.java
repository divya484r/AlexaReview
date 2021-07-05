package com.amazon.AlexaReview.service;

import com.amazon.AlexaReview.entity.Reviews;
import com.amazon.AlexaReview.model.AverageRating;
import com.amazon.AlexaReview.model.Review;
import com.amazon.AlexaReview.model.ReviewResult;
import com.amazon.AlexaReview.model.TotalRating;
import com.amazon.AlexaReview.repository.ReviewsRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


public class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    ReviewsRepository reviewsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewsRepository);
    }


    @Test
    public void testSearchReviewsWithAllFilters() throws ParseException {
        Date reviewedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2017-12-06T13:06:33.000Z");
        Integer rating = 2;
        String storeType = "iTunes";
        List<Reviews> lstDBResult = new ArrayList<>();
        Reviews reviews = mockReviews();
        lstDBResult.add(reviews);

        List<Review> lstReviews = new ArrayList<>();
        Review review1 = mockReview();
        lstReviews.add(review1);

        ReviewResult reviewResult = new ReviewResult();
        reviewResult.setReview(lstReviews);
        Mockito.when(reviewsRepository.findMyReviews(reviewedDate, rating, storeType)).thenReturn(lstDBResult);

        ReviewResult res = reviewService.searchReviews("2017-12-06T13:06:33.000Z", rating, storeType);
        Assert.assertEquals(res.getReview().size(), reviewResult.getReview().size());
        Assert.assertEquals(res.getReview().get(0).getAuthor(), reviewResult.getReview().get(0).getAuthor());
    }

    @Test
    public void testSearchReviewsWithNoFilters() throws ParseException {
        Date reviewedDate = null;
        Integer rating = null;
        String storeType = null;
        List<Reviews> lstDBResult = new ArrayList<>();
        Reviews reviews = mockReviews();
        lstDBResult.add(reviews);

        List<Review> lstReviews = new ArrayList<>();
        Review review1 = mockReview();
        lstReviews.add(review1);

        ReviewResult reviewResult = new ReviewResult();
        reviewResult.setReview(lstReviews);
        Mockito.when(reviewsRepository.findMyReviews(reviewedDate, rating, storeType)).thenReturn(lstDBResult);

        ReviewResult res = reviewService.searchReviews(null, rating, storeType);
        Assert.assertEquals(res.getReview().size(), reviewResult.getReview().size());
        Assert.assertEquals(res.getReview().get(0).getAuthor(), reviewResult.getReview().get(0).getAuthor());
    }

    @Test
    public void testSearchReviewsWithOneFilters() throws ParseException {
        Date reviewedDate = null;
        Integer rating = 2;
        String storeType = null;

        List<Reviews> lstDBResult = new ArrayList<>();
        Reviews reviews = mockReviews();
        lstDBResult.add(reviews);

        List<Review> lstReviews = new ArrayList<>();
        Review review1 = mockReview();
        lstReviews.add(review1);

        ReviewResult reviewResult = new ReviewResult();
        reviewResult.setReview(lstReviews);
        Mockito.when(reviewsRepository.findMyReviews(reviewedDate, rating, storeType)).thenReturn(lstDBResult);

        ReviewResult res = reviewService.searchReviews(null, rating, storeType);
        Assert.assertEquals(res.getReview().size(), reviewResult.getReview().size());
        Assert.assertEquals(res.getReview().get(0).getAuthor(), reviewResult.getReview().get(0).getAuthor());
    }


    private Reviews mockReviews() throws ParseException {
        Reviews reviews = new Reviews();

        reviews.setReview("Cannot fix connection glitches without this-also fix connection glitches \n\nSmart things sees my light and Alexa doesn’t :(\n\n*update new devices “unresponsive” each day...they are working fine in SmartThings. No way to delete disabled devices. Very poor.");
        reviews.setAuthor("Ranchorat");
        reviews.setProductName("Amazon Alexa");
        reviews.setRating(2);
        reviews.setTitle("Need to be able to delete devices");
        reviews.setReviewSource("iTunes");

        reviews.setReviewedDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse("2017-12-06T13:06:33.000Z"));
        return reviews;
    }

    private Review mockReview() {
        Review review1 = new Review();
        review1.setReviewVal("Cannot fix connection glitches without this-also fix connection glitches \n\nSmart things sees my light and Alexa doesn’t :(\n\n*update new devices “unresponsive” each day...they are working fine in SmartThings. No way to delete disabled devices. Very poor.");
        review1.setAuthor("Ranchorat");
        review1.setProductName("Amazon Alexa");
        review1.setRating(2);
        review1.setTitle("Need to be able to delete devices");
        review1.setReviewSource("iTunes");
        review1.setReviewedDate("2017-12-06T13:06:33.000Z");
        return review1;
    }

    @Test
    public void testCreateReview() throws Exception {
        Review review = mockReview();
        Reviews reviews =mockReviews();

        Mockito.when(reviewsRepository.save(Mockito.any(Reviews.class))).thenReturn(reviews);
        Reviews newReview = reviewService.createReview(review);

        Mockito.verify(reviewsRepository).save((Reviews) Mockito.any());
        Assert.assertNotNull(newReview.getAuthor());
        Assert.assertEquals(newReview.getAuthor(),reviews.getAuthor());
    }

    @Test
    public void testGetTotalRating(){
        List<TotalRating> totalRatingList = new ArrayList<>();
        TotalRating totalRating1 = new TotalRating("iTunes", 3, 10);
        totalRatingList.add(totalRating1);
        TotalRating totalRating2 = new TotalRating("iTunes", 4, 15);
        totalRatingList.add(totalRating2);
        TotalRating totalRating3 = new TotalRating("GooglePlayStore", 3, 20);
        totalRatingList.add(totalRating3);
        Mockito.when(reviewsRepository.getTotalRatingsByCategory()).thenReturn(totalRatingList);
        List<TotalRating> result = reviewService.getTotalRating();
        Assert.assertEquals(totalRatingList.size(), result.size());
        Assert.assertEquals(totalRatingList.get(0).getTotalRating(),result.get(0).getTotalRating() );
    }

    @Test
    public void testGetAverageMonthlyRating(){
        List<AverageRating> averageRatingList = new ArrayList<>();
        AverageRating averageRating1 = new AverageRating("iTunes", 3, 2018, 3.5);
        averageRatingList.add(averageRating1);
        AverageRating averageRating2 = new AverageRating("iTunes", 3, 2017, 3.5);
        averageRatingList.add(averageRating2);
        AverageRating averageRating3 = new AverageRating("iTunes", 5, 2018, 4.5);
        averageRatingList.add(averageRating3);
        AverageRating averageRating4 = new AverageRating("iTunes", 6, 2018, 3.0);
        averageRatingList.add(averageRating4);
        Mockito.when(reviewsRepository.getAverageRating()).thenReturn(averageRatingList);
        List<AverageRating> result = reviewService.getAverageRating();
        Assert.assertEquals(averageRatingList.size(), result.size());
        Assert.assertEquals(averageRatingList.get(0).getAverageRating(),result.get(0).getAverageRating(),0);

    }

    @Test
    public void testCreateDump() throws IOException, ParseException {
        String alexaReviews = StreamUtils.copyToString(getClass().getResource("/alexa.json").openStream(),
                UTF_8);
        ReviewResult alexaReviewDump = new ObjectMapper().readValue(alexaReviews, ReviewResult.class);
        reviewService.createReviewDump(alexaReviewDump);
        Mockito.verify(reviewsRepository, Mockito.times(6110)).save((Reviews) Mockito.any());
    }

}

