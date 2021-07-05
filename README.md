# AlexaReview
Sample Application to get reviews on Alexa using Spring boot, Thymeleaf and H2 DB

From Browser application can be accessed via: http://localhost:8080/alexa/index

From Postman endpoints:
Review Dump-To store all old dumps to DB: Post request - http://localhost:8080/alexa/reviewsDump
Sample Input: 
{
	"reviews": [
		{
			"review": "Pero deberia de poder cambiarle el idioma a alexa",
			"author": "WarcryxD",
			"review_source": "iTunes",
			"rating": 4,
			"title": "Excelente",
			"product_name": "Amazon Alexa",
			"reviewed_date": "2018-01-12T02:27:03.000Z"
		},
		{
			"review": "Cannot fix connection glitches without this-also fix connection glitches \n\nSmart things sees my light and Alexa doesn’t :(\n\n*update new devices “unresponsive” each day...they are working fine in SmartThings. No way to delete disabled devices. Very poor.",
			"author": "Ranchorat",
			"review_source": "iTunes",
			"rating": 1,
			"title": "Need to be able to delete devices",
			"product_name": "Amazon Alexa",
			"reviewed_date": "2017-12-06T13:06:33.000Z"
		}
}

Add a single review: Post request - http://localhost:8080/alexa/reviews
Sample Input:
{
			"review": "Works well for music. But audible books stop and error out often like on every book.  No way to do playlist of books or repeat a book or playlist automatically.",
			"author": "",
			"review_source": "iTune",
			"rating": 5,
			"title": "",
			"product_name": "Amazon Alexa",
			"reviewed_date": "2018-03-25T00:00:00.000Z"
		}
  
  Search Review with or without filter: Get Request-
  http://localhost:8080/alexa/reviews?reviewDate=2018-01-12T02:27:03.000Z&rating=4&storeType=GooglePlayStore
  
  Total Rating for each Catergory: Get Request
  http://localhost:8080/alexa/totalRatingByCategory
  
  Average Monthly rating: Get Request
  http://localhost:8080/alexa/AverageMonthlyRating


