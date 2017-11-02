# CMPE277TeamProject : FBDroid : Android 

1.	High level and component level design

As can be seen from the diagram below, the android app communicates with our NodeJS backend services with MongoDB as our datastore. The android app also stores the uploaded images to Amazon S3 store, which urls we store in our mongo store.


![Alt text](/snapshot/Picture1.png?raw=true "Optional Title")



2.	Technology choices

	Followed Google’s Material Design Guidelines for our UI.
	The entire UI is built using Fragments against using too many activities to keep it light on memory.
	We used Retrofit by Square against using Volley for making the calls to the REST services of our app due its performance advantage.
	We have used Android drawables and styling for achieving the same, keeping the apk size low and faster on loading.
	We used Amazon’s S3 bucket for storing the app’s images and then stored them in our document store, which is MongoDB.
	We have written our services, in NodeJS, using ExpressJS as our backend framework.
	Used Glide for image caching and retrieval.

3.	Features 

	Signup and Authentication 
This feature is implemented to facilitate user with the signup and then sign in. When a new user signs up for the app, he is required to give email and password. A four digit OTP is mailed to the users’ email Id for verification. Now when users click on sign up button he is prompted to enter the verification code and allowed to login the application. 

![Alt text](/snapshot/Picture2.png?raw=true "Optional Title")



●	Profile Management
Every application user has his profile. The user is facilitated to update the profile fields like ‘aboutme’, ‘profession’, ‘interests’, ‘location’, ‘Screenname’. Along with this he can also update the profile picture. Profile picture is stored in S3 buckets and the link to the image is stored in mongoDB database. The screen name of every user is ensured to be unique. User can also choose to browse the public profiles and users in his friend list.

![Alt text](/snapshot/Picture3.png?raw=true "Optional Title")



●	Settings
User is provisioned to update the settings like visibility, notification and offline. User can toggle to make choices among the options. Depending on his configuration the user will be public/friendsonly, online/offline, notification turned on/off.


![Alt text](/snapshot/Picture4.png?raw=true "Optional Title")





●	Friends
User can browse for friends and send friend requests or follow them. User can see his pending requests, list of friends, et al. Friend requests can to sent and user has an option to confirm or delete the request.





![Alt text](/snapshot/Picture5.png?raw=true "Optional Title")





●	Post Management
User is capable of adding a post. Post may consist of text content or image or both. Posts of friends and followers is visible on all the the friend’s timeline.



![Alt text](/snapshot/Picture7.png?raw=true "Optional Title")









●	Follow
User has an option to follow another user. Follower sees all the posts of the followee. 


![Alt text](/snapshot/Picture8.png?raw=true "Optional Title")








●	Timeline 
Timeline is the landing page of the application. Timeline shows all the posts of friends and followee. Posts are seen in chronological order where recent posts are seen on the top.   


![Alt text](/snapshot/Picture9.png?raw=true "Optional Title")

  
	
