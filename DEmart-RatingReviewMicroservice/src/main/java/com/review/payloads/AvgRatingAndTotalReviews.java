package com.review.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvgRatingAndTotalReviews {
	
	private long count;
	private double rating;

}
