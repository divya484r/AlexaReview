package com.amazon.AlexaReview.controller;

import com.amazon.AlexaReview.entity.Reviews;
import com.amazon.AlexaReview.model.AverageRating;
import com.amazon.AlexaReview.model.Review;
import com.amazon.AlexaReview.model.ReviewResult;
import com.amazon.AlexaReview.model.TotalRating;
import com.amazon.AlexaReview.service.ReviewService;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/alexa")
public class AlexaReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        try {
            Reviews reviews1 = reviewService.createReview(review);
            return new ResponseEntity<>(reviews1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviews(@RequestParam(required = false) String reviewDate,
                                        @RequestParam(required = false) Integer rating,
                                        @RequestParam(required = false) String storeType) {
        Optional<ReviewResult> results;
        try {
            ReviewResult reviewsList = reviewService.searchReviews(reviewDate, rating, storeType);

            if (reviewsList != null) {
                System.out.println("reviewsList size=" + reviewsList.getReview().size());
                results = Optional.of(reviewsList);

                return results.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @RequestMapping(value = "/totalRatingByCategory", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<?> getTotalRatingByCategory() {
        List<TotalRating> totalRatings = reviewService.getTotalRating();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(totalRatings);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @RequestMapping(value = "/AverageMonthlyRating", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<?> getAverageMonthlyRating() {
        List<AverageRating> monthlyAverageRatings = reviewService.getAverageRating();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(monthlyAverageRatings);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @PostMapping("/reviewsDump")
    public ResponseEntity<?> createReviewDump(@RequestBody ReviewResult review) {
        try {
            reviewService.createReviewDump(review);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @RequestMapping(value = "/reviewsWithFilters", method = RequestMethod.GET, produces = {"application/json"})
//    public ResponseEntity<?> getShipmentByFilters(@RequestHeader HttpHeaders headers, @RequestParam(value = "filter", required = true) final List<String> filter,
//                                                  @RequestParam(value = "fields", required = false, defaultValue = "") final List<String> fields,
//                                                  @RequestParam(value = "sort", required = false, defaultValue = "id") final String sort,
//                                                  @RequestParam(value = "count", required = false, defaultValue = "10") final String count,
//                                                  @RequestParam(value = "anchor", required = false, defaultValue = "0") final String anchor) throws Exception {
//        log.info("Event=getShipmentByFilters Status=Started");
//        FilterSet filters = Filters.parse(filter);
//
//        log.info("Event=getShipmentByFilters FilterSize={} Filters={} Fields={} Sort={} Count={} Anchor={} AppId={}",
//                filter.size(), String.valueOf(filter), String.valueOf(fields), sort, count, anchor, getAppId(headers));
//
//        //Filter Validation
//        new ParameterValidation()
//                .withCondition(filter.size() > 0, "filter", ValidationErrorCode.MISSING_FILTER_FIELD)
//                .withRule(Filters.Validation.allowedFilters(filters,
//                        new FilterDefinition(ORDER_NUMBER),
//                        new FilterDefinition(FULFILLMENT_REQUESTNUMBER),
//                        new FilterDefinition(ORDER_TYPE)
//                )).validate();
//
//        if ((filter.size() == 1 && !filters.hasField(FULFILLMENT_REQUESTNUMBER))
//                || (filter.size() == 2 && !(filters.hasField(ORDER_NUMBER) && filters.hasField(ORDER_TYPE)))) {
//            log.error("Event=getShipmentByFilters Status=Error ErrorMsg=\"Invalid Filter Combination.\" ResponseCode=400 Filter={}", filter);
//            JSONObject msgObject = new JSONObject();
//            msgObject.put("message", "Validation Failed");
//
//            JSONObject errorsObj = new JSONObject();
//            errorsObj.put("code", "INVALID_FILTER_FIELD");
//            errorsObj.put("field", "/filters");
//            errorsObj.put("message", "The given filter should be provided with the combination");
//
//            JSONArray errors = new JSONArray();
//            errors.add(errorsObj);
//
//            msgObject.put("errors", errors);
//            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(msgObject);
//        } else if (filter.size() > 2) {
//            log.error("Event=getShipmentByFilters Status=Error ErrorMsg=\"Maximum number of filters exceeded!\" ResponseCode=400 Filter={}", filter);
//            JSONObject msgObject = new JSONObject();
//            msgObject.put("message", "Validation Failed");
//
//            JSONObject errorsObj = new JSONObject();
//            errorsObj.put("code", "INVALID_FILTER_FIELD");
//            errorsObj.put("field", "/filters");
//            errorsObj.put("message", "Maximum No. of filters exceeded");
//
//            JSONArray errors = new JSONArray();
//            errors.add(errorsObj);
//
//            msgObject.put("errors", errors);
//            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(msgObject);
//        }
//
//        final ShipmentsRequest shipmentsRequest = ShipmentsRequest.builder()
//                .filters(filter)
//                .fields(fields)
//                .sort(sort)
//                .anchor(anchor)
//                .count(count)
//                .build();
//
//        ShipDetailsListResponse shipments;
//
//        if (shipmentsRequest != null) {
//            log.info("shipmentsRequest is not null {} ", shipmentsRequest.toString());
//        }
//
//        shipments = shipmentListService.getShipmentDetailsByFilter(shipmentsRequest, filter, filters);
//
//        val entity = new CollectionResource
//                .Builder()
//                .withObjects((List<ShipmentDetails>) shipments.getObjects())
//                .withPages(shipments.getPages())
//                .build();
//        GET_SHIPMENT_LIST_SUCCESS.incrementAndGet();
//        log.info("Event=getShipmentByFilters Status=Completed ResponseCode=200");
//        return new ResponseEntity<>(entity, HttpStatus.OK);
//    }


}
